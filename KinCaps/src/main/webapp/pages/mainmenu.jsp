<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Menu Principal - KINCAPS</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/mainmenu.css" />
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon">   
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <script src="${pageContext.request.contextPath}/scripts/logout.js" defer></script>
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg navbar-dark bg-kincaps px-4">
            <a class="navbar-brand fw-bold text-white d-flex align-items-center" href="${pageContext.request.contextPath}/gorras">
                <img src="${pageContext.request.contextPath}/img/Logo/logonobg.png" alt="Logo" style="width:8vh;" class="me-2"> KINCAPS
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarMain">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarMain">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/gorras">
                            <i class="bi bi-house-door-fill me-1"></i> Inicio
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/gorras/catalogo">
                            <i class="bi bi-bag-fill me-1"></i> Productos
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/gorras/catalogo">
                            <i class="bi bi-tag-fill me-1"></i> Ofertas
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/gorras/mision">
                            <i class="bi bi-people-fill me-1"></i> Sobre Nosotros
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/gorras/contactanos">
                            <i class="bi bi-envelope-fill me-1"></i> Contacto
                        </a>
                    </li>
                </ul>
                <div class="d-flex gap-2">
                    <a id="logout-link" class="btn btn-outline-light" href="${pageContext.request.contextPath}/logout">
                        Bienvenido ${usuarioCompleto.nombre} ${usuarioCompleto.apellido}
                    </a>
                </div>
            </div>
        </nav>

        <main class="flex-grow-1">
            <header class="hero-section d-flex align-items-center justify-content-center text-center text-white">
                <div>
                    <h1 class="display-4 fw-bold">Bienvenido a KINCAPS</h1>
                    <p class="lead">Tu tienda virtual favorita de gorras urbanas y deportivas</p>
                    <a href="${pageContext.request.contextPath}/gorras/catalogo" class="btn btn-light btn-lg text-kincaps fw-bold mt-3">Explorar Catálogo</a>
                </div>
            </header>

            <section class="container py-5">
                <h2 class="text-center mb-4 text-kincaps fw-bold">Gorras Destacadas</h2>
                <div class="row g-4">
                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="card h-100 shadow-sm">
                            <span class="insignia-tipo tipo-f1"> 
                                <img src="${pageContext.request.contextPath}/img/tipo/f1.png" alt="F1" style="width: 60px;">
                            </span>
                            <img src="${pageContext.request.contextPath}/img/gorra1.png" class="card-img-top" alt="Gorra 1">
                            <div class="card-body text-center">
                                <h5 class="card-title">Gorra Deportiva</h5>
                                <p class="card-text text-muted">Gorra de Fórmula 1, Piloto Max Verstappen, año 2025, equipo Red Bull Racing.</p>
                                <a href="${pageContext.request.contextPath}/gorras/catalogo" class="btn btn-outline-primary">Ver más</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="card h-100 shadow-sm">
                            <span class="insignia-tipo tipo-toyota"> 
                                <img src="${pageContext.request.contextPath}/img/toyota_logo_white.png" alt="TOYOTA" style="width: 60px;">
                            </span>
                            <img src="${pageContext.request.contextPath}/img/gorra2.png" class="card-img-top" alt="Gorra 2">
                            <div class="card-body text-center">
                                <h5 class="card-title">Gorra Deportiva</h5>
                                <p class="card-text text-muted">Gorra de Deportiva Toyota, Kiichiro Toyoda , año 2025, equipo Toyota Racing.</p>
                                <a href="${pageContext.request.contextPath}/gorras/catalogo" class="btn btn-outline-primary">Ver más</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="card h-100 shadow-sm">
                            <span class="insignia-tipo tipo-NY"> 
                                <img src="${pageContext.request.contextPath}/img/tipo/urbana.png" alt="NY" style="width: 30px;">
                            </span>
                            <img src="${pageContext.request.contextPath}/img/gorra3.png" class="card-img-top"  style="margin-top: 10vh; padding-left: 2vh; padding-right: 2vh; padding-bottom: 3.7vh;" alt="Gorra 3">
                            <div class="card-body text-center">
                                <h5 class="card-title">Gorra Urbana</h5>
                                <p class="card-text text-muted">Gorra de Urbana NY, New Era New York, año 2025, Estilo Nuevo y Lucido.</p>
                                <a href="${pageContext.request.contextPath}/gorras/catalogo" class="btn btn-outline-primary">Ver más</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="card h-100 shadow-sm">
                            <span class="insignia-tipo tipo-f1"> 
                                <img src="${pageContext.request.contextPath}/img/tipo/f1.png" alt="MB" style="width: 60px;">
                            </span>
                            <img src="${pageContext.request.contextPath}/img/gorra4.png" class="card-img-top" alt="Gorra 3">
                            <div class="card-body text-center">
                                <h5 class="card-title">Gorra Deportiva</h5>
                                <p class="card-text text-muted">Gorra de Deportiva Mercedez Benz, Carl Benz , año 2025, equipo Mercedez Racing.</p>
                                <a href="${pageContext.request.contextPath}/gorras/catalogo" class="btn btn-outline-primary">Ver más</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <footer class="bg-kincaps text-white text-center py-4 mt-5">
            <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
            <small>
                <a href="${pageContext.request.contextPath}/politica" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                <a href="${pageContext.request.contextPath}/terminos" class="text-white text-decoration-none">Términos y Condiciones</a>
            </small>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>