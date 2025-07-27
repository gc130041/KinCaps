<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveedores | Administrador</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="icon" href="../img/Logo/logonobg.png" type="image/x-icon"> 
        <link rel="stylesheet" href="../style/tablas.css">
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
                            <a class="nav-link dropdown-toggle" href="mainmenuadmin.jsp" role="button" aria-expanded="false">
                                Menú Principal
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item dropdown-item-blue" href="clientes.jsp">Clientes</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="empleados.jsp">Empleados</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="productos.jsp">Productos</a></li>
                                <li><a class="dropdown-item dropdown-item-blue" href="carrito.jsp">Carrito</a></li>
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

        <main class="flex-grow-1">
            <div class="container mt-5">
                <h2 class="text-center mb-4">Gestión de Proveedores</h2>
                <div class="d-flex justify-content-end gap-2 mb-3">
                    <button class="btn menu-button">Agregar</button>
                    <button class="btn menu-button">Buscar</button>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered align-middle text-center">
                        <thead class="table-primary">
                            <tr>
                                <th>Código Proveedor</th>
                                <th>Nombre</th>
                                <th>Contacto</th>
                                <th>Teléfono</th>
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>PROV001</td>
                                <td>Insumos de Guatemala S.A.</td>
                                <td>Carlos Rivera</td>
                                <td>+502 2345-6789</td>
                                <td>
                                    <button class="btn btn-sm btn-edit">Editar</button>
                                    <button class="btn btn-sm btn-delete">Eliminar</button>
                                </td>
                            </tr>
                            <tr>
                                <td>PROV002</td>
                                <td>Tecnología y Más</td>
                                <td>Lucía Fernández</td>
                                <td>+502 5555-1234</td>
                                <td>
                                    <button class="btn btn-sm btn-edit">Editar</button>
                                    <button class="btn btn-sm btn-delete">Eliminar</button>
                                </td>
                            </tr>
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
