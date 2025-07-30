package web;

import dao.ClienteDAO;
import dao.EmpleadoDAO;
import dao.FacturaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import modelo.Empleado;
import modelo.Factura;
import modelo.Factura.MetodoPago;

@WebServlet(name = "FacturasCRUDServlet", urlPatterns = {"/mantenimiento/facturas", "/mantenimiento/facturas/*"})
public class FacturasCRUDServlet extends HttpServlet {

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Cliente> listaClientes = new ClienteDAO().listarTodos();
        List<Empleado> listaEmpleados = new EmpleadoDAO().listarTodos();
        Factura facturaAEditar = (Factura) request.getAttribute("facturaEditar");
        boolean esEdicion = (facturaAEditar != null);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>" + (esEdicion ? "Editar" : "Agregar") + " Factura</title>");
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
            out.println("                    <a class='nav-link' href='" + request.getContextPath() + "/pages/mainmenuadmin.jsp' role='button'>");
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
            out.println("            <h3 class='text-center mb-4'>Formulario para " + (esEdicion ? "editar una" : "agregar una") + " Factura</h3>");
            out.println("            </div>");
            out.println("            <div class='card-body'>");

            String actionUrl = esEdicion ? request.getContextPath() + "/mantenimiento/facturas/actualizar" : request.getContextPath() + "/mantenimiento/facturas/agregar";
            out.println("                <form method='post' action='" + actionUrl + "'>");

            if (esEdicion) {
                out.println("                    <input type='hidden' name='id' value='" + facturaAEditar.getIdFactura() + "'>");
            }

            out.println("    <div class='mb-3'>");
            out.println("        <label class='form-label'>Cliente</label>");
            out.println("        <select class='form-select' name='idCliente' required>");
            out.println("            <option value=''>Seleccione un cliente</option>");
            for (Cliente cliente : listaClientes) {
                int id = cliente.getIdCliente();
                String nombreCompleto = cliente.getNombre() + " " + cliente.getApellido();
                boolean seleccionado = esEdicion && facturaAEditar.getCliente() != null && facturaAEditar.getCliente().getIdCliente() == id;
                out.println("            <option value='" + id + "'" + (seleccionado ? " selected" : "") + ">" + nombreCompleto + "</option>");
            }
            out.println("        </select>");
            out.println("    </div>");

            out.println("    <div class='mb-3'>");
            out.println("        <label class='form-label'>Empleado</label>");
            out.println("        <select class='form-select' name='idEmpleado' required>");
            out.println("            <option value=''>Seleccione un empleado</option>");
            for (Empleado empleado : listaEmpleados) {
                int id = empleado.getIdEmpleado();
                String nombreCompleto = empleado.getNombre() + " " + empleado.getApellido();
                boolean seleccionado = esEdicion && facturaAEditar.getEmpleado() != null && facturaAEditar.getEmpleado().getIdEmpleado() == id;
                out.println("            <option value='" + id + "'" + (seleccionado ? " selected" : "") + ">" + nombreCompleto + "</option>");
            }
            out.println("        </select>");
            out.println("    </div>");

            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Total</label>");
            out.println("                        <input type='number' step='0.01' class='form-control' name='total' required value='" + (esEdicion ? facturaAEditar.getTotal() : "") + "'>");
            out.println("                    </div>");

            out.println("    <div class='mb-3'>");
            out.println("        <label class='form-label'>Método de Pago</label>");
            out.println("        <select class='form-select' name='metodoPago' required>");
            out.println("            <option value=''>Seleccione un método de pago</option>");
            for (Factura.MetodoPago metodo : Factura.MetodoPago.values()) {
                boolean seleccionado = esEdicion && facturaAEditar.getMetodoPago() == metodo;
                out.println("            <option value='" + metodo.name() + "'" + (seleccionado ? " selected" : "") + ">" + metodo.name().replace("_", " ") + "</option>");
            }
            out.println("        </select>");
            out.println("    </div>");
            
            if (esEdicion) {
                out.println("                    <div class='mb-3'>");
                out.println("                        <label class='form-label'>Fecha de Emisión</label>");
                String fechaParaInput = (esEdicion && facturaAEditar.getFechaEmision() != null) ? facturaAEditar.getFechaEmision().toString().substring(0, 16) : "";
                out.println("                        <input type='datetime-local' class='form-control' name='fechaEmision' required value='" + fechaParaInput + "'>");
                out.println("                    </div>");
            }

            out.println("                    <div class='mt-4 mb-5'>");
            out.println("                        <button type='submit' class='btn " + (esEdicion ? "btn-warning" : "btn btn-save") + " me-2'>" + (esEdicion ? "Actualizar" : "Guardar") + " Factura</button>");
            out.println("                        <a href='" + request.getContextPath() + "/mantenimiento/facturas/listar' class='btn btn-secondary'>Cancelar</a>");
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

        FacturaDAO dao = new FacturaDAO();
        Factura factura;
        int id;

        try {
            switch (accion) {
                case "/agregar":
                    if (request.getMethod().equalsIgnoreCase("GET")) {
                        mostrarFormulario(request, response);
                    } else {
                        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                        Cliente cliente = new ClienteDAO().buscarPorId(idCliente);
                        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
                        Empleado empleado = new EmpleadoDAO().buscarPorId(idEmpleado);
                        BigDecimal total = new BigDecimal(request.getParameter("total"));
                        MetodoPago metodoPago = Factura.MetodoPago.valueOf(request.getParameter("metodoPago"));
                        
                        factura = new Factura(cliente, empleado, total, metodoPago);
                        dao.guardar(factura);
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/facturas/listar");
                    }
                    break;
                case "/editar":
                    id = Integer.parseInt(request.getParameter("id"));
                    factura = dao.buscarPorId(id);
                    if (factura != null) {
                        request.setAttribute("facturaEditar", factura);
                        mostrarFormulario(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/facturas/listar");
                    }
                    break;
                case "/actualizar":
                    id = Integer.parseInt(request.getParameter("id"));
                    factura = dao.buscarPorId(id);
                    if (factura != null) {
                        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                        Cliente cliente = new ClienteDAO().buscarPorId(idCliente);
                        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
                        Empleado empleado = new EmpleadoDAO().buscarPorId(idEmpleado);

                        factura.setCliente(cliente);
                        factura.setEmpleado(empleado);
                        factura.setTotal(new BigDecimal(request.getParameter("total")));
                        factura.setMetodoPago(Factura.MetodoPago.valueOf(request.getParameter("metodoPago")));
                        factura.setFechaEmision(LocalDateTime.parse(request.getParameter("fechaEmision")));
                        
                        dao.actualizar(factura);
                    }
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/facturas/listar");
                    break;
                case "/eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/facturas/listar");
                    break;
                case "/listar":
                default:
                    List<Factura> listaFacturas = dao.listarTodas();
                    request.setAttribute("listaFacturas", listaFacturas);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/factura.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(FacturasCRUDServlet.class.getName()).log(Level.SEVERE, "Error en el servlet", e);
            throw new ServletException("Ocurrió un error en la aplicación", e);
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
        return "Servlet para CRUD de Facturas";
    }
}
