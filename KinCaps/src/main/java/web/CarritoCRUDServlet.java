package web;

import dao.CarritoDAO;
import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Carrito;
import modelo.Carrito.Estado;
import modelo.Cliente;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import java.time.LocalDateTime;

@WebServlet(name = "CarritoCRUDServlet", urlPatterns = {"/mantenimiento/carrito", "/mantenimiento/carrito/*"})
public class CarritoCRUDServlet extends HttpServlet {

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Cliente> listaClientes = new ClienteDAO().listarTodos();
        Carrito carritoAEditar = (Carrito) request.getAttribute("carritoEditar");
        boolean esEdicion = (carritoAEditar != null);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>" + (esEdicion ? "Editar" : "Agregar") + " Carrito</title>");
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
            out.println("                <li class='nav-item dropdown'>");
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
            out.println("            <h3 class='text-center mb-4'>Formulario para " + (esEdicion ? "editar un" : "agregar un") + " Carrito</h3>");
            out.println("            </div>");
            out.println("            <div class='card-body'>");

            String actionUrl = esEdicion ? request.getContextPath() + "/mantenimiento/carrito/actualizar" : request.getContextPath() + "/mantenimiento/carrito/agregar";
            out.println("                <form method='post' action='" + actionUrl + "'>");

            if (esEdicion) {
                out.println("<input type='hidden' name='id' value='" + carritoAEditar.getIdCarrito() + "'>");
            }

            out.println("    <div class='mb-3'>");
            out.println("        <label class='form-label'>Cliente</label>");
            out.println("        <select class='form-select' name='idCliente' required>");
            out.println("            <option value=''>Seleccione un cliente</option>");
            for (Cliente cliente : listaClientes) {
                int id = cliente.getIdCliente();
                String nombreCompleto = cliente.getNombre() + " " + cliente.getApellido();
                boolean seleccionado = esEdicion && carritoAEditar.getCliente() != null && carritoAEditar.getCliente().getIdCliente() == id;
                out.println("            <option value='" + id + "'" + (seleccionado ? " selected" : "") + ">" + nombreCompleto + "</option>");
            }
            out.println("        </select>");
            out.println("    </div>");

            out.println("    <div class='mb-3'>");
            out.println("        <label class='form-label'>Estado</label>");
            out.println("        <select class='form-select' name='estado' required>");
            out.println("            <option value=''>Seleccione un estado</option>");
            for (Estado estado : Estado.values()) {
                boolean seleccionado = esEdicion && carritoAEditar.getEstado() == estado;
                out.println("        <option value='" + estado.name() + "'" + (seleccionado ? " selected" : "") + ">" + estado.name() + "</option>");
            }
            out.println("        </select>");
            out.println("    </div>");

            if (esEdicion) {
                out.println("                    <div class='mb-3'>");
                out.println("                        <label class='form-label'>Fecha de Creación</label>");
                String fechaParaInput = (esEdicion && carritoAEditar.getFechaCreacion() != null) ? carritoAEditar.getFechaCreacion().toString().substring(0, 16) : "";
                out.println("                        <input type='datetime-local' class='form-control' name='fechaCreacion' required value='" + fechaParaInput + "'>");
                out.println("                    </div>");
            }

            out.println("                    <div class='mt-4 mb-5'>");
            out.println("                        <button type='submit' class='btn " + (esEdicion ? "btn-warning" : "btn btn-save") + " me-2'>" + (esEdicion ? "Actualizar" : "Guardar") + " Carrito</button>");
            out.println("                        <a href='" + request.getContextPath() + "/mantenimiento/carrito/listar' class='btn btn-secondary'>Cancelar</a>");
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

        CarritoDAO dao = new CarritoDAO();
        Carrito carrito;
        int id;

        try {
            switch (accion) {
                case "/agregar":
                    if (request.getMethod().equalsIgnoreCase("GET")) {
                        mostrarFormulario(request, response);
                    } else {
                        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                        Cliente cliente = new ClienteDAO().buscarPorId(idCliente);

                        carrito = new Carrito(cliente);
                        carrito.setEstado(Estado.valueOf(request.getParameter("estado")));

                        dao.guardar(carrito);
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/carrito/listar");
                    }
                    break;
                case "/editar":
                    id = Integer.parseInt(request.getParameter("id"));
                    carrito = dao.buscarPorId(id);
                    if (carrito != null) {
                        request.setAttribute("carritoEditar", carrito);
                        mostrarFormulario(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/carrito/listar");
                    }
                    break;
                case "/actualizar":
                    id = Integer.parseInt(request.getParameter("id"));
                    carrito = dao.buscarPorId(id);
                    if (carrito != null) {
                        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                        Cliente cliente = new ClienteDAO().buscarPorId(idCliente);
                        carrito.setCliente(cliente);
                        carrito.setEstado(Estado.valueOf(request.getParameter("estado")));

                        String fechaCreacionStr = request.getParameter("fechaCreacion");
                        if (fechaCreacionStr != null && !fechaCreacionStr.isEmpty()) {
                            carrito.setFechaCreacion(LocalDateTime.parse(fechaCreacionStr));
                        }

                        dao.actualizar(carrito);
                    }
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/carrito/listar");
                    break;
                case "/desactivar":
                    id = Integer.parseInt(request.getParameter("id"));
                    carrito = dao.buscarPorId(id);
                    if (carrito != null) {
                        carrito.setEstado(Estado.INACTIVO);
                        dao.actualizar(carrito);
                    }
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/carrito/listar");
                    break;
                case "/activar":
                    id = Integer.parseInt(request.getParameter("id"));
                    carrito = dao.buscarPorId(id);
                    if (carrito != null) {
                        carrito.setEstado(Estado.ACTIVO);
                        dao.actualizar(carrito);
                    }
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/carrito/listar");
                    break;
                case "/listar":
                default:
                    List<Carrito> listaCarritos = dao.listarTodos();
                    listaCarritos.sort(Comparator.comparing(Carrito::getEstado));
                    request.setAttribute("listaCarritos", listaCarritos);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/carrito.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(CarritoCRUDServlet.class.getName()).log(Level.SEVERE, "Error en el servlet", e);
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
        return "Servlet para CRUD de Carrito";
    }
}