<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catálogo | KINCAPS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
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
                            <form>
                                <div class="mb-4">
                                    <label for="price-limit" class="form-label fw-bold">Límite de Precio</label>
                                    <input type="range" class="form-range" min="0" max="1000" step="10" id="price-limit" oninput="document.getElementById('priceOutput').value = `Q${this.value}.00`">
                                    <output id="priceOutput" class="form-text">Q500.00</output>
                                </div>
                                <div class="mb-4">
                                    <h5 class="fw-bold">Categorías</h5>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="catUrbana">
                                        <label class="form-check-label" for="catUrbana">Urbana</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="catDeportiva">
                                        <label class="form-check-label" for="catDeportiva">Deportiva</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="catF1">
                                        <label class="form-check-label" for="catF1">Formula 1</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="catOtras">
                                        <label class="form-check-label" for="catOtras">Otras</label>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary w-100">Aplicar Filtros</button>
                            </form>
                        </div>
                    </aside>

                    <div class="col-lg-9">
                        <div class="row mt-4">
                            <!-- Gorras (Productos) -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra1.png" class="card-img-top" alt="Gorra 1">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Azul de Los Angeles Dodgers, Año 1988.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra2.png" class="card-img-top" alt="Gorra 2">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de los Yankees de Nueva York, Año 2000.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->        
                            <!-- Repetir más tarjetas si es necesario -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra3.png" class="card-img-top" alt="Gorra 3">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra4.png" class="card-img-top" alt="Gorra 4">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra5.png" class="card-img-top" alt="Gorra 5">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra6.png" class="card-img-top" alt="Gorra 6">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra7.webp" class="card-img-top" alt="Gorra 7">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra8.webp" class="card-img-top" alt="Gorra 8">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra9.png" class="card-img-top" alt="Gorra 9">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra10.png" class="card-img-top" alt="Gorra 10">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra11.png" class="card-img-top" alt="Gorra 11">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra12.webp" class="card-img-top" alt="Gorra 12">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra13.webp" class="card-img-top" alt="Gorra 13">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra14.jpg" class="card-img-top" alt="Gorra 15">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra15.webp" class="card-img-top" alt="Gorra 15">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra16.webp" class="card-img-top" alt="Gorra 16">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra17.avif" class="card-img-top" alt="Gorra 17">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra18.jpg" class="card-img-top" alt="Gorra 18">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de Los Angeles Dodgers.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra19.jpg" class="card-img-top" alt="Gorra 19">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Marrón de los San Diego Padres, Año 2020. </p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Inicio de la tarjeta -->
                            <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <span class="insignia-tipo tipo-NY"><img src="${pageContext.request.contextPath}/img/NY_logo_white.png" alt="New Era" style="width: 35px;"></span>
                                    <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra20.webp" class="card-img-top" alt="Gorra 20">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">Gorra Urbana</h5>
                                        <p class="card-text text-muted">Gorra New Era Negra de los New York Mets Año, 1954.</p>
                                        <a href="#" class="btn btn-outline-primary mt-auto">Ver más</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin de la tarjeta -->
                            <!-- Division -->
                            <hr>
                            <h2>Formula 1</h2>
                            <!-- Inicio de la tarjeta -->

                        </div>
                    </div>
                </div>
            </div>
        </main>

        <footer class="bg-header text-white text-center py-3 mt-auto">
            <div class="container">
                <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
                <div>
                    <a href="#" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                    <a href="#" class="text-white text-decoration-none">Términos y Condiciones</a>
                </div>
            </div>
        </footer>

        <div class="modal fade" id="miModal" tabindex="-1" aria-labelledby="miModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content h-100">
                    <div class="modal-header">
                        <h5 class="modal-title" id="miModalLabel"><strong>Carrito de Compras</strong></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body d-flex flex-column">
                        <div class="flex-grow-1 overflow-auto">
                            <!-- Productos del carrito -->
                            <div class="d-flex align-items-center mb-3 border-bottom pb-2">
                                <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra1.png" alt="Gorra 1" style="width: 90px; height: 90px; border-radius: 4px; margin-right: 15px;">
                                <div style="width: 100%; padding: 0; margin: 0;">
                                    <h6 class="mb-1">Gorra Urbana Azul</h6>
                                    <div class="d-flex align-items-center" style="width: 100%; padding: 0; margin: 0;">
                                        <strong>Q000.00</strong>
                                        <span class="text-muted" style="margin-left: auto;">Cantidad: 0</span>
                                    </div>
                                </div>
                            </div>
                            <!-- Gorra 2 Comprada-->
                            <div class="d-flex align-items-center mb-3 border-bottom pb-2">
                                <img src="${pageContext.request.contextPath}/img/Gorras/Urbanos/gorra2.png" alt="Gorra 2" style="width: 90px; height: 90px; border-radius: 4px; margin-right: 15px;">
                                <div style="width: 100%; padding: 0; margin: 0;">
                                    <h6 class="mb-1">Gorra Urbana Negra</h6>
                                    <div class="d-flex align-items-center" style="width: 100%; padding: 0; margin: 0;">
                                        <strong>Q000.00</strong>
                                        <span class="text-muted" style="margin-left: auto;">Cantidad: 0</span>
                                    </div>
                                </div>
                            </div>
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
    </main>
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
