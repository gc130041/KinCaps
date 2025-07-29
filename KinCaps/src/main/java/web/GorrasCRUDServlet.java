package web;

import dao.GorrasDAO;
import dao.ProveedorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Gorras;
import modelo.Proveedor;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "GorrasCRUDServlet", urlPatterns = {"/mantenimiento/gorras", "/mantenimiento/gorras/*"})
public class GorrasCRUDServlet extends HttpServlet {
    
    private String getSafeString(String value) {
        return value != null ? value : "";
    }
    
    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Proveedor> listaProveedores = new ProveedorDAO().listarTodos();
        Gorras gorraAEditar = (Gorras) request.getAttribute("gorraEditar");
        boolean esEdicion = (gorraAEditar != null);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>" + (esEdicion ? "Editar" : "Agregar") + " Gorra</title>");
            out.println("    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
            out.println("    <link rel='icon' href='" + request.getContextPath() + "/img/Logo/logonobg.png' type='image/x-icon'>");
            out.println("    <link rel='stylesheet' href='" + request.getContextPath() + "/style/tablas.css'>");
            out.println("</head>");
            out.println("<body class='bg-light'>");
            out.println("<nav class='navbar navbar-expand-lg bg-header navbar-dark'>");
            out.println("    <div class='container-fluid'>");
            out.println("        <a class='navbar-brand fw-bold'>Panel de Administración</a>");
            out.println("        <button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#menuNav'>");
            out.println("            <span class='navbar-toggler-icon'></span>");
            out.println("        </button>");
            out.println("        <div class='collapse navbar-collapse' id='menuNav'>");
            out.println("            <ul class='navbar-nav ms-auto'>");
            out.println("                <li class='nav-item dropdown'>");
            out.println("                    <a class='nav-link' href='" + request.getContextPath() + "/mantenimiento" + "' role='button'>");
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
            out.println("            <h3 class='text-center mb-4'>Formulario para " + (esEdicion ? "editar una" : "agregar una") + " Gorra</h3>");
            out.println("            </div>");
            out.println("            <div class='card-body'>");

            String actionUrl = esEdicion ? request.getContextPath() + "/mantenimiento/gorras/actualizar" : request.getContextPath() + "/mantenimiento/gorras/agregar";
            out.println("                <form method='post' action='" + actionUrl + "'>");
            
            if (esEdicion) {
                 out.println("<input type='hidden' name='id' value='" + gorraAEditar.getIdGorra() + "'>");
            }

            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Modelo</label>");
            out.println("                        <input type='text' class='form-control' name='modelo' required value='" + (esEdicion ? getSafeString(gorraAEditar.getModelo()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Marca</label>");
            out.println("                        <input type='text' class='form-control' name='marca' required value='" + (esEdicion ? getSafeString(gorraAEditar.getMarca()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Color</label>");
            out.println("                        <input type='text' class='form-control' name='color' required value='" + (esEdicion ? getSafeString(gorraAEditar.getColor()) : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Precio</label>");
            out.println("                        <input type='number' step='0.01' class='form-control' name='precio' required value='" + (esEdicion && gorraAEditar.getPrecio() != null ? gorraAEditar.getPrecio() : "") + "'>");
            out.println("                    </div>");
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Stock</label>");
            out.println("                        <input type='number' class='form-control' name='stock' required value='" + (esEdicion ? gorraAEditar.getStock() : "") + "'>");
            out.println("                    </div>");
            out.println("    <div class='mb-3'>");
            out.println("        <label class='form-label'>Proveedor</label>");
            out.println("        <select class='form-select' name='idProveedor' required>");
            out.println("            <option value=''>Seleccione un proveedor</option>");

            for (Proveedor proveedor : listaProveedores) {
                int id = proveedor.getIdProveedor();
                String nombre = proveedor.getNombre();
                boolean seleccionado = esEdicion && gorraAEditar.getProveedor() != null && gorraAEditar.getProveedor().getIdProveedor() == id;
                out.println("            <option value='" + id + "'" + (seleccionado ? " selected" : "") + ">" + nombre + "</option>");
            }

            out.println("        </select>");
            out.println("    </div>");
            out.println("                    <div class='mt-4 mb-5'>");
            out.println("                        <button type='submit' class='btn " + (esEdicion ? "btn-warning" : "btn btn-save") + " me-2'>" + (esEdicion ? "Actualizar" : "Guardar") + " Gorra</button>");
            out.println("                        <a href='" + request.getContextPath() + "/mantenimiento/gorras/listar' class='btn btn-secondary'>Cancelar</a>");
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
        
        String accion = getAccion(request);

        GorrasDAO dao = new GorrasDAO();
        Gorras gorra;
        ProveedorDAO proveedorDao;
        Proveedor proveedor;
        int id;

        try {
            switch (accion) {
                case "/agregar":
                    if (request.getMethod().equalsIgnoreCase("GET")) {
                        mostrarFormulario(request, response);
                    } else { 
                        String modelo = request.getParameter("modelo");
                        String marca = request.getParameter("marca");
                        String color = request.getParameter("color");
                        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
                        int stock = Integer.parseInt(request.getParameter("stock"));
                        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
                        proveedorDao = new ProveedorDAO();
                        proveedor = proveedorDao.buscarPorId(idProveedor);
                        gorra = new Gorras(modelo, marca, color, precio, stock, proveedor);
                        dao.guardar(gorra);
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/gorras/listar");
                    }
                    break;
                case "/editar": 
                    id = Integer.parseInt(request.getParameter("id"));
                    gorra = dao.buscarPorId(id);
                    if (gorra != null) {
                        request.setAttribute("gorraEditar", gorra);
                        mostrarFormulario(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/gorras/listar");
                    }
                    break;
                case "/actualizar": 
                    id = Integer.parseInt(request.getParameter("id"));
                    gorra = dao.buscarPorId(id);
                    if (gorra != null) {
                        gorra.setModelo(request.getParameter("modelo"));
                        gorra.setMarca(request.getParameter("marca"));
                        gorra.setColor(request.getParameter("color"));
                        gorra.setPrecio(new BigDecimal(request.getParameter("precio")));
                        gorra.setStock(Integer.parseInt(request.getParameter("stock")));
                        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
                        proveedorDao = new ProveedorDAO();
                        proveedor = proveedorDao.buscarPorId(idProveedor);
                        gorra.setProveedor(proveedor);
                        dao.actualizar(gorra);
                    }
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/gorras/listar");
                    break;
                case "/eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/gorras/listar");
                    break;
                case "/listar":
                default:
                    List<Gorras> listaGorras = dao.listarTodas();
                    request.setAttribute("listaGorras", listaGorras);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/productos.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(GorrasCRUDServlet.class.getName()).log(Level.SEVERE, "Error en el servlet", e);
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
        return "Servlet para CRUD de Gorras";
    }
}