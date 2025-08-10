package web.carrito;

import dao.CarritoDAO;
import dao.DetalleCarritoDAO;
import dao.GorrasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
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

@WebServlet(name = "DetalleGorraServlet", urlPatterns = {"/gorras/detalle"})
public class DetalleGorraServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        int idGorra = 0;
        try {
            idGorra = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto inválido.");
            return;
        }

        GorrasDAO gorrasDAO = new GorrasDAO();
        Gorras gorra = gorrasDAO.buscarPorId(idGorra);
        
        int cantidadEnCarrito = 0;
        HttpSession session = request.getSession(false);
        List<DetalleCarrito> listaDetalleCarrito = new java.util.ArrayList<>();
        BigDecimal totalCarrito = BigDecimal.ZERO;

        if (session != null && session.getAttribute("usuario") instanceof Cliente) {
            Cliente cliente = (Cliente) session.getAttribute("usuario");
            CarritoDAO carritoDAO = new CarritoDAO();
            Carrito carritoActivo = carritoDAO.buscarActivoPorCliente(cliente);

            if (carritoActivo != null) {
                DetalleCarritoDAO detalleDAO = new DetalleCarritoDAO();
                listaDetalleCarrito = detalleDAO.buscarPorCarrito(carritoActivo);

                for (DetalleCarrito detalle : listaDetalleCarrito) {
                    if (detalle.getGorra().getIdGorra() == idGorra) {
                        cantidadEnCarrito = detalle.getCantidad();
                    }
                    BigDecimal subtotal = detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad()));
                    totalCarrito = totalCarrito.add(subtotal);
                }
            }
        }
        
        int stockDisponibleParaAgregar = (gorra != null) ? gorra.getStock() - cantidadEnCarrito : 0;

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            String contextPath = request.getContextPath();
            if (gorra != null) {
                out.println("<title>Detalle de " + gorra.getNombreGorra() + " | KINCAPS</title>");
            } else {
                out.println("<title>Producto no Encontrado | KINCAPS</title>");
            }
            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
            out.println("<link rel='icon' href='" + contextPath + "/img/Logo/logonobg.png' type='image/x-icon'>");
            out.println("<link rel='stylesheet' href='" + contextPath + "/style/catalogo.css'>");
            out.println("<script src='" + contextPath + "/scripts/carrito.js' defer></script>");
            out.println("<style>");
            out.println("body { padding-top: 90px; }");
            out.println(".detalle-container { background-color: white; padding: 2rem; border-radius: 15px; box-shadow: 0 8px 25px rgba(0,0,0,0.1); }");
            out.println(".detalle-img { max-width: 100%; height: auto; border-radius: 10px; }");
            out.println(".detalle-precio { font-size: 2.5rem; color: #0f86d4; font-weight: bold; }");
            out.println(".btn-agregar-carrito { background-color: #002e71; color: white; font-weight: bold; padding: 12px 30px; border-radius: 50px; transition: all 0.3s ease; }");
            out.println(".btn-agregar-carrito:hover { background-color: #0f86d4; transform: scale(1.05); }");
            out.println(".stock-disponible { color: #28a745; font-weight: bold; }");
            out.println(".stock-agotado { color: #dc3545; font-weight: bold; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body class='d-flex flex-column min-vh-100' data-context-path='" + contextPath + "'>");
            out.println("<header>");
            out.println("<nav class='navbar navbar-expand-lg bg-header navbar-dark fixed-top'>");
            out.println("<div class='container-fluid'>");
            out.println("<a href='" + contextPath + "/gorras' class='navbar-brand fw-bold'>");
            out.println("<img src='" + contextPath + "/img/Logo/logotipo.png' style='width: 5vh;' alt='Logotipo.png'/> Detalle del Producto");
            out.println("</a>");
            out.println("<a href='" + contextPath + "/gorras/catalogo' class='btn btn-outline-light'>Volver al Catálogo</a>");
            out.println("</div></nav>");
            out.println("</header>");

            out.println("<main class='flex-grow-1 py-5'>");
            out.println("<div class='container'>");

            if (gorra != null) {
                String folderPath = "otros";
                if (gorra.getTipo() != null) {
                    switch (gorra.getTipo()) {
                        case URBANA: folderPath = "Urbanos"; break;
                        case DEPORTIVA: folderPath = "deportivo"; break;
                        case FORMULA_1: folderPath = "f1"; break;
                        case OTRO: folderPath = "otros"; break;
                    }
                }
                String imageURL = contextPath + "/img/Gorras/" + folderPath + "/" + gorra.getImagen();

                out.println("<div class='detalle-container'>");
                out.println("<div class='row align-items-center g-5'>");
                out.println("<div class='col-md-6 text-center'>");
                out.println("<img src='" + imageURL + "' class='detalle-img' alt='" + gorra.getDescripcion() + "'>");
                out.println("</div>");
                out.println("<div class='col-md-6'>");
                out.println("<h1 class='fw-bold'>" + gorra.getNombreGorra() + "</h1>");
                out.println("<h3 class='text-muted'>" + gorra.getMarca() + "</h3>");
                out.println("<p class='lead my-4'>" + gorra.getDescripcion() + "</p>");
                out.println("<div class='d-flex align-items-center justify-content-between my-4'>");
                out.println("<span class='detalle-precio'>Q" + gorra.getPrecio().toPlainString() + "</span>");
                out.println("<span class='stock-disponible' id='stock-display'>");
                if (stockDisponibleParaAgregar > 0) {
                    out.println("¡Disponible! (" + stockDisponibleParaAgregar + " en stock)");
                } else {
                    out.println("No hay más unidades disponibles.");
                }
                out.println("</span>");
                out.println("</div>");
                out.println("<p><strong>Color:</strong> " + gorra.getColor() + "</p>");
                out.println("<p><strong>Categoría:</strong> " + gorra.getTipo().name() + "</p>");

                if (stockDisponibleParaAgregar > 0) {
                    out.println("<div class='d-flex justify-content-center my-4'>");
                    out.println("<input type='number' class='form-control' value='1' min='1' max='" + stockDisponibleParaAgregar + "' id='cantidad-input' style='width: 100px; text-align: center;'>");
                    out.println("</div>");
                    out.println("<button type='button' class='btn btn-agregar-carrito w-100 mt-2' data-id='" + gorra.getIdGorra() + "'> <i class='bi bi-cart-plus'></i> Agregar al Carrito</button>");
                } else {
                    out.println("<div class='alert alert-warning text-center mt-4'>Ya tienes todo el stock disponible en tu carrito.</div>");
                }
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
            } else {
                out.println("<div class='text-center'>");
                out.println("<h2>Oops... Producto no encontrado</h2>");
                out.println("<p>La gorra que buscas no existe o no está disponible.</p>");
                out.println("<a href='" + contextPath + "/gorras' class='btn btn-primary'>Volver al Catálogo</a>");
                out.println("</div>");
            }
            out.println("</div>");
            out.println("</main>");

            out.println("<div class='modal fade' id='miModal' tabindex='-1' aria-labelledby='miModalLabel' aria-hidden='true'>");
            out.println("<div class='modal-dialog'>");
            out.println("<div class='modal-content h-100'>");
            out.println("<div class='modal-header'>");
            out.println("<h5 class='modal-title' id='miModalLabel'><strong>Carrito de Compras</strong></h5>");
            out.println("<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Cerrar'></button>");
            out.println("</div>");
            out.println("<div class='modal-body d-flex flex-column'>");
            out.println("<div id='carrito-items' class='flex-grow-1 overflow-auto'>");
            if (!listaDetalleCarrito.isEmpty()) {
                for (DetalleCarrito detalle : listaDetalleCarrito) {
                    Gorras itemGorra = detalle.getGorra();
                    String folderPath = "otros";
                    if (itemGorra.getTipo() != null) {
                        switch (itemGorra.getTipo()) {
                            case URBANA: folderPath = "Urbanos"; break;
                            case DEPORTIVA: folderPath = "deportivo"; break;
                            case FORMULA_1: folderPath = "f1"; break;
                        }
                    }
                    String imageURL = request.getContextPath() + "/img/Gorras/" + folderPath + "/" + itemGorra.getImagen();
                    out.println("<div class='d-flex align-items-center mb-3' data-id-gorra='" + itemGorra.getIdGorra() + "'>");
                    out.println("<img src='" + imageURL + "' alt='" + itemGorra.getNombreGorra() + "' style='width: 60px; height: 60px; object-fit: cover; border-radius: 5px;'>");
                    out.println("<div class='ms-3 flex-grow-1'><h6 class='mb-0'>" + itemGorra.getNombreGorra() + "</h6><small class='text-muted item-cantidad'>Cantidad: " + detalle.getCantidad() + "</small></div>");
                    out.println("<div class='d-flex align-items-center'><strong class='me-3'>Q" + detalle.getPrecioUnitario().setScale(2, RoundingMode.HALF_UP) + "</strong><button class='btn btn-outline-danger btn-sm rounded-circle btn-eliminar-item' data-id-gorra='" + itemGorra.getIdGorra() + "' data-nombre-gorra='" + itemGorra.getNombreGorra() + "' data-cantidad-actual='" + detalle.getCantidad() + "' title='Eliminar item'><i class='bi bi-trash3'></i></button></div>");
                    out.println("</div>");
                }
            }
            out.println("</div>");
            out.println("<p id='carrito-vacio' class='text-center text-muted mt-4' style='" + (!listaDetalleCarrito.isEmpty() ? "display: none;" : "") + "'>Tu carrito está vacío.</p>");
            out.println("</div>");
            out.println("<div class='modal-footer'><div class='w-100'><div class='text-end mb-3'><h5>Total: <strong id='carrito-total'>Q" + totalCarrito.setScale(2, RoundingMode.HALF_UP) + "</strong></h5></div><div class='d-flex justify-content-between'><button type='button' class='btn btn-secondary' data-bs-dismiss='modal'>Seguir Comprando</button><button type='button' class='btn btn-primary'>Ir a Pagar</button></div></div></div>");
            out.println("</div></div></div>");

            out.println("<footer class='bg-header text-white text-center py-4 mt-auto'>");
            out.println("<p class='mb-1'>2025 KINCAPS. Todos los derechos reservados.</p>");
            out.println("<small><a href='" + contextPath + "/pages/politica.jsp' class='text-white text-decoration-none me-3'>Política de Privacidad</a> <a href='" + contextPath + "/pages/terminos.jsp' class='text-white text-decoration-none'>Términos y Condiciones</a></small>");
            out.println("</footer>");

            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para mostrar el detalle de una gorra específica.";
    }
}