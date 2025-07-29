/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.EmpleadoDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import modelo.Empleado;


/**
 *
 * @author informatica
 */
@WebServlet(name = "ServletCRUDEmpleado", urlPatterns = {"/mantenimiento/empleado", "/mantenimiento/empleado/*"})
public class ServletCRUDEmpleado extends HttpServlet {

     private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        
        Empleado empleadoAEditar = (Empleado) request.getAttribute("gorraEditar");
        boolean esEdicion = (empleadoAEditar != null);

        

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>" + (esEdicion ? "Editar" : "Agregar") + " Empleado</title>"); // cambiar;p
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
            out.println("            <h3 class='text-center mb-4'>Formulario para " + (esEdicion ? "editar una" : "agregar una") + " Empleado</h3>"); // cambiarlo
            out.println("            </div>");
            out.println("            <div class='card-body'>");

            // La acción del formulario cambia. Si es edición, envía a 'actualizarDatos' con el ID.
            if (esEdicion) {
                out.println("                <form method='post' action='ServletCrudEmpleado?accion=actualizarDatos&id=" + empleadoAEditar.getIdEmpleado() + "'>"); // cabiarlo
            } else {
                out.println("                <form method='post' action='ServletCrudEmpleado?accion=agregar'>"); // cambiarlo
            }

            // --- Campo Modelo ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Nombre</label>");
            // Si es edición, se rellena el valor. Si no, se deja vacío.
            out.println("                        <input type='text' class='form-control' name='nombre' required value='" + (esEdicion ? empleadoAEditar.getNombre() : "") + "'>"); // cambiarlo
            out.println("                    </div>");

            // --- Campo Marca ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Apellido</label>");
            out.println("                        <input type='text' class='form-control' name='apellido' required value='" + (esEdicion ? empleadoAEditar.getApellido() : "") + "'>"); // cambiar;p
            out.println("                    </div>");

            // --- Campo Color ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Email</label>");
            out.println("                        <input type='text' class='form-control' name='email' required value='" + (esEdicion ? empleadoAEditar.getEmail() : "") + "'>"); // cambiarlo
            out.println("                    </div>");

            // --- Campo Precio ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>telefono</label>");
            out.println("                        <input type='number' step='0.01' class='form-control' name='telefono' required value='" + (esEdicion ? empleadoAEditar.getTelefono() : "") + "'>"); // cambiarlo
            out.println("                    </div>");

            // --- Campo Precio ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>direccion</label>");
            out.println("                        <input type='number' step='0.01' class='form-control' name='direccion' required value='" + (esEdicion ? empleadoAEditar.getDireccion() : "") + "'>"); // cambiarlo
            out.println("                    </div>");

            // --- Campo Stock ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>contrasena</label>");
            out.println("                        <input type='number' class='form-control' name='contrasenaHash' required value='" + (esEdicion ? empleadoAEditar.getContrasenaHash() : "") + "'>"); //cambiarlo
            out.println("                    </div>");

            // --- Campo Stock ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>puesto</label>");
            out.println("                        <input type='number' class='form-control' name='puesto' required value='" + (esEdicion ? empleadoAEditar.getPuesto() : "") + "'>"); //cambiarlo
            out.println("                    </div>");

            // --- Campo Stock ---
            out.println("                    <div class='mb-3'>");
            out.println("                        <label class='form-label'>Fecha</label>");
            out.println("                        <input type='number' class='form-control' name='fechaContratacion' required value='" + (esEdicion ? empleadoAEditar.getFechaContratacion() : "") + "'>"); //cambiarlo
            out.println("                    </div>");

            // llave foranea empleado no tiene lo dejo por si acaso
            /*// --- Campo ID Proveedor ---
            out.println("    <div class='mb-3'>");
            out.println("        <label class='form-label'>Proveedor</label>");
            out.println("        <select class='form-select' name='idProveedor' required>");

            out.println("            <option value=''>Seleccione un proveedor</option>");

            for (Proveedor proveedor : listaProveedores) {
                int id = proveedor.getIdProveedor();
                String nombre = proveedor.getNombre();
                boolean seleccionado = esEdicion && empleadoAEditar.getProveedor() != null && empleadoAEditar.getProveedor().getIdProveedor() == id;

                out.println("            <option value='" + id + "'" + (seleccionado ? " selected" : "") + ">" + nombre + "</option>");
            }

            out.println("        </select>");
            out.println("    </div>");
             */
            // El texto del botón también cambia
            out.println("                    <div class='mt-4 mb-5'>");
            out.println("                        <button type='submit' class='btn " + (esEdicion ? "btn-warning" : "btn btn-save") + " me-2'>" + (esEdicion ? "Actualizar" : "Guardar") + " Empleado</button>"); //cambiarlo
            out.println("                        <a href='ServletCrudEmpleado?accion=listar' class='btn btn-secondary'>Cancelar</a>");
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

        // Si no se especifica una acción, por defecto se muestra el formulario para agregar.
       String accion = getAccion(request);

        EmpleadoDAO dao = new EmpleadoDAO();
        Empleado empleado;
        /*
        ProveedorDAO proveedorDao;
        Proveedor proveedor;
*/
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
                    String puesto = request.getParameter("puesto");
                        /*
                        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
                        proveedorDao = new ProveedorDAO();
                        proveedor = proveedorDao.buscarPorId(idProveedor);
                        */
                        LocalDate fechaContratacion = LocalDate.parse(request.getParameter("fechaContratacion"));
                        
                        
                    empleado = new Empleado(nombre, apellido, email, telefono, direccion, contrasenaHash, puesto, fechaContratacion);  
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/empleado/listar");
                    }
                    break;
                case "/editar": 
                    id = Integer.parseInt(request.getParameter("id"));
                    empleado = dao.buscarPorId(id);
                    if (empleado != null) {
                        request.setAttribute("gorraEditar", empleado);
                        mostrarFormulario(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/mantenimiento/empleado/listar");
                    }
                    break;
                case "/actualizar": 
                    id = Integer.parseInt(request.getParameter("id"));
                    empleado = dao.buscarPorId(id);
                    if (empleado != null) {
                        empleado.setNombre(request.getParameter("nombre"));
                    empleado.setApellido(request.getParameter("apellido"));
                    empleado.setEmail(request.getParameter("email"));
                    empleado.setTelefono(request.getParameter("telefono"));
                    empleado.setDireccion(request.getParameter("direccion"));
                    empleado.setContrasenaHash(request.getParameter("contrasenaHash"));
                    empleado.setPuesto(request.getParameter("puesto"));

                    String fechaString = request.getParameter("fechaContratacion");
                    LocalDate fechaContratacion = LocalDate.parse(fechaString);
                    empleado.setFechaContratacion(fechaContratacion);
                    /*
                        proveedorDao = new ProveedorDAO();
                        proveedor = proveedorDao.buscarPorId(idProveedor);
                        gorra.setProveedor(proveedor);
                        */
                        dao.actualizar(empleado);
                    }
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/empleado/listar");
                    break;
                case "/eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect(request.getContextPath() + "/mantenimiento/empleado/listar");
                    break;
                case "/listar":
                default:
                    List<Empleado> listaEmpleado = dao.listarTodos();
                    request.setAttribute("listaEmpleado", listaEmpleado);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/empleados.jsp");
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
        return "Servlet para CRUD de Empleado";
    }
}