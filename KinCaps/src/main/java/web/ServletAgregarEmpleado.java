/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

/**
 *
 * @author pc
 */
import dao.EmpleadoDAO;
import modelo.Empleado;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "ServletAgregarEmpleado", urlPatterns = {"/ServletAgregarEmpleado"})
public class ServletAgregarEmpleado extends HttpServlet{
    //doGet y doPost
    
    protected void doPost(HttpServletRequest solicitud, HttpServletResponse respuesta) throws IOException, ServletException{
     String nombre = solicitud.getParameter("nombre");
    String apellido = solicitud.getParameter("apellido");
    String email = solicitud.getParameter("email"); 
    String telefono = solicitud.getParameter("telefono");
    String direccion = solicitud.getParameter("direccion");
    String contrasenaHash = solicitud.getParameter("contrasena");
    String puesto = solicitud.getParameter("puesto");
    String fechaContratacion = solicitud.getParameter("fechaContratacion");
        
        //Dao -> objeto ->persistirlo
        EmpleadoDAO dao = new EmpleadoDAO();
        Empleado empleado = new Empleado(nombre,apellido,email,telefono,direccion,contrasenaHash,puesto,fechaContratacion); 
        dao.guardar(empleado);
        
        //redirigir a /ServletListarClientes
        solicitud.getRequestDispatcher("/ServletAgregarEmpleado").forward(solicitud, respuesta);
    }

}
