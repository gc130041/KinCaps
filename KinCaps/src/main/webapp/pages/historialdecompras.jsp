<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial de Compras | KINCAPS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/catalogo.css"> 
    </head>

    <body class="d-flex flex-column min-vh-100">
        <header>
            <nav class="navbar navbar-expand-lg bg-header navbar-dark fixed-top">
                <div class="container-fluid">
                    <a href="${pageContext.request.contextPath}/pages/catalogo.jsp" class="navbar-brand fw-bold">
                        <img src="${pageContext.request.contextPath}/img/Logo/logotipo.png" style="width: 5vh;" alt="Logotipo.png"/> Historial de Compras
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>
            </nav>
        </header>

        <main class="container" style="padding-top: 80px; padding-bottom: 40px;">
            <div class="row">
                <div class="col-12">
                    <h1 class="mb-4 border-bottom pb-2">Mis Pedidos</h1>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table table-hover align-middle">
                    <thead>
                        <tr>
                            <th style="background: linear-gradient(90deg,#002e71,#06458a); border-color: transparent; color:white;" scope="col">Pedido #</th>
                            <th style="background: linear-gradient(90deg,#06458a,#0b5da3); border-color: transparent; color:white;" scope="col">Fecha</th>
                            <th style="background: linear-gradient(90deg,#0b5da3,#0f75bd); border-color: transparent; color:white;" scope="col">Total</th>
                            <th style="background: linear-gradient(90deg,#0f75bd,#0f86d4); border-color: transparent; color:white;" scope="col">Estado</th>
                            <th style="background-color: #0f86d4; border-color: transparent; color:white;" scope="col" class="text-center">Detalles</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">KIN-2025-001</th>
                            <td>01 de Agosto, 2025</td>
                            <td>Q75.00</td>
                            <td><span class="badge bg-success">Entregado</span></td>
                            <td class="text-center">
                                <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#detalle-001" role="button" aria-expanded="false" aria-controls="detalle-001">
                                    Ver Detalles
                                </a>
                            </td>
                        </tr>
                        <tr class="collapse" id="detalle-001">
                            <td colspan="5" class="p-0">
                                <div class="p-3" style="background-color: #f8f9fa;">
                                    <h6 class="mb-3 fw-bold">Productos del Pedido KIN-2025-001</h6>
                                    <div class="d-flex justify-content-between border-bottom pb-2 mb-2">
                                        <span>Gorra Urbana Negra</span>
                                        <span>Cantidad: 1</span>
                                        <span class="fw-semibold">Q50.00</span>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <span>Gorra Deportiva Negra</span>
                                        <span>Cantidad: 1</span>
                                        <span class="fw-semibold">Q25.00</span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">KIN-2025-002</th>
                            <td>15 de Julio, 2025</td>
                            <td>Q110.00</td>
                            <td><span class="badge bg-warning text-dark">Enviado</span></td>
                            <td class="text-center">
                                <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#detalle-002" role="button" aria-expanded="false" aria-controls="detalle-002">
                                    Ver Detalles
                                </a>
                            </td>
                        </tr>
                        <tr class="collapse" id="detalle-002">
                            <td colspan="5" class="p-0">
                                <div class="p-3" style="background-color: #f8f9fa;">
                                    <h6 class="mb-3 fw-bold">Productos del Pedido KIN-2025-002</h6>
                                    <div class="d-flex justify-content-between">
                                        <span>Gorra Formula 1</span>
                                        <span>Cantidad: 1</span>
                                        <span class="fw-semibold">Q110.00</span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">KIN-2025-003</th>
                            <td>20 de Junio, 2025</td>
                            <td>Q40.50</td>
                            <td><span class="badge bg-info">Procesando</span></td>
                            <td class="text-center">
                                <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#detalle-003" role="button" aria-expanded="false" aria-controls="detalle-003">
                                    Ver Detalles
                                </a>
                            </td>
                        </tr>
                        <tr class="collapse" id="detalle-003">
                            <td colspan="5" class="p-0">
                                <div class="p-3" style="background-color: #f8f9fa;">
                                    <h6 class="mb-3 fw-bold">Productos del Pedido KIN-2025-003</h6>
                                    <div class="d-flex justify-content-between">
                                        <span>Gorra Formula 1</span>
                                        <span>Cantidad: 1</span>
                                        <span class="fw-semibold">Q40.50</span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">KIN-2025-004</th>
                            <td>05 de Mayo, 2025</td>
                            <td>Q85.00</td>
                            <td><span class="badge bg-danger">Cancelado</span></td>
                            <td class="text-center">
                                <a href="#" class="btn btn-secondary btn-sm disabled">Ver Detalles</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </main>

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
