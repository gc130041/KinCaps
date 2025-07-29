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
        <style>
            body {
                font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
                margin: 0;
                background-color: #f4f4f4;
                color: #15151e;
            }
            .filtro-seccion {
                background-color: white;
                padding: 1.5rem;
                margin-bottom: 1.5rem;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            .filtro-seccion h2 {
                margin-top: 0;
                border-bottom: 1px solid #ddd;
                padding-bottom: 0.5rem;
                font-size: 1rem;
                color: #15151e;
            }
            .filtro-seccion label {
                font-weight: bold;
                margin-bottom: 0.5rem;
                display: block;
            }
            .filtro-seccion input,
            .filtro-seccion select {
                width: 100%;
                padding: 0.8rem;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
                font-size: 1rem;
            }
            .filtro-seccion button {
                width: 100%;
                padding: 0.8rem;
                background-color: #15151e;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s;
            }
            .filtro-seccion button:hover {
                background-color: #e10600;
            }
        </style>
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

        <div class="modal fade" id="miModal" tabindex="-1" aria-labelledby="miModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content custom-modal">
                    <div class="modal-header">
                        <h5 class="modal-title" id="miModalLabel">Carrito de Compras</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        <p>No hay productos en el carrito. ¡Agrega algo!</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary">Ir a Pagar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <!-- Filtro de búsqueda lateral -->
                <div class="col-md-3 col-lg-2 p-3">
                    <div class="filtro-seccion">
                        <h2>Filtro de Búsqueda</h2>
                        <form action="searchResults.jsp" method="GET">
                            <div class="d-flex flex-column gap-3">
                                <div>
                                    <label for="nombre">Nombre:</label>
                                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese nombre de la gorra">
                                </div>
                                <div>
                                    <label for="categoria">Categoría:</label>
                                    <select class="form-select" id="categoria" name="categoria">
                                        <option value="">Seleccione</option>
                                        <option value="deportiva">Deportiva</option>
                                        <option value="casual">Casual</option>
                                        <option value="snapback">Snapback</option>
                                    </select>
                                </div>
                                <div>
                                    <label for="precioMin">Precio Mínimo:</label>
                                    <input type="number" class="form-control" id="precioMin" name="precioMin" step="0.01" placeholder="0.00">
                                </div>
                                <div>
                                    <button type="submit">Buscar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- Contenido principal -->
                <div class="col-md-9 col-lg-10">
                    <!-- Aquí irá el contenido de los resultados -->
                </div>
            </div>

            <footer class="bg-header text-white text-center py-4 mt-auto">
                <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
                <small>
                    <a href="#" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                    <a href="#" class="text-white text-decoration-none">Términos y Condiciones</a>
                </small>
            </footer>

            <script src="https://cdn.jsdelivr.net/npm/boot
