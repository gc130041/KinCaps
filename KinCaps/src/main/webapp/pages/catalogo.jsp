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
        <nav class="navbar navbar-expand-lg bg-header navbar-dark">
            <div class="container-fluid">
                <a href="${pageContext.request.contextPath}/pages/mainmenu.jsp" style="font-size: 30px;" class="navbar-brand fw-bold">
                    <img src="${pageContext.request.contextPath}/img/Logo/logotipo.png" style="width:5vh;" alt="Logotipo.png"/> Catálogo de Gorras
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse">
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

        <!-- Filtro de búsqueda lateral -->
        <div class="filtro-seccion position-fixed start-0" style="top:8.2%; bottom:77px; width:300px">
            <h2>Filtro de Búsqueda</h2>
            <form action="" method="" class="d-flex flex-column h-100">
                <div class="d-flex flex-column gap-3">
                    <div>
                        <label>Nombre:</label>
                        <input type="text" class="form-control" placeholder="Ingrese nombre de la gorra">
                    </div>
                    <div>
                        <label>Categoría:</label>
                        <select class="form-select">
                            <option value="">Seleccione</option>
                            <option value="deportiva">Deportiva</option>
                            <option value="formula1">Formula 1</option>
                            <option value="urbana">Urbana</option>
                        </select>
                    </div>
                    <div>
                        <label>Precio Mínimo:</label>
                        <input type="number" class="form-control " step="0.01" min="0.00" max="1000.00" placeholder="0.00">
                    </div>
                    <div>
                        <button type="submit" class="btnE btn-primary w-100">Buscar</button>
                    </div>
                </div>
            </form>
        </div>

        <main class="flex-grow-1 p-3" style="overflow-y: auto;">

            <!--Carrito desplegable-->       
            <div class="modal fade" id="miModal" aria-labelledby="miModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content custom-modal d-flex flex-column" style="height: 100%;">
                        <div class="modal-header">
                            <h5 class="modal-title" id="miModalLabel"><strong>Carrito de Compras</strong></h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body overflow-auto flex-grow-1">
                            <!-- Gorra 1 Comprada-->
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
                        <div class="border-top p-3">
                            <div class="text-end">
                                <h5>Total: <strong>Q000.00</strong></h5>
                            </div>
                            <div class="mt-3 d-flex justify-content-between">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Seguir Comprando</button>
                                <button type="button" class="btn btn-primary">Ir a Pagar</button>
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
            <a href="#" class="text-white text-decoration-none me-3">Política de Privacidad</a>
            <a href="#" class="text-white text-decoration-none">Términos y Condiciones</a>
        </small>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
