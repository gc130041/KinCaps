<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.Date"%>
<%@page import="java.time.ZoneId"%>

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
                    <a href="${pageContext.request.contextPath}/gorras" class="navbar-brand fw-bold">
                        <img src="${pageContext.request.contextPath}/img/Logo/logotipo.png" style="width: 5vh;" alt="Logotipo.png"/> Historial de Compras
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="menuNav">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/gorras/catalogo" class="btn btn-outline-light">Volver al Catálogo</a>
                            </li>
                        </ul>
                    </div>
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
                        <c:if test="${empty listaPedidos}">
                            <tr>
                                <td colspan="5" class="text-center p-4">
                                    Aún no has realizado ninguna compra. ¡Explora nuestro <a href="${pageContext.request.contextPath}/gorras/catalogo">catálogo</a>!
                                </td>
                            </tr>
                        </c:if>

                        <c:forEach var="pedido" items="${listaPedidos}">
                            <c:set var="fechaComoDate" value="<%= Date.from(((modelo.Carrito) pageContext.findAttribute("pedido")).getFechaCreacion().atZone(ZoneId.systemDefault()).toInstant())%>" />

                            <c:set var="totalPedido" value="${0}" />
                            <c:forEach var="detalle" items="${pedido.detalles}">
                                <c:set var="totalPedido" value="${totalPedido + (detalle.cantidad * detalle.precioUnitario)}" />
                            </c:forEach>

                            <tr>
                                <th scope="row">KIN-2025-${pedido.idCarrito}</th>
                                <td>
                                    <fmt:formatDate value="${fechaComoDate}" pattern="dd 'de' MMMM, yyyy" />
                                </td>
                                <td>
                                    <fmt:setLocale value="es-GT" />
                                    <fmt:formatNumber value="${totalPedido}" type="currency" currencySymbol="Q"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${pedido.estado == 'PAGADO'}">
                                            <span class="badge bg-success">Pagado</span>
                                        </c:when>
                                        <c:when test="${pedido.estado == 'INACTIVO'}">
                                            <span class="badge bg-warning text-dark">Cancelado</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-secondary">${pedido.estado}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="text-center">
                                    <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#detalle-${pedido.idCarrito}" role="button" aria-expanded="false" aria-controls="detalle-${pedido.idCarrito}">
                                        Ver Detalles
                                    </a>
                                </td>
                            </tr>
                            <tr class="collapse" id="detalle-${pedido.idCarrito}">
                                <td colspan="5" class="p-0">
                                    <div class="p-3" style="background-color: #f8f9fa;">
                                        <h6 class="mb-3 fw-bold">Productos del Pedido KIN-2025-${pedido.idCarrito}</h6>
                                        <c:forEach var="detalle" items="${pedido.detalles}">
                                            <div class="d-flex justify-content-between border-bottom pb-2 mb-2">
                                                <span>${detalle.gorra.nombreGorra}</span>
                                                <span>Cantidad: ${detalle.cantidad}</span>
                                                <span class="fw-semibold">
                                                    <fmt:formatNumber value="${detalle.precioUnitario}" type="currency" currencySymbol="Q"/>
                                                </span>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
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