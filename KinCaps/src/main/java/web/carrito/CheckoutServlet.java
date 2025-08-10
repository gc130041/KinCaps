package web.carrito;

import dao.CarritoDAO;
import dao.DetalleCarritoDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Cliente;
import modelo.DetalleCarrito;
import util.JPAUtil;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/gorras/checkout"})
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("usuario") instanceof Cliente)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Cliente clienteEnSesion = (Cliente) session.getAttribute("usuario");

        EntityManager em = JPAUtil.getEntityManager();
        try {
            Cliente clienteCompleto = em.find(Cliente.class, clienteEnSesion.getIdCliente());
            if (clienteCompleto == null) {
                throw new ServletException("El cliente de la sesión no fue encontrado en la base de datos.");
            }

            List<Carrito> carritos = em.createQuery("SELECT c FROM Carrito c WHERE c.cliente = :cliente AND c.estado = :estado", Carrito.class)
                    .setParameter("cliente", clienteCompleto)
                    .setParameter("estado", Carrito.Estado.ACTIVO)
                    .getResultList();

            if (carritos.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/gorras");
                return;
            }
            Carrito carritoActivo = carritos.get(0);

            List<DetalleCarrito> listaDetalleCarrito = em.createQuery(
                    "SELECT d FROM DetalleCarrito d JOIN FETCH d.gorra WHERE d.carrito = :carrito",
                    DetalleCarrito.class)
                    .setParameter("carrito", carritoActivo)
                    .getResultList();

            if (listaDetalleCarrito.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/gorras");
                return;
            }

            BigDecimal totalCarrito = BigDecimal.ZERO;
            for (DetalleCarrito detalle : listaDetalleCarrito) {
                totalCarrito = totalCarrito.add(detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad())));
            }

            request.setAttribute("cliente", clienteCompleto);
            request.setAttribute("listaDetalleCarrito", listaDetalleCarrito);
            request.setAttribute("totalCarrito", totalCarrito);

            request.getRequestDispatcher("/pages/checkout.jsp").forward(request, response);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
