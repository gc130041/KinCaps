package web.carrito;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;
import util.JPAUtil;

@WebServlet(name = "ProcesarPagoServlet", urlPatterns = {"/gorras/procesar-pago"})
public class ProcesarPagoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("usuario") instanceof Cliente)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Cliente cliente = (Cliente) session.getAttribute("usuario");
        
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            
            Cliente managedCliente = em.merge(cliente);
            Empleado empleadoAsignado = em.find(Empleado.class, 1);
            if(empleadoAsignado == null){
                throw new ServletException("Empleado con ID 1 no encontrado. No se puede generar la factura.");
            }

            List<Carrito> carritos = em.createQuery("SELECT c FROM Carrito c WHERE c.cliente = :cliente AND c.estado = :estado", Carrito.class)
                    .setParameter("cliente", managedCliente)
                    .setParameter("estado", Carrito.Estado.ACTIVO)
                    .getResultList();

            if (carritos.isEmpty()) {
                throw new ServletException("No se encontró un carrito activo para este cliente.");
            }
            Carrito carritoActivo = carritos.get(0);

            List<DetalleCarrito> items = em.createQuery("SELECT d FROM DetalleCarrito d WHERE d.carrito = :carrito", DetalleCarrito.class)
                    .setParameter("carrito", carritoActivo)
                    .getResultList();
            
            BigDecimal totalReal = BigDecimal.ZERO;
            for (DetalleCarrito item : items) {
                totalReal = totalReal.add(item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad())));
            }

            Factura factura = new Factura();
            factura.setCliente(managedCliente);
            factura.setEmpleado(empleadoAsignado);
            factura.setTotal(totalReal);
            factura.setMetodoPago(Factura.MetodoPago.valueOf(request.getParameter("metodoPago")));
            em.persist(factura);

            for (DetalleCarrito item : items) {
                DetalleFactura detalleFactura = new DetalleFactura(factura, item.getGorra(), item.getCantidad(), item.getPrecioUnitario());
                em.persist(detalleFactura);
                
                Gorras gorra = em.find(Gorras.class, item.getGorra().getIdGorra());
                int nuevoStock = gorra.getStock() - item.getCantidad();
                if (nuevoStock < 0) {
                    throw new ServletException("Stock insuficiente para la gorra: " + gorra.getNombreGorra());
                }
                gorra.setStock(nuevoStock);
                em.merge(gorra);
            }

            carritoActivo.setEstado(Carrito.Estado.PAGADO);
            em.merge(carritoActivo);
            
            tx.commit();
            
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String contextPath = request.getContextPath();
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
                out.println("<title>¡Compra Exitosa! | KINCAPS</title>");
                out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
                out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css'>");
                out.println("<link rel='icon' href='" + contextPath + "/img/Logo/logonobg.png' type='image/x-icon'>");
                out.println("<link rel='stylesheet' href='" + contextPath + "/style/catalogo.css'>");
                out.println("</head>");
                out.println("<body class='d-flex flex-column min-vh-100'>");
                
                out.println("<header>");
                out.println("<nav class='navbar navbar-expand-lg bg-header navbar-dark'>");
                out.println("<div class='container-fluid'>");
                out.println("<a href='" + contextPath + "/gorras' class='navbar-brand fw-bold'>");
                out.println("<img src='" + contextPath + "/img/Logo/logotipo.png' style='width: 5vh;' alt='Logotipo.png'/> KINCAPS");
                out.println("</a>");
                out.println("</div></nav>");
                out.println("</header>");

                out.println("<main class='flex-grow-1 d-flex align-items-center justify-content-center text-center'>");
                out.println("<div class='container'>");
                out.println("<i class='bi bi-check-circle-fill text-success' style='font-size: 5rem;'></i>");
                out.println("<h1 class='mt-3'>¡Gracias por tu compra!</h1>");
                out.println("<p class='lead'>Tu pedido ha sido procesado exitosamente.</p>");
                out.println("<p>Tu número de factura es: <strong>#" + factura.getIdFactura() + "</strong></p>");
                out.println("<div class='mt-4'>");
                out.println("<a href='" + contextPath + "/gorras' class='btn btn-primary'>Seguir Comprando</a>");
                out.println("<a href='" + contextPath + "/pages/historialdecompras.jsp' class='btn btn-outline-secondary'>Ver Historial de Compras</a>");
                out.println("</div></div></main>");
                
                out.println("<footer class='bg-header text-white text-center py-4 mt-auto'>");
                out.println("<p class='mb-1'>2025 KINCAPS. Todos los derechos reservados.</p>");
                out.println("</footer>");
                
                out.println("</body></html>");
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/checkout?error=proceso_fallido");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}