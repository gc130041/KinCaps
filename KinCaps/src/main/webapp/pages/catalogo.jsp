<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito | Administrador</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon"> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/catalogo.css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg bg-header navbar-dark">
            <div class="container-fluid">
                <a href="${pageContext.request.contextPath}/pages/mainmenu.jsp" style="font-size: 30px;" class="navbar-brand fw-bold"><img src="${pageContext.request.contextPath}/img/Logo/logotipo.png" style="width:5vh;" alt="Logotipo.png"/> Catálogo de Gorras</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle pressed-list" href="mainmenuadmin.jsp" role="button" aria-expanded="false">
                                Menú Principal
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item dropdown-item-blue" href="proveedor.jsp">Proveedores</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="clientes.jsp">Clientes</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="empleados.jsp">Empleados</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="productos.jsp">Productos</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="factura.jsp">Factura</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="detalleFactura.jsp">Detalle Factura</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="detalleCarrito.jsp">Detalle Carrito</a></li>
                                <li><a class="dropdown-item dropdown-item-red" href="../index.jsp">Cerrar Sesión</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <footer class="bg-header text-white text-center py-4 mt-auto">
            <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
            <small>
                <a href="#" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                <a href="#" class="text-white text-decoration-none">Términos y Condiciones</a>
            </small>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
 