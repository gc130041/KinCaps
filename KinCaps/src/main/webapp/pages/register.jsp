<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Register - KINCAPS</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/register.css" />
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
            <form method="post" action="${pageContext.request.contextPath}/pages/register" class="registro-form mx-auto">
                <!--  NOMBRE O NAME  -->
                <div class="mb-3">
                    <input type="text" class="form-control" id="name" placeholder="Nombre Completo" name="name" value="${nameValue}">
                </div>
                <!--  APELLIDO O LAST-NAME  -->
                <div class="mb-3">
                    <input type="text" class="form-control" id="lastname" placeholder="Apellido Completo" name="lastname" value="${lastnameValue}">
                </div>
                <!--  EMAIL O CORREO  -->
                <div class="mb-3">
                    <input type="email" class="form-control" id="email" placeholder="Correo Electrónico" name="email" value="${emailValue}">
                </div>
                <!--  TELEFONO O CELULAR  -->
                <div class="mb-3">
                    <input type="tel" class="form-control" id="telefono" placeholder="Número de Teléfono" name="telefono" value="${telefonoValue}">
                </div>
                <!--  DIRRECIÓN O UBICACION  -->
                <div class="mb-3">
                    <input type="text" class="form-control" id="ubicacion" placeholder="Dirección" name="ubicacion" value="${ubicacionValue}">
                </div>
                <!--  PASSWORD O CONTRASEÑA (Por seguridad, estos no los rellenamos) -->
                <div class="mb-3">
                    <input type="password" class="form-control" id="password" placeholder="Contraseña" name="password">
                </div>
                <!--  CONFIRMAR PASSWORD O CONTRASEÑA  -->
                <div class="mb-3">
                    <input type="password" class="form-control" id="confirmarpassword" placeholder="Confirma Tu Contraseña" name="confirmarpassword">
                </div>
                <!--  BOTON REGISTRARTE NO DEBE LLEVAR ID YA ES UN BOTON  -->
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
                    <button type="submit" class="btn btn-light btn-custom-text fw-bold">Registrate</button>
                </div>
            </form>
            <div class="mt-4 d-flex justify-content-center gap-3">
                <a href="${pageContext.request.contextPath}/index.jsp">¿Ya tienes una cuenta?, Inicia Sesión</a>
            </div>
        </div>
    </body>
</html>


