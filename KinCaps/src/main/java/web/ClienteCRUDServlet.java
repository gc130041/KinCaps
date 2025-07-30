package web;

import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Cliente.Estado;

@WebServlet(name = "ClienteCRUDServlet", urlPatterns = {"/mantenimiento/clientes", "/mantenimiento/clientes/*"})
public class ClienteCRUDServlet extends HttpServlet {

    private String getSafeString(String value) {
        return value != null ? value : "";
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        Cliente clienteAEditar = (Cliente) request.getAttribute("clienteEditar");
        boolean esEdicion = (clienteAEditar != null);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>" + (esEdicion ? "Editar" : "Agregar") + " Cliente</title>");
            out.println("    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
            out.println("    <link rel='icon' href='" + request.getContextPath() + "/img/Logo/logonobg.png' type='image/x-icon'>");
            out.println("    <link rel='stylesheet' href='" + request.getContextPath() + "/style/tablas.css'>");
            out.println("</head>");
            out.println("<body class='d-flex flex-column min-vh-100 bg-light'>");
            out.println("<nav class='navbar navbar-expand-lg bg-header navbar-dark'>");
            out.println("    <div class='container-fluid'>");
            out.println("        <a class='navbar-brand fw-bold'>Panel de Administración</a>");
            out.println("        <button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#menuNav'>");
            out.println("            <span class='navbar-toggler-icon'></span>");
            out.println("        </button>");
            out.println("        <div class='collapse navbar-collapse' id='menuNav'>");
            out.println("            <ul class='navbar-nav ms-auto'>");
            out.println("                <li class='nav-item'>");
            out.println("                    <a class='nav-link' href='" + request.getContextPath() + "/pages/mainmenuadmin.jsp" + "' role='button'>");
            out.println("                        Menú Principal");
            out.println("                    </a>");
            out.println("                </li>");
            out.println("            </ul>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</nav>");
            out.println("    <main class='flex-grow-1'>");
            out.println("    <div class='container mt-5'>");
            out.println("        <div class='border-5'>");
            out.println("            <h3 class='text-center mb-4'>Formulario para " + (esEdicion ? "editar un" : "agregar un") + " Cliente</h3>");
            out.println("            </div>");
            out.println("            <div class='card-body'>");

            String actionUrl = esEdicion ? request.getContextPath() + "/mantenimiento/clientes/actualizar" : request.getContextPath() + "/mantenimiento/clientes/agregar";
            out.println("                <form method='post' action='" + actionUrl + "'>");

            if (esEdicion) {
                out.println("<input type='hidden' name='id' value='" + clienteAEditar.getIdCliente() + "'>");
            }

            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Nombre</label>");
            out.println("                        <input type='text' class='form-control' name='nombre' required value='" + (esEdicion ? getSafeString(clienteAEditar.getNombre()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Apellido</label>");
            out.println("                        <input type='text' class='form-control' name='apellido' required value='" + (esEdicion ? getSafeString(clienteAEditar.getApellido()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Email</label>");
            out.println("                        <input type='email' class='form-control' name='email' required value='" + (esEdicion ? getSafeString(clienteAEditar.getEmail()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Teléfono</label>");
            out.println("                        <input type='text' class='form-control' name='telefono' required value='" + (esEdicion ? getSafeString(clienteAEditar.getTelefono()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Dirección</label>");
            out.println("                        <input type='text' class='form-control' name='direccion' required value='" + (esEdicion ? getSafeString(clienteAEditar.getDireccion()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Estado</label>");
            out.println("                        <select class='form-select' name='estado' required>");
            out.println("                            <option value=''>Seleccione un estado</option>");
            for (Cliente.Estado estado : Cliente.Estado.values()) {
                boolean seleccionado = esEdicion && clienteAEditar.getEstado() == estado;
                out.println("                        <option value='" + estado.name() + "'" + (seleccionado ? " selected" : "") + ">" + estado.name() + "</option>");
            }
            out.println("                        </select>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Contraseña (Hash)</label>");
            out.println("                        <input type='text' class='form-control' name='contrasenaHash' required value='" + (esEdicion ? getSafeString(clienteAEditar.getContrasenaHash()) : "") + "'>");
            out.println("                    </div>");

            out.println("                    <div class='mt-4 mb-5'>");
            out.println("                        <button type='submit' class='btn " + (esEdicion ? "btn-warning" : "btn btn-save") + " me-2'>" + (esEdicion ? "Actualizar" : "Guardar") + " Cliente</button>");
            out.println("                        <a href='" + request.getContextPath() + "/mantenimiento/clientes/listar' class='btn btn-secondary'>Cancelar</a>");
            out.println("                    </div>");
            out.println("                </form>");
            out.println("            </div>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("    </main>");
            out.println("<footer class='bg-header text-white text-center py-4 mt-auto'>");
            out.println("    <p class='mb-1'>2025 KINCAPS. Todos los derechos reservados.</p>");
            out.println("    <small>");
            out.println("        <a href='#' class='text-white text-decoration-none me-3'>Política de Privacidad</a>");
            out.println("        <a href='#' class='text-white text-decoration-none'>Términos y Condiciones</a>");
            out.println("    </small>");
            out.println("</footer>");
            out.println("    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String getAccion(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            return "/listar";
        }
        return pathInfo;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = getAccion(request);

        ClienteDAO dao = new ClienteDAO();
        Cliente cliente;
        int id;

        try {
            switch (accion) {
                case "/agregar":
                    if (request.getMethod().equalsIgnoreCase("GET")) {
                        mostrarFormulario(request, response);
                    } else {
                        String nombre = request.getParameter("nombre");
                        String apellido = request.getParameter("apellido");
                        String email = request.getParameter("email");
                        String telefono = request.getParameter("telefono");
                        String direccion = request.getParameter("direccion");
                        String contrasenaHash = request.getParameter("contrasenaHash");
                        Estado estado = Estado.valueOf(request.getParameter("estado"));

                        cliente = new Cliente(nombre, apellido, email, telefono, direccion, contrasenaHash, estado);
                        dao.crearCliente(cliente);
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/clientes/listar");
                    }
                    break;
                case "/editar":
                    id = Integer.parseInt(request.getParameter("id"));
                    cliente = dao.buscarPorId(id);
                    if (cliente != null) {
                        request.setAttribute("clienteEditar", cliente);
                        mostrarFormulario(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/clientes/listar");
                    }
                    break;
                case "/actualizar":
                    id = Integer.parseInt(request.getParameter("id"));
                    cliente = dao.buscarPorId(id);
                    if (cliente != null) {
                        cliente.setNombre(request.getParameter("nombre"));
                        cliente.setApellido(request.getParameter("apellido"));
                        cliente.setEmail(request.getParameter("email"));
                        cliente.setTelefono(request.getParameter("telefono"));
                        cliente.setDireccion(request.getParameter("direccion"));
                        cliente.setEstado(Estado.valueOf(request.getParameter("estado")));
                        cliente.setContrasenaHash(request.getParameter("contrasenaHash"));
                        dao.actualizar(cliente);
                    }
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/clientes/listar");
                    break;
                case "/eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/clientes/listar");
                    break;
                case "/listar":
                default:
                    List<Cliente> listaClientes = dao.listarTodos();
                    request.setAttribute("listaClientes", listaClientes);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/clientes.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(ClienteCRUDServlet.class.getName()).log(Level.SEVERE, "Error en el servlet", e);
            throw new ServletException("Ocurrió un error en la aplicación.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para CRUD de Clientes";
    }
}