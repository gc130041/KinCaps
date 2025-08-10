<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Gorras" %>
<%@page import="java.util.List" %>
<%@page import="modelo.DetalleCarrito"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.RoundingMode"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catálogo | KINCAPS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <script src="${pageContext.request.contextPath}/scripts/catalogo.js" defer></script>
        <script src="${pageContext.request.contextPath}/scripts/carrito.js" defer></script>
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon"> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/catalogo.css"> 
    </head>

    <body class="d-flex flex-column min-vh-100" data-context-path="${pageContext.request.contextPath}">
        <header>
            <nav class="navbar navbar-expand-lg bg-header navbar-dark fixed-top">
                <div class="container-fluid">
                    <a href="${pageContext.request.contextPath}/gorras" class="navbar-brand fw-bold">
                        <img src="${pageContext.request.contextPath}/img/Logo/logotipo.png" style="width: 5vh;" alt="Logotipo.png"/> Catálogo de Gorras
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="menuNav">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/gorras/historial" type="button" class="btn btn-link text-white" style="text-decoration: none;">
                                    <i class="bi bi-clock-history"></i> Historial De Compras
                                </a>
                                <button type="button" class="btn btn-link text-white" style="text-decoration: none;" data-bs-toggle="modal" data-bs-target="#miModal">
                                    <i class="bi bi-cart2"></i> Carrito De Compras
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <main class="flex-grow-1" style="padding-top: 90px;">
            <div class="container-fluid">
                <div class="row">
                    <aside class="col-lg-3">
                        <div class="filtro-seccion bg-light p-3 rounded shadow-sm">
                            <h3 class="mb-4">Filtros</h3>
                            <form id="filter-form">
                                <div class="mb-4">
                                    <h5 class="fw-bold">Rango de Precio</h5>
                                    <div class="price-range-slider">
                                        <div class="price-values mb-3 d-flex justify-content-between">
                                            <span id="range1-value">Min. Q0</span>
                                            <span id="range2-value">Max. Q1000</span>
                                        </div>
                                        <div class="slider-container">
                                            <div class="slider-track"></div>
                                            <input type="range" class="form-range" min="0" max="1000" value="0" id="slider-1">
                                            <input type="range" class="form-range" min="0" max="1000" value="1000" id="slider-2">
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-4">
                                    <h5 class="fw-bold">Categorías</h5>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="URBANA" id="catUrbana">
                                        <label class="form-check-label" for="catUrbana">Urbana</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="DEPORTIVA" id="catDeportiva">
                                        <label class="form-check-label" for="catDeportiva">Deportiva</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="FORMULA_1" id="catF1">
                                        <label class="form-check-label" for="catF1">Formula 1</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="OTRO" id="catOtras">
                                        <label class="form-check-label" for="catOtras">Otras</label>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </aside>

                    <div class="col-lg-9">
                        <div class="row g-4" id="product-container">
                            <%
                                List<Gorras> listaGorras = (List<Gorras>) request.getAttribute("listaGorras");
                                if (listaGorras != null && !listaGorras.isEmpty()) {
                                    for (Gorras g : listaGorras) {
                                        String folderPath = "otros";
                                        String tipoEnumName = "OTRO";
                                        String insigniaURL = "";
                                        String insigniaClass = "tipo-NY";
                                        String insigniaWidth = "40px";

                                        if (g.getTipo() != null) {
                                            tipoEnumName = g.getTipo().name();
                                            switch (g.getTipo()) {
                                                case URBANA:
                                                    folderPath = "urbano";
                                                    insigniaURL = request.getContextPath() + "/img/tipo/urbana.png";
                                                    insigniaClass = "tipo-NY";
                                                    insigniaWidth = "30px";
                                                    break;
                                                case DEPORTIVA:
                                                    folderPath = "deportivo";
                                                    insigniaURL = request.getContextPath() + "/img/tipo/deportiva.png";
                                                    insigniaClass = "tipo-MB";
                                                    insigniaWidth = "30px";
                                                    break;
                                                case FORMULA_1:
                                                    folderPath = "f1";
                                                    insigniaURL = request.getContextPath() + "/img/tipo/f1.png";
                                                    insigniaClass = "tipo-f1";
                                                    insigniaWidth = "60px";
                                                    break;
                                                case OTRO:
                                                    folderPath = "otros";
                                                    break;
                                            }
                                        }
                                        String imagenNombre = (g.getImagen() != null && !g.getImagen().isEmpty()) ? g.getImagen() : "default.png";
                                        String descripcionTexto = (g.getDescripcion() != null) ? g.getDescripcion() : "Sin descripción disponible.";
                                        String marcaTexto = (g.getMarca() != null) ? g.getMarca() : "";
                                        String nombreGorraTexto = (g.getNombreGorra() != null) ? g.getNombreGorra() : "Gorra sin nombre";
                                        java.math.BigDecimal precioValor = (g.getPrecio() != null) ? g.getPrecio() : java.math.BigDecimal.ZERO;
                                        String imageURL = request.getContextPath() + "/img/Gorras/" + folderPath + "/" + imagenNombre;
                            %>
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4 product-item" 
                                 data-price="<%= precioValor%>" 
                                 data-category="<%= tipoEnumName%>" 
                                 data-brand="<%= marcaTexto%>">
                                <div class="card h-100 shadow-sm">
                                    <% if (!insigniaURL.isEmpty()) {%>
                                    <span class="insignia-tipo <%= insigniaClass%>">    
                                        <img src="<%= insigniaURL%>" alt="<%= tipoEnumName%>" style="width: <%= insigniaWidth%>;">
                                    </span>
                                    <% }%>
                                    <img src="<%= imageURL%>" class="card-img-top" alt="<%= descripcionTexto%>">
                                    <div class="card-body d-flex flex-column">
                                        <div class="d-flex justify-content-between align-items-start mb-2">
                                            <h5 class="card-title mb-0 me-2"><%= nombreGorraTexto%></h5>
                                            <span class="badge bg-primary text-nowrap fs-6">Q<%= precioValor%></span>
                                        </div>
                                        <p class="card-text text-muted small"><%= descripcionTexto%></p>
                                        <a href="${pageContext.request.contextPath}/gorras/detalle?id=<%= g.getIdGorra()%>" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <%
                                }
                            } else {
                            %>
                            <div class="col-12">
                                <p class="text-center">No hay productos para mostrar en este momento.</p>
                            </div>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <div class="modal fade" id="miModal" tabindex="-1" aria-labelledby="miModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content h-100">
                    <div class="modal-header">
                        <h5 class="modal-title" id="miModalLabel"><strong>Carrito de Compras</strong></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body d-flex flex-column">
                        <div id="carrito-items" class="flex-grow-1 overflow-auto">
                            <%
                                List<DetalleCarrito> listaDetalleCarrito = (List<DetalleCarrito>) request.getAttribute("listaDetalleCarrito");
                                BigDecimal totalCarrito = BigDecimal.ZERO;

                                if (listaDetalleCarrito != null && !listaDetalleCarrito.isEmpty()) {
                                    for (DetalleCarrito detalle : listaDetalleCarrito) {
                                        Gorras gorra = detalle.getGorra();
                                        String folderPath = "otros";
                                        if (gorra.getTipo() != null) {
                                            switch (gorra.getTipo()) {
                                                case URBANA:
                                                    folderPath = "urbano";
                                                    break;
                                                case DEPORTIVA:
                                                    folderPath = "deportivo";
                                                    break;
                                                case FORMULA_1:
                                                    folderPath = "f1";
                                                    break;
                                            }
                                        }
                                        String imageURL = request.getContextPath() + "/img/Gorras/" + folderPath + "/" + gorra.getImagen();
                                        BigDecimal subtotal = detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad()));
                                        totalCarrito = totalCarrito.add(subtotal);
                            %>
                            <div class="d-flex align-items-center mb-3" data-id-gorra="<%= gorra.getIdGorra()%>">
                                <img src="<%= imageURL%>" alt="<%= gorra.getNombreGorra()%>" style="width: 60px; height: 60px; object-fit: cover; border-radius: 5px;">
                                <div class="ms-3 flex-grow-1">
                                    <h6 class="mb-0"><%= gorra.getNombreGorra()%></h6>
                                    <small class="text-muted item-cantidad">Cantidad: <%= detalle.getCantidad()%></small>
                                </div>
                                <div class="d-flex align-items-center">
                                    <strong class="me-3">Q<%= detalle.getPrecioUnitario().setScale(2, RoundingMode.HALF_UP) + " ℆"%></strong>
                                    <button class="btn btn-outline-danger btn-sm rounded-circle btn-eliminar-item" 
                                            data-id-gorra="<%= gorra.getIdGorra()%>"
                                            data-nombre-gorra="<%= gorra.getNombreGorra()%>"
                                            data-cantidad-actual="<%= detalle.getCantidad()%>"
                                            title="Eliminar item">
                                        <i class="bi bi-trash3"></i>
                                    </button>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>
                        <p id="carrito-vacio" class="text-center text-muted mt-4" style="
                           <%= (listaDetalleCarrito != null && !listaDetalleCarrito.isEmpty()) ? "display: none;" : ""%>">
                            Tu carrito está vacío.
                        </p>
                    </div>
                    <div class="modal-footer">
                        <div class="w-100">
                            <div class="text-end mb-3">
                                <h5>Total: <strong id="carrito-total">
                                        Q<%= totalCarrito.setScale(2, RoundingMode.HALF_UP)%>
                                    </strong></h5>
                            </div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Seguir Comprando</button>
                                <a href="${pageContext.request.contextPath}/gorras/checkout" class="btn btn-primary">Ir a Pagar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <div class="modal fade" id="eliminarItemModal" tabindex="-1" aria-labelledby="eliminarItemModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="eliminarItemModalLabel">Eliminar Producto</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        <p>¿Cuántos items de <strong id="nombre-item-eliminar">Gorra Genérica</strong> deseas eliminar?</p>
                        <div class="d-flex justify-content-center my-3">
                            <input type="number" class="form-control" value="1" min="1" id="eliminar-cantidad-input" style="width: 100px; text-align: center;">
                        </div>
                        <input type="hidden" id="eliminar-id-gorra-input">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-danger" id="confirmar-eliminacion-btn">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>

        <footer class="bg-header text-white text-center py-4 mt-auto">
            <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
            <small>
                <a href="${pageContext.request.contextPath}/politica" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                <a href="${pageContext.request.contextPath}/terminos" class="text-white text-decoration-none">Términos y Condiciones</a>
            </small>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html> 