<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Principal | Administrador</title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon"> 
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/mainmenuadmin.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/mainmenu.css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <header class="bg-header text-white text-center py-4 shadow-sm">
            <h1 class="display-5 fw-bold">
                Panel de Administración
            </h1>
        </header>
        <main class="flex-grow-1">
            <div class="container mt-5">
                <div class="text-center mb-4">
                    <h2 class="text-primary fw-bold">Selecciona una opción</h2>
                    <p class="text-muted">Gestiona fácilmente todas las secciones del sistema.</p>
                </div>
                <div class="row justify-content-center g-4">
                    <div class="col-md-4">
                        <div class="card option-card h-100 text-center p-4">
                            <h5>Proveedores</h5>
                            <p class="text-muted">Maneja la información de tus proveedores.</p>
                            <a href="${pageContext.request.contextPath}/mantenimiento/proveedores/" class="btn menu-button mt-2">Gestionar Proveedores</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card option-card h-100 text-center p-4">
                            <h5>Clientes</h5>
                            <p class="text-muted">Crea, edita y elimina clientes del sistema.</p>
                            <a href="${pageContext.request.contextPath}/mantenimiento/clientes/" class="btn menu-button mt-2">Gestionar Usuarios</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card option-card h-100 text-center p-4">
                            <h5>Empleados</h5>
                            <p class="text-muted">Gestiona los datos del personal.</p>
                            <a href="${pageContext.request.contextPath}/mantenimiento/empleados/" class="btn menu-button mt-2">Gestionar Empleados</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card option-card h-100 text-center p-4">
                            <h5>Productos</h5>
                            <p class="text-muted">Gestion total sobre tus productos y stock.</p>
                            <a href="${pageContext.request.contextPath}/mantenimiento/gorras/" class="btn menu-button mt-2">Gestionar Productos</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card option-card h-100 text-center p-4">
                            <h5>Carrito</h5>
                            <p class="text-muted">Gestiona el estado del carrito de compras.</p>
                            <a href="${pageContext.request.contextPath}/mantenimiento/carrito/" class="btn menu-button mt-2">Ver Carrito</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card option-card h-100 text-center p-4">
                            <h5>Factura</h5>
                            <p class="text-muted">Gestiona y emite facturas.</p>
                            <a href="${pageContext.request.contextPath}/mantenimiento/facturas/" class="btn menu-button mt-2">Gestionar Facturas</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card option-card h-100 text-center p-4">
                            <h5>Detalle Factura</h5>
                            <p class="text-muted">Gestiona detalles específicos de cada factura.</p>
                            <a href="${pageContext.request.contextPath}/mantenimiento/detallefactura/" class="btn menu-button mt-2">Ver Detalle</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card option-card h-100 text-center p-4">
                            <h5>Detalle Carrito</h5>
                            <p class="text-muted">Gestiona el contenido completo del carrito.</p>
                            <a href="${pageContext.request.contextPath}/mantenimiento/detallecarrito/" class="btn menu-button mt-2">Ver Detalle</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card option-card h-100 text-center p-4">
                            <h5>Cerrar Sesión</h5>
                            <p class="text-muted">Finaliza tu sesión actual de forma segura.</p>
                            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger mt-2">Cerrar Sesión</a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer class="bg-header text-white text-center py-4 mt-5">
            <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
            <small>
                <a href="${pageContext.request.contextPath}/politica" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                <a href="${pageContext.request.contextPath}/terminos" class="text-white text-decoration-none">Términos y Condiciones</a>
            </small>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
