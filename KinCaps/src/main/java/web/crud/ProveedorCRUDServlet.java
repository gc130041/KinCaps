package web.crud;

import dao.ProveedorDAO;
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
import modelo.Proveedor;

@WebServlet(name = "ProveedorCRUDServlet", urlPatterns = {"/mantenimiento/proveedores", "/mantenimiento/proveedores/*"})
public class ProveedorCRUDServlet extends HttpServlet {

    private String getSafeString(String value) {
        return value != null ? value : "";
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        Proveedor proveedorAEditar = (Proveedor) request.getAttribute("proveedorEditar");
        boolean esEdicion = (proveedorAEditar != null);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>" + (esEdicion ? "Editar" : "Agregar") + " Proveedor</title>");
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
            out.println("            <h3 class='text-center mb-4'>Formulario para " + (esEdicion ? "editar un" : "agregar un") + " Proveedor</h3>");
            out.println("            </div>");
            out.println("            <div class='card-body'>");

            String actionUrl = esEdicion ? request.getContextPath() + "/mantenimiento/proveedores/actualizar" : request.getContextPath() + "/mantenimiento/proveedores/agregar";
            out.println("                <form method='post' action='" + actionUrl + "'>");

            if (esEdicion) {
                 out.println("<input type='hidden' name='id' value='" + proveedorAEditar.getIdProveedor() + "'>");
            }

            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Nombre</label>");
            out.println("                        <input type='text' class='form-control' name='nombre' required value='" + (esEdicion ? getSafeString(proveedorAEditar.getNombre()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Contacto</label>");
            out.println("                        <input type='text' class='form-control' name='contacto' required value='" + (esEdicion ? getSafeString(proveedorAEditar.getContacto()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Teléfono</label>");
            out.println("                        <input type='text' class='form-control' name='telefono' required value='" + (esEdicion ? getSafeString(proveedorAEditar.getTelefono()) : "") + "'>");
            out.println("                    </div>");

            out.println("                    <div class='mt-4 mb-5'>");
            out.println("                        <button type='submit' class='btn " + (esEdicion ? "btn-warning" : "btn btn-save") + " me-2'>" + (esEdicion ? "Actualizar" : "Guardar") + " Proveedor</button>");
            out.println("                        <a href='" + request.getContextPath() + "/mantenimiento/proveedores/listar' class='btn btn-secondary'>Cancelar</a>");
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

        ProveedorDAO dao = new ProveedorDAO();
        Proveedor proveedor;
        int id;

        try {
            switch (accion) {
                case "/agregar":
                    if (request.getMethod().equalsIgnoreCase("GET")) {
                        mostrarFormulario(request, response);
                    } else {
                        String nombre = request.getParameter("nombre");
                        String contacto = request.getParameter("contacto");
                        String telefono = request.getParameter("telefono");
                        proveedor = new Proveedor(nombre, contacto, telefono);
                        dao.guardar(proveedor);
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/proveedores/listar");
                    }
                    break;
                case "/editar":
                    id = Integer.parseInt(request.getParameter("id"));
                    proveedor = dao.buscarPorId(id);
                    if (proveedor != null) {
                        request.setAttribute("proveedorEditar", proveedor);
                        mostrarFormulario(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/proveedores/listar");
                    }
                    break;
                case "/actualizar":
                    id = Integer.parseInt(request.getParameter("id"));
                    proveedor = dao.buscarPorId(id);
                    if (proveedor != null) {
                        proveedor.setNombre(request.getParameter("nombre"));
                        proveedor.setContacto(request.getParameter("contacto"));
                        proveedor.setTelefono(request.getParameter("telefono"));
                        dao.actualizar(proveedor);
                    }
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/proveedores/listar");
                    break;
                case "/eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/proveedores/listar");
                    break;
                case "/listar":
                default:
                    List<Proveedor> listaProveedores = dao.listarTodos();
                    request.setAttribute("listaProveedores", listaProveedores);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/proveedor.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(ProveedorCRUDServlet.class.getName()).log(Level.SEVERE, "Error en el servlet", e);
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
        return "Servlet para CRUD de Proveedores";
    }
}