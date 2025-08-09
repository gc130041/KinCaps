<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Gorras" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catálogo | KINCAPS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <script src="${pageContext.request.contextPath}/scripts/catalogo.js" defer></script>
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon"> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/catalogo.css"> 
    </head>

    <body class="d-flex flex-column min-vh-100">
        <header>
            <nav class="navbar navbar-expand-lg bg-header navbar-dark fixed-top">
                <div class="container-fluid">
                    <a href="${pageContext.request.contextPath}/pages/mainmenu.jsp" class="navbar-brand fw-bold">
                        <img src="${pageContext.request.contextPath}/img/Logo/logotipo.png" style="width: 5vh;" alt="Logotipo.png"/> Catálogo de Gorras
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="menuNav">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <button type="button" class="btn btn-link text-white" data-bs-toggle="modal" data-bs-target="#miModal">
                                    <i class="bi bi-cart2"></i> Carrito De Compras
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <main class="flex-grow-1" style="padding-top: 70px;">
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
                        <div class="row mt-4" id="product-container">
                            <%
                                List<Gorras> listaGorras = (List<Gorras>) request.getAttribute("listaGorras");
                                if (listaGorras != null && !listaGorras.isEmpty()) {
                                    for (Gorras g : listaGorras) {
                                        String folderPath = "otros";
                                        String tipoEnumName = "OTRO";
                                        String insigniaURL = "";
                                        String insigniaClass = "tipo-NY"; 
                                        
                                        if (g.getTipo() != null) {
                                            tipoEnumName = g.getTipo().name();
                                            switch (g.getTipo()) {
                                                case URBANA:
                                                    folderPath = "Urbanos";
                                                    insigniaURL = request.getContextPath() + "/img/tipo/urbana.png";
                                                    insigniaClass = "tipo-NY";
                                                    break;
                                                case DEPORTIVA:
                                                    folderPath = "deportivo";
                                                    insigniaClass = "tipo-MB";
                                                    break;
                                                case FORMULA_1:
                                                    folderPath = "f1";
                                                    insigniaURL = request.getContextPath() + "/img/tipo/f1.png";
                                                    insigniaClass = "tipo-f1";
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
                                 data-price="<%= precioValor %>" 
                                 data-category="<%= tipoEnumName %>" 
                                 data-brand="<%= marcaTexto %>">
                                <div class="card h-100 shadow-sm position-relative">
                                    <% if (!insigniaURL.isEmpty()) { %>
                                        <span class="insignia-tipo <%= insigniaClass %>"><img src="<%= insigniaURL %>" alt="<%= tipoEnumName %>" style="width: 60px;"></span>
                                    <% } %>
                                    <img src="<%= imageURL %>" class="card-img-top" alt="<%= descripcionTexto %>">
                                    <div class="card-body d-flex flex-column">
                                        <div class="d-flex justify-content-between align-items-start mb-2">
                                            <h5 class="card-title mb-0 me-2"><%= nombreGorraTexto %></h5>
                                            <span class="badge bg-primary text-nowrap fs-6">Q<%= precioValor %></span>
                                        </div>
                                        <p class="card-text text-muted small"><%= descripcionTexto %></p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
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
                        <div class="flex-grow-1 overflow-auto">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="w-100">
                            <div class="text-end mb-3">
                                <h5>Total: <strong>Q0.00</strong></h5>
                            </div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Seguir Comprando</button>
                                <button type="button" class="btn btn-primary">Ir a Pagar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    
        <footer class="bg-header text-white text-center py-4 mt-auto">
            <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
            <small>
                <a href="${pageContext.request.contextPath}/pages/politica.jsp" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                <a href="${pageContext.request.contextPath}/pages/terminos.jsp" class="text-white text-decoration-none">Términos y Condiciones</a>
            </small>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html> 