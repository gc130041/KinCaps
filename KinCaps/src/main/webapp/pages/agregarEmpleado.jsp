<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Agregar de empleado</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    </head>
    <body>
        <div class="container mt-4">
            <h2>Bienvenidos al sistema</h2>
            <form action="ServletAgregarEmpleado" method="post">
                <input type="hidden" name="accion" value="insertar">

                <div class="mb-3">
                    <label>Nombre</label>
                    <input type="text" name="nombre" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label>Apellido</label>
                    <input type="text" name="apellido" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label>Correo</label>
                    <input type="email" name="email" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label>Teléfono</label>
                    <input type="text" name="telefono" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label>Dirección</label>
                    <input type="text" name="direccion" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label>Contraseña</label>
                    <input type="password" name="contrasena" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label>Puesto</label>
                    <input type="text" name="puesto" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label>Fecha de Contratación</label>
                    <input type="date" name="fechaContratacion" class="form-control" required />
                </div>

                <button type="submit" class="btn btn-success">Guardar</button>
                <a href="empleados.jsp" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
    </body>
</html>

