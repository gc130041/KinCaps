<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Log In - KINCAPS</title>
        <link rel="stylesheet" href="style/index.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    </head>
    <body class="registro-body d-flex align-items-center justify-content-center">
        <div class="registro-wrapper text-white text-center px-4">
            <div class="icon-placeholder mb-4 mx-auto">
                <img class="icon-placeholder" src="img/usuario.png" alt="Logo Usuario"/>
            </div>
            <h1 class="mb-3">Iniciar Sesión</h1>
            <p>
                Tu tienda virtual favorita con el catálogo más amplio de gorras urbanas y deportivas.
            </p>
            <form method="post" action="login" class="registro-form mx-auto">
                <div class="mb-3">
                    <input type="email" class="form-control" id="email" placeholder="Correo electrónico" name="email" required>
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="password" placeholder="Contraseña" name="password" required>
                </div>
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null && !error.isEmpty()) {
                %>
                <div class="alert alert-danger" role="alert">
                    <%= error%>
                </div>
                <%
                    }
                %>
                <div class="d-grid">
                    <button type="submit" class="btn btn-light btn-custom-text fw-bold">Iniciar Sesión</button>
                </div>
            </form>
            <div class="mt-4 d-flex justify-content-center gap-3">
                <a href="pages/register.jsp">¿No tienes una cuenta?, Regístrate</a>
            </div>
        </div>
    </body>
</html>