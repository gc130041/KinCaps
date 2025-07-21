<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Register - KINCAPS</title>
        <link rel="stylesheet" href="../style/register.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    </head>
    <body class="registro-body d-flex align-items-center justify-content-center">
        <div class="registro-wrapper text-white text-center px-4">
            <div class="icon-placeholder mb-4 mx-auto">
                <img class="icon-placeholder" src="../img/usuario.png" alt="Logo Usuario"/>
            </div>
            <h1 class="mb-3">Registrate</h1>
            <p>
                Tu tienda virtual favorita con el catálogo más amplio de gorras urbanas y deportivas.
            </p>
            <form method="get" action="ServletLogIn" class="registro-form mx-auto">
                <!--  NOMBRE O NAME  -->
                <div class="mb-3">
                    <input type="text" class="form-control" id="#" placeholder="Nombre Completo" name="#">
                </div>
                <!--  APELLIDO O LAST-NAME  -->
                <div class="mb-3">
                    <input type="text" class="form-control" id="#" placeholder="Apellidos Completos" name="#">
                </div>
                <!--  EMAIL O CORREO  -->
                <div class="mb-3">
                    <input type="email" class="form-control" id="#" placeholder="Correo electrónico" name="#">
                </div>
                <!--  PASSWORD O CONTRASEÑA  -->
                <div class="mb-3">
                    <input type="password" class="form-control" id="#" placeholder="Contraseña" name="#">
                </div>
                <!--  CONFIRMAR PASSWORD O CONTRASEÑA  -->
                <div class="mb-3">
                    <input type="password" class="form-control" id="#" placeholder="Confirma Tu Contraseña" name="#">
                </div>
                <!--  BOTON REGISTRARTE NO DEBE LLEVAR ID YA ES UN BOTON  -->
                <div class="d-grid">
                    <button type="submit" class="btn btn-light btn-custom-text fw-bold">Registrate</button>
                </div>
            </form>
            <div class="mt-4 d-flex justify-content-center gap-3">
                <a href="../index.jsp">¿Ya tienes una cuenta, Registrate?</a>
            </div>
        </div>
    </body>
</html>


