package web;

import dao.UsuarioDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Usuario;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.verificarCredenciales(email, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            if (usuario instanceof Cliente) {
                response.sendRedirect("pages/cliente.jsp");
            } else if (usuario instanceof Empleado) {
                response.sendRedirect("pages/empleado.jsp");
            }
        } else {
            request.setAttribute("error", "Correo o contraseña inválidos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}