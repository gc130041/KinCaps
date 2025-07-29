/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;
import dao.EmpleadoDAO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author pc
 */
@WebServlet(name = "ServletEditarEmpleado", urlPatterns = {"/ServletEditarEmpleado"})
public class ServletEditarEmpleado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpleadoDAO dao = new EmpleadoDAO();
        String accion = request.getParameter("accion");

        switch (accion) {
            case "editar":
                //dao --> usuario(id) -> redireccion con el cliente -> editarCliente.jsp

                int idEditar = Integer.parseInt(request.getParameter("id"));
                Empleado empleado = dao.buscarPorId(idEditar);

                request.setAttribute("editarEmpleado", empleado);
                request.getRequestDispatcher("editarEmpleado.jsp").forward(request, response);

                break;
            case "actualizar":
                int idActualizar = Integer.parseInt(request.getParameter("id"));
                empleado = dao.buscarPorId(idActualizar);
                empleado.setNombre(request.getParameter("nombre"));
                empleado.setApellido(request.getParameter("apellido"));
                empleado.setEmail(request.getParameter("email"));
                empleado.setTelefono(request.getParameter("telefono"));
                empleado.setDireccion(request.getParameter("direccion"));
                empleado.setContrasenaHash(request.getParameter("contrasena"));
                empleado.setPuesto(request.getParameter("puesto"));

                String fechaStr = request.getParameter("fechaContratacion");
                try {
                    java.time.LocalDate fechaContratacion = java.time.LocalDate.parse(fechaStr);
                    empleado.setFechaContratacion(fechaContratacion);
                } catch (Exception e) {
                   
                    empleado.setFechaContratacion(java.time.LocalDate.now());
                }

                dao.actualizar(empleado);
                response.sendRedirect("ServletListarEmpleado");
                break;
            default:
                List<Empleado> listaEmpleado = dao.listarTodas();

                request.setAttribute("empleados", listaEmpleado);
                request.getRequestDispatcher("empleados.jsp").forward(request, response);

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


