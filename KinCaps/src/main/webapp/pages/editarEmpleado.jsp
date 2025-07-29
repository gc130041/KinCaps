<%@page import = "modelo.Empleado" %>
<%@page contentType = "text/html" pageEncoding="UTF-8"%>
<%
    Empleado empleado = (Empleado) request.getAttribute("editarEmpleado");
%>


<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Cliente</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-4">
            <h2>Editar Cliente</h2>
            <form action="ServletEditarCliente" method="post">
                <input type="hidden" name="accion" value="actualizar">
                <input type="hidden" name="id" value="<%= empleado.getIdEmpleado()%>">
                <div class="mb-3">
                    <label>Nombre</label>
                    <input type="text" name="nombre" value="<%= empleado.getNombre()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label>Apellido</label>
                    <input type="text" name="apellido" value="<%= empleado.getApellido()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label>Correo</label>
                    <input type="email" name="email" value="<%= empleado.getEmail()%>" class="form-control">
                </div>
                
                
                 <div class="mb-3">
                    <label>Teléfono</label>
                    <input type="text" name="telefono" value="<%= empleado.getTelefono()%>" class="form-control">
                </div>
                
                <div class="mb-3">
                    <label>Direccion</label>
                    <input type="text" name="direccion" value="<%= empleado.getDireccion()%>" class="form-control">
                </div>
                <div class="mb-3">
                    <label>Contrasena</label>
                    <input type="password" name="contrasena" value="<%= empleado.getContrasenaHash()%>" class="form-control">
                </div>
                
                <div class="mb-3">
                    <label>Puesto</label>
                    <input type="date" name="Puesto" value="<%= empleado.getPuesto()%>" class="form-control">
                </div>
                
                <div class="mb-3">
                    <label>Fecha de Contratación</label>
                    <input type="text" name="direccion" value="<%= empleado.getFechaContratacion()%>" class="form-control">
                </div>
                
                
                
                <button class="btn btn-success">Actualizar</button>
                <a href="ServletListarEmpleado" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
    </body>
</html>