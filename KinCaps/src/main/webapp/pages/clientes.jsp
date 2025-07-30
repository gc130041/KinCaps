<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Cliente" %>
<%@page import="modelo.Cliente.Estado" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes | Administrador</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon"> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tablas.css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg bg-header navbar-dark">
            <div class="container-fluid">
                <a href="${pageContext.request.contextPath}/mantenimiento" class="navbar-brand fw-bold">Panel de Administración</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="menuNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/mantenimiento" role="button" aria-expanded="false">
                                Menú Principal
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/mantenimiento/proveedores/listar">Proveedores</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/mantenimiento/empleados/listar">Empleados</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/mantenimiento/gorras/listar">Productos</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/mantenimiento/carrito/listar">Carrito</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/mantenimiento/factura/listar">Factura</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/mantenimiento/detalleFactura/listar">Detalle Factura</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/mantenimiento/detallecarrito/listar">Detalle Carrito</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item dropdown-item-red" href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <main class="flex-grow-1">
            <div class="container mt-5">
                <h2 class="text-center mb-4">Gestión de Clientes</h2>
                <div class="d-flex justify-content-end gap-2 mb-3">
                    <a href="${pageContext.request.contextPath}/mantenimiento/clientes/agregar" class="btn menu-button">Agregar</a>
                    <button class="btn menu-button">Buscar</button>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered align-middle text-center">
                        <thead class="table-primary">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Email</th>
                                <th>Teléfono</th>
                                <th>Dirección</th>
                                <th>Estado</th>
                                <th>Contraseña (Hash)</th>
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
                                if (listaClientes != null && !listaClientes.isEmpty()) {
                                    for (Cliente c : listaClientes) {
                                        String filaClass = (c.getEstado() == Estado.INACTIVO) ? "fila-inactiva" : "";
                            %>
                            <tr class="<%= filaClass%>">
                                <td><%= c.getIdCliente()%></td>
                                <td><%= c.getNombre()%></td>
                                <td><%= c.getApellido()%></td>
                                <td><%= c.getEmail()%></td>
                                <td><%= c.getTelefono()%></td>
                                <td><%= c.getDireccion()%></td>
                                <td><%= c.getEstado()%></td>
                                <td class="grap-text"><%= c.getContrasenaHash()%></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/mantenimiento/clientes/editar?id=<%= c.getIdCliente()%>" class="btn btn-sm btn-edit">Editar</a>
                                    <% if (c.getEstado() == Estado.ACTIVO) {%>
                                    <a href="${pageContext.request.contextPath}/mantenimiento/clientes/desactivar?id=<%= c.getIdCliente()%>" class="btn btn-sm btn-delete" onclick="return confirm('¿Desea desactivar este cliente?')">Desactivar</a>
                                    <% } else {%>
                                    <a href="${pageContext.request.contextPath}/mantenimiento/clientes/activar?id=<%= c.getIdCliente()%>" class="btn btn-sm btn-success" onclick="return confirm('¿Desea activar este cliente?')">Activar</a>
                                    <% } %>
                                </td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="9" class="text-center">No hay clientes que mostrar.</td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <footer class="bg-header text-white text-center py-4 mt-auto">
            <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
            <small>
                <a href="#" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                <a href="#" class="text-white text-decoration-none">Términos y Condiciones</a>
            </small>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>