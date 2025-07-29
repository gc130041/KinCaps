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

/**
 * Servlet para gestionar las operaciones CRUD (Crear, Leer, Actualizar,
 * Eliminar) de Gorras.
 *
 * @author olive
 */
@WebServlet(name = "ServletCrudGorras", urlPatterns = {"/ServletCrudGorras"})
public class ServletCrudGorras extends HttpServlet {

    /**
     * Muestra un formulario dinámico para agregar o editar una gorra. Si se
     * está editando, los campos del formulario se rellenan con los datos
     * existentes.
     *
     * @param request La petición HTTP, usada para obtener la gorra a editar.
     * @param response La respuesta HTTP, usada para escribir el HTML.
     * @throws IOException Si ocurre un error de E/S.
     */
    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Intentar obtener la gorra que se va a editar desde los atributos del request.
        // Será null si estamos creando una nueva gorra.
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
            out.println("                    <a class='nav-link dropdown-toggle' href='" + request.getContextPath() + "/pages/mainmenuadmin.jsp' role='button' aria-expanded='false'>");
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
            // El encabezado también cambia
            out.println("            <h3 class='text-center mb-4'>Formulario para " + (esEdicion ? "editar una" : "agregar una") + " Gorra</h3>");
            out.println("            </div>");
            out.println("            <div class='card-body'>");

            // La acción del formulario cambia. Si es edición, envía a 'actualizarDatos' con el ID.
            if (esEdicion) {
                out.println("                <form method='post' action='ServletCrudGorras?accion=actualizarDatos&id=" + gorraAEditar.getIdGorra() + "'>");
            } else {
                out.println("                <form method='post' action='ServletCrudGorras?accion=agregar'>");
            }

            // --- Campo Modelo ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Modelo</label>");
            // Si es edición, se rellena el valor. Si no, se deja vacío.
            out.println("                        <input type='text' class='form-control' name='modelo' required value='" + (esEdicion ? gorraAEditar.getModelo() : "") + "'>");
            out.println("                    </div>");

            // --- Campo Marca ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Marca</label>");
            out.println("                        <input type='text' class='form-control' name='marca' required value='" + (esEdicion ? gorraAEditar.getMarca() : "") + "'>");
            out.println("                    </div>");

            // --- Campo Color ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Color</label>");
            out.println("                        <input type='text' class='form-control' name='color' required value='" + (esEdicion ? gorraAEditar.getColor() : "") + "'>");
            out.println("                    </div>");

            // --- Campo Precio ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Precio</label>");
            out.println("                        <input type='number' step='0.01' class='form-control' name='precio' required value='" + (esEdicion ? gorraAEditar.getPrecio() : "") + "'>");
            out.println("                    </div>");

            // --- Campo Stock ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Stock</label>");
            out.println("                        <input type='number' class='form-control' name='stock' required value='" + (esEdicion ? gorraAEditar.getStock() : "") + "'>");
            out.println("                    </div>");

            // --- Campo ID Proveedor ---
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

            // El texto del botón también cambia
            out.println("                    <div class='mt-4 mb-5'>");
            out.println("                        <button type='submit' class='btn " + (esEdicion ? "btn-warning" : "btn btn-save") + " me-2'>" + (esEdicion ? "Actualizar" : "Guardar") + " Gorra</button>");
            out.println("                        <a href='ServletCrudGorras?accion=listar' class='btn btn-secondary'>Cancelar</a>");
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Si no se especifica una acción, por defecto se muestra el formulario para agregar.
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "mostrarAgregar";
        }

        GorrasDAO dao = new GorrasDAO();
        // Se declaran las variables fuera del switch para un código más limpio.
        Gorras gorra = null;
        ProveedorDAO proveedorDao = null;
        Proveedor proveedor = null;
        int id;

        try {
            switch (accion) {
                case "mostrarAgregar":
                    // Simplemente llamamos a mostrarFormulario sin poner ningún atributo.
                    mostrarFormulario(request, response);
                    break;

                case "mostrarEditar":
                    id = Integer.parseInt(request.getParameter("id"));
                    gorra = dao.buscarPorId(id);
                    // Ponemos la gorra en el request para que mostrarFormulario la pueda usar.
                    request.setAttribute("gorraEditar", gorra);
                    mostrarFormulario(request, response);
                    break;

                case "actualizarDatos":
                    id = Integer.parseInt(request.getParameter("id"));
                    gorra = dao.buscarPorId(id);

                    // Actualizamos el objeto gorra con los datos del formulario
                    gorra.setModelo(request.getParameter("modelo"));
                    gorra.setMarca(request.getParameter("marca"));
                    gorra.setColor(request.getParameter("color"));
                    gorra.setPrecio(new BigDecimal(request.getParameter("precio")));
                    gorra.setStock(Integer.parseInt(request.getParameter("stock")));

                    int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
                    proveedorDao = new ProveedorDAO();
                    proveedor = proveedorDao.buscarPorId(idProveedor);
                    gorra.setProveedor(proveedor);

                    dao.actualizar(gorra); // Suponiendo que tienes un método actualizar en tu DAO

                    // Redirigimos a la lista para ver los cambios.
                    response.sendRedirect("ServletCrudGorras?accion=listar");
                    break;

                case "agregar":
                    String modelo = request.getParameter("modelo");
                    String marca = request.getParameter("marca");
                    String color = request.getParameter("color");
                    BigDecimal precio = new BigDecimal(request.getParameter("precio"));
                    int stock = Integer.parseInt(request.getParameter("stock"));
                    idProveedor = Integer.parseInt(request.getParameter("idProveedor"));

                    proveedorDao = new ProveedorDAO();
                    proveedor = proveedorDao.buscarPorId(idProveedor);

                    if (proveedor == null) {
                        throw new IllegalArgumentException("El proveedor con ID " + idProveedor + " no fue encontrado.");
                    }

                    gorra = new Gorras(modelo, marca, color, precio, stock, proveedor);
                    dao.guardar(gorra);

                    // Después de agregar, redirigimos para evitar reenvíos del formulario.
                    response.sendRedirect("ServletCrudGorras?accion=listar");
                    break;

                case "eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect("ServletCrudGorras?accion=listar");
                    break;

                case "listar":
                    // Aquí iría la lógica para obtener la lista de gorras y mostrarla en un JSP.
                    // Por ahora, solo un mensaje.
                    List<Gorras> listaGorras = dao.listarTodas();
                    request.setAttribute("listaGorras", listaGorras);
                    request.getRequestDispatcher("pages/productos.jsp").forward(request, response);
                    break;

                default:
                    // Si la acción no es reconocida, mostramos el formulario de agregar.
                    response.sendRedirect("ServletCrudGorras?accion=listar");
                    break;
            }
        } catch (Exception e) {
            // Manejo básico de errores
            Logger.getLogger(ServletCrudGorras.class.getName()).log(Level.SEVERE, "Error en el servlet", e);
            throw new ServletException("Ocurrió un error en la aplicación.", e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    }// </editor-fold>
}
