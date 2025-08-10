<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Contactanos - KINCAPS</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/contactanos.css" />
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

        <main class="flex-grow-1 container container py-5 d-flex align-items-center justify-content-center">
            <div class="col-12 col-md-6">
                <div class="bg-white rounded-3 p-4 shadow-sm">
                    <h3 class="mb-3 text-center"><i class="bi bi-person-fill"></i> Contacto</h3>
                    <hr class="mb-4">
                    <p class="text-center mb-4">
                        Si tienes preguntas, comentarios o deseas más información sobre nuestros productos,
                        no dudes en comunicarte con nosotros. Estamos aquí para ayudarte.
                    </p>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item d-flex align-items-center">
                            <i class="bi bi-telephone-fill text-primary me-3 fs-5"></i>
                            <span class="fw-semibold">Teléfono:</span> +502 1234-5678
                        </li>
                        <li class="list-group-item d-flex align-items-center">
                            <i class="bi bi-envelope-fill text-primary me-3 fs-5"></i>
                            <span class="fw-semibold">Correo:</span> KINCAPS@kinal.edu.gt
                        </li>
                        <li class="list-group-item d-flex align-items-center">
                            <i class="bi bi-geo-alt-fill text-primary me-3 fs-5"></i>
                            <span class="fw-semibold">Dirección:</span> 6A Avenida 13-54, Ciudad de Guatemala 01007, Guatemala
                        </li>
                    </ul>
                </div>
            </div>
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
