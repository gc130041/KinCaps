package web;

import dao.ClienteDAO;
import modelo.Cliente;
import util.PasswordHash;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Cliente.Estado;

@WebServlet(name = "ServletRegistro", value = "/register")
public class RegisterServlet extends HttpServlet {

    private ClienteDAO clienteDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("name");
        String apellido = request.getParameter("lastname");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("ubicacion");
        String password = request.getParameter("password");
        String confirmarPassword = request.getParameter("confirmarpassword");

        if (isCampoVacio(nombre) || isCampoVacio(apellido) || isCampoVacio(email)
                || isCampoVacio(telefono) || isCampoVacio(direccion) || isCampoVacio(password)
                || isCampoVacio(confirmarPassword)) {

            request.setAttribute("error", "Todos los campos son obligatorios.");
            repopularFormulario(request, nombre, apellido, email, telefono, direccion);
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmarPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            repopularFormulario(request, nombre, apellido, email, telefono, direccion);
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }

        try {
            if (clienteDAO.emailExiste(email)) {
                request.setAttribute("error", "El correo electrónico ya está registrado.");
                repopularFormulario(request, nombre, apellido, "", telefono, direccion);
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
                return;
            }

            String hashPassword = PasswordHash.contrasenaHash(password);
            Cliente nuevoCliente = new Cliente(nombre, apellido, email, telefono, direccion, hashPassword, Estado.ACTIVO);
            clienteDAO.crearCliente(nuevoCliente);

            response.sendRedirect(request.getContextPath() + "/index.jsp?registro=exito");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error interno del servidor. Intente más tarde.");
            repopularFormulario(request, nombre, apellido, email, telefono, direccion);
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }
    }

    private boolean isCampoVacio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    private void repopularFormulario(HttpServletRequest request, String nombre, String apellido, String email, String telefono, String direccion) {
        request.setAttribute("nameValue", nombre);
        request.setAttribute("lastnameValue", apellido);
        request.setAttribute("emailValue", email);
        request.setAttribute("telefonoValue", telefono);
        request.setAttribute("ubicacionValue", direccion);
    }
}
