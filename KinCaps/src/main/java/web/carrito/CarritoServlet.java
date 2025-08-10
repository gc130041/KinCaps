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

@WebServlet(name = "CarritoServlet", urlPatterns = {"/carrito/agregar"})
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> jsonResponse = new HashMap<>();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "No has iniciado sesión.");
            response.getWriter().write(new Gson().toJson(jsonResponse));
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (!(usuario instanceof Cliente)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Acción no permitida para este tipo de usuario.");
            response.getWriter().write(new Gson().toJson(jsonResponse));
            return;
        }

        Cliente cliente = (Cliente) usuario;

        try {
            int idGorra = Integer.parseInt(request.getParameter("idGorra"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            if (cantidad <= 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "La cantidad debe ser un número positivo.");
                response.getWriter().write(new Gson().toJson(jsonResponse));
                return;
            }

            GorrasDAO gorrasDAO = new GorrasDAO();
            Gorras gorra = gorrasDAO.buscarPorId(idGorra);

            if (gorra == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                jsonResponse.put("message", "Producto no encontrado.");
                response.getWriter().write(new Gson().toJson(jsonResponse));
                return;
            }
            
            DetalleCarritoDAO detalleDAO = new DetalleCarritoDAO();
            CarritoDAO carritoDAO = new CarritoDAO();
            Carrito carrito = carritoDAO.buscarActivoPorCliente(cliente);
             if (carrito == null) {
                carrito = new Carrito(cliente);
                carritoDAO.guardar(carrito);
            }

            DetalleCarrito detalleExistente = detalleDAO.buscarPorCarritoYGorra(carrito, gorra);
            int cantidadEnCarrito = (detalleExistente != null) ? detalleExistente.getCantidad() : 0;

            if (gorra.getStock() < cantidadEnCarrito + cantidad) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "No hay stock suficiente para la cantidad solicitada.");
                response.getWriter().write(new Gson().toJson(jsonResponse));
                return;
            }

            int cantidadTotalEnCarrito;
            if (detalleExistente != null) {
                detalleExistente.setCantidad(detalleExistente.getCantidad() + cantidad);
                detalleDAO.actualizar(detalleExistente);
                cantidadTotalEnCarrito = detalleExistente.getCantidad();
            } else {
                DetalleCarrito nuevoDetalle = new DetalleCarrito(carrito, gorra, cantidad, gorra.getPrecio());
                detalleDAO.guardar(nuevoDetalle);
                cantidadTotalEnCarrito = cantidad;
            }

            int stockRestante = gorra.getStock() - cantidadTotalEnCarrito;

            jsonResponse.put("status", "success");
            jsonResponse.put("message", "Producto agregado correctamente.");

            Map<String, Object> productoMap = new HashMap<>();
            productoMap.put("idGorra", gorra.getIdGorra());
            productoMap.put("nombre", gorra.getNombreGorra());
            productoMap.put("precio", gorra.getPrecio().doubleValue());
            productoMap.put("imagen", obtenerUrlImagen(gorra, request.getContextPath()));
            productoMap.put("cantidadAgregada", cantidad);
            productoMap.put("cantidadTotalEnCarrito", cantidadTotalEnCarrito);
            productoMap.put("stockRestante", stockRestante);

            jsonResponse.put("producto", productoMap);
            response.getWriter().write(new Gson().toJson(jsonResponse));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Ocurrió un error inesperado en el servidor.");
            response.getWriter().write(new Gson().toJson(jsonResponse));
            e.printStackTrace();
        }
    }

    private String obtenerUrlImagen(Gorras g, String contextPath) {
        String folderPath = "otros";
        if (g.getTipo() != null) {
            switch (g.getTipo()) {
                case URBANA:
                    folderPath = "Urbanos";
                    break;
                case DEPORTIVA:
                    folderPath = "deportivo";
                    break;
                case FORMULA_1:
                    folderPath = "f1";
                    break;
                case OTRO:
                    folderPath = "otros";
                    break;
            }
        }
        String imagenNombre = (g.getImagen() != null && !g.getImagen().isEmpty()) ? g.getImagen() : "default.png";
        return contextPath + "/img/Gorras/" + folderPath + "/" + imagenNombre;
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejar la adición de productos al carrito de compras.";
    }
}