<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Nosotros - KINCAPS</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/misionvision.css" />
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon">   
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg navbar-dark bg-kincaps px-4">
            <a class="navbar-brand fw-bold text-white d-flex align-items-center" href="${pageContext.request.contextPath}/pages/mainmenu.jsp">
                <img src="${pageContext.request.contextPath}/img/Logo/logonobg.png" alt="Logo" style="width:8vh;" class="me-2"> KINCAPS
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarMain">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarMain">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/pages/mainmenu.jsp">
                            <i class="bi bi-house-door-fill me-1"></i> Inicio
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/pages/catalogo.jsp">
                            <i class="bi bi-bag-fill me-1"></i> Productos
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/pages/catalogo.jsp">
                            <i class="bi bi-tag-fill me-1"></i> Ofertas
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/pages/misionvision.jsp">
                            <i class="bi bi-people-fill me-1"></i> Sobre Nosotros
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/pages/contactanos.jsp"">
                            <i class="bi bi-envelope-fill me-1"></i> Contacto
                        </a>
                    </li>
                </ul>
                <div class="d-flex gap-2">
                    <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/logout">Bienvenido "NombreUsuario"</a>
                </div>
            </div>
        </nav>

        <main class="flex-grow-1 container py-4 d-flex align-items-center">
            <div class="row g-4">

                <div class="col-12 col-md-6">
                    <div class="bg-white rounded-3 p-4 shadow-sm h-100">
                        <h3 class="mb-3">MISIÓN</h3>
                        <p class="mb-0">
                            Nuestra misión es ofrecer productos y servicios de alta calidad que satisfagan las necesidades de nuestros clientes, promoviendo innovación, ética y compromiso con la comunidad.
                        </p>
                    </div>
                </div>

                <div class="col-12 col-md-6">
                    <div class="bg-white rounded-3 p-4 shadow-sm h-100">
                        <h3 class="mb-3">VISIÓN</h3>
                        <p class="mb-0">
                            Ser reconocidos como líderes en nuestro sector, inspirando confianza y generando un impacto positivo en la sociedad mediante prácticas sostenibles y excelencia operativa.
                        </p>
                    </div>
                </div>
            </div>
        </main>

        <footer class="bg-kincaps text-white text-center py-4 mt-5">
            <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
            <small>
                <a href="${pageContext.request.contextPath}/pages/politica.jsp" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                <a href="${pageContext.request.contextPath}/pages/terminos.jsp" class="text-white text-decoration-none">Términos y Condiciones</a>
            </small>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
