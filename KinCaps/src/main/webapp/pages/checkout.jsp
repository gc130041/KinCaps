<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Cliente, modelo.DetalleCarrito, modelo.Gorras"%>
<%@page import="java.util.List, java.math.BigDecimal, java.math.RoundingMode"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finalizar Compra | KINCAPS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/catalogo.css">
        <style>
            .checkout-container {
                background-color: white;
                padding: 2rem;
                border-radius: 15px;
                box-shadow: 0 8px 25px rgba(0,0,0,0.1);
            }
            .resumen-pedido {
                background-color: #f8f9fa;
                padding: 1.5rem;
                border-radius: 10px;
            }
            .input-group-text img {
                height: 24px;
            }
        </style>
    </head>
    <body class="d-flex flex-column min-vh-100" data-context-path="${pageContext.request.contextPath}">
        <%
            Cliente cliente = (Cliente) request.getAttribute("cliente");
            List<DetalleCarrito> items = (List<DetalleCarrito>) request.getAttribute("listaDetalleCarrito");
            BigDecimal total = (BigDecimal) request.getAttribute("totalCarrito");
        %>
        <header>
            <nav class="navbar navbar-expand-lg bg-header navbar-dark">
                <div class="container-fluid">
                    <a href="${pageContext.request.contextPath}/gorras" class="navbar-brand fw-bold">
                        <img src="${pageContext.request.contextPath}/img/Logo/logotipo.png" style="width: 5vh;" alt="Logotipo.png"/> Finalizar Compra
                    </a>
                </div>
            </nav>
        </header>

        <main class="flex-grow-1 py-5">
            <div class="container">
                <form action="${pageContext.request.contextPath}/gorras/procesar-pago" method="POST" id="checkout-form">
                    <div class="row g-5">
                        <!-- Columna del Formulario de Pago -->
                        <div class="col-lg-7">
                            <div class="checkout-container">
                                <h4 class="mb-4">Información del Cliente</h4>
                                <div class="mb-3">
                                    <strong>Nombre:</strong> <%= cliente.getNombre()%> <%= cliente.getApellido()%>
                                </div>
                                <div class="mb-3">
                                    <strong>Email:</strong> <%= cliente.getEmail()%>
                                </div>
                                <div class="mb-4">
                                    <label for="direccion" class="form-label">Dirección de Envío</label>
                                    <input type="text" class="form-control" id="direccion" name="direccion" value="<%= (cliente.getDireccion() != null) ? cliente.getDireccion() : ""%>" required>
                                </div>
                                <hr>
                                <h4 class="mb-4">Método de Pago</h4>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="metodoPago" id="pagoTarjeta" value="TARJETA_DE_CREDITO" checked>
                                    <label class="form-check-label" for="pagoTarjeta">Tarjeta de Crédito / Débito</label>
                                </div>
                                <div class="form-check mb-3">
                                    <input class="form-check-input" type="radio" name="metodoPago" id="pagoContraEntrega" value="EFECTIVO">
                                    <label class="form-check-label" for="pagoContraEntrega">Pago Contra Entrega</label>
                                </div>

                                <!-- Campos de la Tarjeta -->
                                <div id="tarjeta-info">
                                    <div class="mb-3">
                                        <label for="numeroTarjeta" class="form-label">Número de Tarjeta</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="numeroTarjeta" name="numeroTarjeta" placeholder="0000 0000 0000 0000" required>
                                            <span class="input-group-text">
                                                <img id="card-icon" src="${pageContext.request.contextPath}/img/tarjeta/default.png" alt="Icono Tarjeta">
                                            </span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-8 mb-3">
                                            <label for="fechaExpiracion" class="form-label">Vencimiento (MM/AA)</label>
                                            <input type="text" class="form-control" id="fechaExpiracion" name="fechaExpiracion" placeholder="MM/AA" required>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <label for="cvv" class="form-label">CVV</label>
                                            <input type="text" class="form-control" id="cvv" name="cvv" placeholder="123" required>
                                        </div>
                                    </div>
                                </div>
                                <!-- Mensaje para Pago Contra Entrega -->
                                <div id="contra-entrega-info" class="alert alert-info d-none">
                                    Pagarás el monto total en efectivo al momento de recibir tu pedido.
                                </div>
                            </div>
                        </div>

                        <!-- Columna del Resumen del Pedido -->
                        <div class="col-lg-5">
                            <div class="resumen-pedido">
                                <h4 class="d-flex justify-content-between align-items-center mb-4">
                                    <span>Resumen del Pedido</span>
                                    <span class="badge bg-primary rounded-pill"><%= items.size()%></span>
                                </h4>
                                <ul class="list-group mb-3">
                                    <% for (DetalleCarrito item : items) {%>
                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                        <div>
                                            <h6 class="my-0"><%= item.getGorra().getNombreGorra()%></h6>
                                            <small class="text-muted">Cantidad: <%= item.getCantidad()%></small>
                                        </div>
                                        <span class="text-muted">Q<%= item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad())).setScale(2, RoundingMode.HALF_UP)%></span>
                                    </li>
                                    <% }%>
                                    <li class="list-group-item d-flex justify-content-between">
                                        <strong>Total (Q)</strong>
                                        <strong>Q<%= total.setScale(2, RoundingMode.HALF_UP)%></strong>
                                    </li>
                                </ul>
                                <button type="submit" class="w-100 btn btn-primary btn-lg">Realizar Pago</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </main>

        <footer class="bg-header text-white text-center py-4 mt-auto">
            <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
        </footer>

        <script src="${pageContext.request.contextPath}/scripts/checkout.js" defer></script>
    </body>
</html>