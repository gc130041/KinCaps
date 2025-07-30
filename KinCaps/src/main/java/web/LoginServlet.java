package web;

import dao.CookieAuthDAO;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.CookieAuth;
import modelo.Empleado;
import modelo.Usuario;
import util.PasswordHash;
import util.TokenUtil;
import dao.UsuarioDAO;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("recuerdame")) {
                    String cookieValue = cookie.getValue();
                    if (validarCookieRecuerdame(cookieValue, request, response)) {
                        return;
                    }
                }
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember-me");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.verificarCredenciales(email, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            if (rememberMe != null && rememberMe.equals("on")) {
                crearCookieRecuerdame(usuario, response);
            }

            if (usuario instanceof Cliente) {
                response.sendRedirect(request.getContextPath() + "/gorras");
            } else if (usuario instanceof Empleado) {
                response.sendRedirect(request.getContextPath() + "/mantenimiento");
            }
        } else {
            request.setAttribute("error", "Correo o contraseña inválidos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean validarCookieRecuerdame(String cookieValue, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] parts = cookieValue.split(":");
        if (parts.length != 2) {
            borrarCookie(response);
            return false;
        }

        String selector = parts[0];
        String validador = parts[1];

        CookieAuthDAO cookieDAO = new CookieAuthDAO();
        CookieAuth token = cookieDAO.buscarPorSelector(selector);

        if (token == null || token.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            if (token != null) {
                cookieDAO.eliminarPorSelector(selector);
            }
            borrarCookie(response);
            return false;
        }

        String validadorHashDB = token.getTokenHash();
        String validadorHashRecibido = PasswordHash.contrasenaHash(validador);

        if (!validadorHashDB.equals(validadorHashRecibido)) {
            cookieDAO.eliminarPorSelector(selector);
            borrarCookie(response);
            return false;
        }

        Usuario usuario = null;
        if (token.getCliente() != null) {
            usuario = token.getCliente();
        } else if (token.getEmpleado() != null) {
            usuario = token.getEmpleado();
        }

        if (usuario == null) {
            cookieDAO.eliminarPorSelector(selector);
            borrarCookie(response);
            return false;
        }
        
        cookieDAO.eliminarPorSelector(selector);
        crearCookieRecuerdame(usuario, response);

        HttpSession session = request.getSession(true);
        session.setAttribute("usuario", usuario);
        
        if (usuario instanceof Cliente) {
            response.sendRedirect(request.getContextPath() + "/gorras");
        } else if (usuario instanceof Empleado) {
            response.sendRedirect(request.getContextPath() + "/mantenimiento");
        }

        return true;
    }

    private void crearCookieRecuerdame(Usuario usuario, HttpServletResponse response) {
        String selector = TokenUtil.generarNuevoToken();
        String validador = TokenUtil.generarNuevoToken();
        String validadorHash = PasswordHash.contrasenaHash(validador);

        Cliente cliente = null;
        Empleado empleado = null;

        if (usuario instanceof Cliente) {
            cliente = (Cliente) usuario;
        } else if (usuario instanceof Empleado) {
            empleado = (Empleado) usuario;
        } else {
            return;
        }

        CookieAuthDAO cookieDAO = new CookieAuthDAO();
        LocalDateTime expiracion = LocalDateTime.now().plusDays(30);

        CookieAuth token = new CookieAuth(selector, validadorHash, cliente, empleado, expiracion);
        cookieDAO.crearToken(token);

        Cookie cookieRecuerdame = new Cookie("recuerdame", selector + ":" + validador);
        cookieRecuerdame.setMaxAge(30 * 24 * 60 * 60);
        cookieRecuerdame.setPath("/");
        cookieRecuerdame.setHttpOnly(true);
        response.addCookie(cookieRecuerdame);
    }

    private void borrarCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("recuerdame", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}