package web.crud;

import dao.DetalleFacturaDAO;
import dao.FacturaDAO;
import dao.GorrasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.Gorras;

@WebServlet(name = "DetalleFacturaCRUDServlet", urlPatterns = {"/mantenimiento/detallefactura", "/mantenimiento/detallefactura/*"})
public class DetalleFacturaCRUDServlet extends HttpServlet {

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Factura> listaFacturas = new FacturaDAO().listarTodas();
        List<Gorras> listaGorras = new GorrasDAO().listarTodas();
        DetalleFactura detalleAEditar = (DetalleFactura) request.getAttribute("detalleEditar");
        boolean esEdicion = (detalleAEditar != null);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>" + (esEdicion ? "Editar" : "Agregar") + " Detalle de Factura</title>");
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
            out.println("            <h3 class='text-center mb-4'>Formulario para " + (esEdicion ? "editar un" : "agregar un") + " Detalle de Factura</h3>");
            out.println("            </div>");
            out.println("            <div class='card-body'>");

            String actionUrl = esEdicion ? request.getContextPath() + "/mantenimiento/detallefactura/actualizar" : request.getContextPath() + "/mantenimiento/detallefactura/agregar";
            out.println("                <form method='post' action='" + actionUrl + "'>");

            if (esEdicion) {
                out.println("<input type='hidden' name='id' value='" + detalleAEditar.getIdDetalleFactura() + "'>");
            }

            out.println("    <div class='mb-3'>");
            out.println("        <label class='form-label'>Factura</label>");
            out.println("        <select class='form-select' name='idFactura' required>");
            out.println("            <option value=''>Seleccione una factura</option>");
            for (Factura factura : listaFacturas) {
                int id = factura.getIdFactura();
                String clienteNombre = (factura.getCliente() != null) ? factura.getCliente().getNombre() : "N/A";
                boolean seleccionado = esEdicion && detalleAEditar.getFactura() != null && detalleAEditar.getFactura().getIdFactura() == id;
                out.println("            <option value='" + id + "'" + (seleccionado ? " selected" : "") + ">ID Factura: " + id + " (Cliente: " + clienteNombre + ")</option>");
            }
            out.println("        </select>");
            out.println("    </div>");

            out.println("    <div class='mb-3'>");
            out.println("        <label class='form-label'>Gorra (Producto)</label>");
            out.println("        <select class='form-select' name='idGorra' required>");
            out.println("            <option value=''>Seleccione una gorra</option>");
            for (Gorras gorra : listaGorras) {
                int id = gorra.getIdGorra();
                String gorraInfo = gorra.getTipo() + " - " + gorra.getMarca();
                boolean seleccionado = esEdicion && detalleAEditar.getGorra() != null && detalleAEditar.getGorra().getIdGorra() == id;
                out.println("            <option value='" + id + "'" + (seleccionado ? " selected" : "") + ">" + gorraInfo + "</option>");
            }
            out.println("        </select>");
            out.println("    </div>");

            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Cantidad</label>");
            out.println("                        <input type='number' class='form-control' name='cantidad' required value='" + (esEdicion ? detalleAEditar.getCantidad() : "") + "'>");
            out.println("                    </div>");

            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Precio de Venta</label>");
            out.println("                        <input type='number' step='0.01' class='form-control' name='precioVenta' required value='" + (esEdicion && detalleAEditar.getPrecioVenta() != null ? detalleAEditar.getPrecioVenta() : "") + "'>");
            out.println("                    </div>");

            out.println("                    <div class='mt-4 mb-5'>");
            out.println("                        <button type='submit' class='btn " + (esEdicion ? "btn-warning" : "btn btn-save") + " me-2'>" + (esEdicion ? "Actualizar" : "Guardar") + " Detalle</button>");
            out.println("                        <a href='" + request.getContextPath() + "/mantenimiento/detallefactura/listar' class='btn btn-secondary'>Cancelar</a>");
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

        DetalleFacturaDAO dao = new DetalleFacturaDAO();
        DetalleFactura detalle;
        int id;

        try {
            switch (accion) {
                case "/agregar":
                    if (request.getMethod().equalsIgnoreCase("GET")) {
                        mostrarFormulario(request, response);
                    } else {
                        int idFactura = Integer.parseInt(request.getParameter("idFactura"));
                        int idGorra = Integer.parseInt(request.getParameter("idGorra"));
                        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                        BigDecimal precioVenta = new BigDecimal(request.getParameter("precioVenta"));

                        Factura factura = new FacturaDAO().buscarPorId(idFactura);
                        Gorras gorra = new GorrasDAO().buscarPorId(idGorra);

                        detalle = new DetalleFactura(factura, gorra, cantidad, precioVenta);
                        dao.guardar(detalle);
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/detallefactura/listar");
                    }
                    break;
                case "/editar":
                    id = Integer.parseInt(request.getParameter("id"));
                    detalle = dao.buscarPorId(id);
                    if (detalle != null) {
                        request.setAttribute("detalleEditar", detalle);
                        mostrarFormulario(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/detallefactura/listar");
                    }
                    break;
                case "/actualizar":
                    id = Integer.parseInt(request.getParameter("id"));
                    detalle = dao.buscarPorId(id);
                    if (detalle != null) {
                        int idFactura = Integer.parseInt(request.getParameter("idFactura"));
                        int idGorra = Integer.parseInt(request.getParameter("idGorra"));
                        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                        BigDecimal precioVenta = new BigDecimal(request.getParameter("precioVenta"));

                        Factura factura = new FacturaDAO().buscarPorId(idFactura);
                        Gorras gorra = new GorrasDAO().buscarPorId(idGorra);

                        detalle.setFactura(factura);
                        detalle.setGorra(gorra);
                        detalle.setCantidad(cantidad);
                        detalle.setPrecioVenta(precioVenta);

                        dao.actualizar(detalle);
                    }
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/detallefactura/listar");
                    break;
                case "/eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/detallefactura/listar");
                    break;
                case "/listar":
                default:
                    List<DetalleFactura> listaDetalles = dao.listarTodos();
                    request.setAttribute("listaDetalleFactura", listaDetalles);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/detalleFactura.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(DetalleFacturaCRUDServlet.class.getName()).log(Level.SEVERE, "Error en el servlet", e);
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
        return "Servlet para CRUD de DetalleFactura";
    }
}
