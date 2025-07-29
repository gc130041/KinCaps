<%@page import="java.util.List"%>
<%@page import="modelo.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Empleados | Administrador</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tablas.css">
</head>
<body class="d-flex flex-column min-vh-100">
<nav class="navbar navbar-expand-lg bg-header navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold">Panel de Administración</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/mainmenuadmin.jsp" role="button" aria-expanded="false">
                        Menú Principal
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/proveedor.jsp">Proveedores</a></li>
                        <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/clientes.jsp">Clientes</a></li>
                        <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/productos.jsp">Productos</a></li>
                        <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/carrito.jsp">Carrito</a></li>
                        <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/factura.jsp">Factura</a></li>
                        <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/detalleFactura.jsp">Detalle Factura</a></li>
                        <li><a class="dropdown-item dropdown-item-blue" href="${pageContext.request.contextPath}/detalleCarrito.jsp">Detalle Carrito</a></li>
                        <li><a class="dropdown-item dropdown-item-red" href="${pageContext.request.contextPath}/index.jsp">Cerrar Sesión</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

    <div class="container">
            <div class="row">
                <div class="col-12">
                    <h2 class="mb-4 text-center">Lista de empleados</h2>
                    <a href="${pageContext.request.contextPath}/pages/agregarEmpleado.jsp" class="btn btn-success">Agregar Empleado</a>
                     <a href="${pageContext.request.contextPath}/pages/editarEmpleado.jsp" class="btn btn-secondary">Editar Empleados</a>
                    <table class="table table-striped table-bordered table-hover">
                        <thead class="table-dark">
                    <tr>
                        <th>Código Empleado</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Email</th>
                        <th>Teléfono</th>
                        <th>Dirección</th>
                        <th>Puesto</th>
                        <th>Fecha de Contratación</th>
                        <th>Opciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Empleado> listaEmpleado = (List<Empleado>) request.getAttribute("listaEmpleado");
                                if (listaEmpleado != null && !listaEmpleado.isEmpty()) {
                                    for (Empleado c : listaEmpleado) {
                                       //Llene la tabla, fila -> columna de Cliente
                    %>
                    <tr>
                        <td><%= c.getIdEmpleado() %></td>
                        <td><%= c.getNombre() %></td>
                        <td><%= c.getApellido() %></td>
                        <td><%= c.getEmail() %></td>
                        <td><%= c.getTelefono() %></td>
                        <td><%= c.getDireccion() %></td>
                        <td><%= c.getPuesto() %></td>
                        <td><%= c.getFechaContratacion() %></td>
                       <td>
                                    <a href="ServletEditarEmpleado?accion=editar&id=<%= c.getIdEmpleado()%>"
                                       class="btn btn-warning btn-sm">Editar</a>
                                    <a href="ServletEliminarEmpleados?accion=eliminar&id=<%= c.getFechaContratacion()%>"
                                       class="btn btn-danger btn-sm" onclick="return confirm('¿Desea eliminar este empleado?')">Eliminar</a>
                                </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="9">No hay empleados registrados.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
    </div>

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
