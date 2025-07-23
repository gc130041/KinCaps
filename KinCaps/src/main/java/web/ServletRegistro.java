/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import modelo.Cliente;
import util.JPAUtil;
import util.PasswordHash;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombre = request.getParameter("name");
        String apellido = request.getParameter("lastname");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("ubicacion");
        String password = request.getParameter("password");
        String confirmarPassword = request.getParameter("confirmarpassword");

        // Validar que las contraseñas coincidan
        if (password == null || !password.equals(confirmarPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        EntityManager em = JPAUtil.getEntityManager();

        try {
            // Verificar si ya existe un cliente con el mismo email
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.email = :email", Cliente.class);
            query.setParameter("email", email);
            if (!query.getResultList().isEmpty()) {
                request.setAttribute("error", "El correo electrónico ya está registrado.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }

            // Hashear la contraseña
            String hashPassword = PasswordHash.contrasenaHash(password);

            // Crear un nuevo cliente
            Cliente cliente = new Cliente(nombre, apellido, email, telefono, direccion, hashPassword);

            // Guardar el cliente en la base de datos
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();

            // Redirigir al login con mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/index.jsp?registro=exito");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            request.setAttribute("error", "Error interno, por favor intente más tarde.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }
}