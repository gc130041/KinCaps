package web.carrito;

import com.google.gson.Gson;
import dao.CarritoDAO;
import dao.DetalleCarritoDAO;
import dao.GorrasDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Cliente;
import modelo.DetalleCarrito;
import modelo.Gorras;
import modelo.Usuario;

@WebServlet(name = "EliminarCarritoServlet", urlPatterns = {"/carrito/eliminar"})
public class EliminarCarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> jsonResponse = new HashMap<>();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jsonResponse.put("message", "No has iniciado sesión.");
            response.getWriter().write(new Gson().toJson(jsonResponse));
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (!(usuario instanceof Cliente)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(new Gson().toJson(jsonResponse));
            return;
        }

        try {
            int idGorra = Integer.parseInt(request.getParameter("idGorra"));
            int cantidadAEliminar = Integer.parseInt(request.getParameter("cantidad"));
            
            Cliente cliente = (Cliente) usuario;
            CarritoDAO carritoDAO = new CarritoDAO();
            DetalleCarritoDAO detalleDAO = new DetalleCarritoDAO();

            Carrito carrito = carritoDAO.buscarActivoPorCliente(cliente);
            if (carrito == null) {
                throw new ServletException("No se encontró un carrito activo para este cliente.");
            }
            
            GorrasDAO gorraDAO = new GorrasDAO();
            Gorras gorra = gorraDAO.buscarPorId(idGorra);

            DetalleCarrito detalle = detalleDAO.buscarPorCarritoYGorra(carrito, gorra);

            if (detalle != null) {
                int cantidadActual = detalle.getCantidad();
                int nuevaCantidad = cantidadActual - cantidadAEliminar;

                if (nuevaCantidad <= 0) {
                    detalleDAO.eliminar(detalle.getIdDetalleCarrito());
                    jsonResponse.put("action", "deleted");
                } else {
                    detalle.setCantidad(nuevaCantidad);
                    detalleDAO.actualizar(detalle);
                    jsonResponse.put("action", "updated");
                }

                jsonResponse.put("status", "success");
                jsonResponse.put("message", "Item(s) eliminado(s) correctamente.");
                jsonResponse.put("idGorra", idGorra);
                jsonResponse.put("nuevaCantidad", nuevaCantidad > 0 ? nuevaCantidad : 0);
                jsonResponse.put("precioUnitario", detalle.getPrecioUnitario().doubleValue());
                jsonResponse.put("cantidadEliminada", cantidadAEliminar);

            } else {
                 throw new ServletException("El item a eliminar no existe en el carrito.");
            }
            
            response.getWriter().write(new Gson().toJson(jsonResponse));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.put("message", "Error en el servidor: " + e.getMessage());
            response.getWriter().write(new Gson().toJson(jsonResponse));
            e.printStackTrace();
        }
    }
}