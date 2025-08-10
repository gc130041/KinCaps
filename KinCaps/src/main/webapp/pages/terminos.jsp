<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Términos y Condiciones| KINCAPS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon"> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tablas.css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg bg-header navbar-dark">
            <div class="container-fluid">
                <a href="${pageContext.request.contextPath}/login" class="navbar-brand fw-bold">Términos y Condiciones</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </nav>
               <main>
    <div class="container my-5">
        <h1 class="mb-4 text-primary">Términos y Condiciones de KINCAPS</h1>
        <p>Por favor, lee atentamente los siguientes Términos y Condiciones antes de utilizar los servicios de KINCAPS. Al acceder y utilizar nuestros servicios, aceptas cumplir con estos términos.</p>

        <!-- Acordeón de Términos y Condiciones -->
        <div class="accordion" id="termsAccordion">

            <!-- Introducción -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingIntro">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseIntro" aria-expanded="true" aria-controls="collapseIntro">
                        1. Introducción
                    </button>
                </h2>
                <div id="collapseIntro" class="accordion-collapse collapse show" aria-labelledby="headingIntro" data-bs-parent="#termsAccordion">
                    <div class="accordion-body">
                        Bienvenido a KINCAPS. Estos Términos y Condiciones ("Términos") regulan el acceso y uso de los servicios proporcionados por KINCAPS, una empresa registrada bajo las leyes de Guatemala. Al acceder o utilizar nuestros servicios, estás aceptando cumplir con estos Términos. Si no estás de acuerdo con alguna de las disposiciones, no debes utilizar nuestros servicios.
                    </div>
                </div>
            </div>

            <!-- Tu Relación con KINCAPS -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingRelacion">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseRelacion" aria-expanded="false" aria-controls="collapseRelacion">
                        2. Tu relación con KINCAPS
                    </button>
                </h2>
                <div id="collapseRelacion" class="accordion-collapse collapse" aria-labelledby="headingRelacion" data-bs-parent="#termsAccordion">
                    <div class="accordion-body">
                        Cuando accedes y utilizas los servicios de KINCAPS, estableces una relación contractual con nosotros. Esta relación está regida por los Términos y Condiciones que se describen aquí, así como por cualquier otra política aplicable que se publique en nuestro sitio web. KINCAPS se reserva el derecho de modificar estos Términos en cualquier momento, y dichos cambios serán efectivos al ser publicados en esta página.
                        <br><br>
                        Es tu responsabilidad revisar regularmente los Términos y Condiciones para estar al tanto de cualquier cambio. Si continúas utilizando nuestros servicios después de la modificación de los Términos, estarás aceptando dichas modificaciones.
                    </div>
                </div>
            </div>

            <!-- Usar los Servicios de KINCAPS -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingUso">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseUso" aria-expanded="false" aria-controls="collapseUso">
                        3. Usar los Servicios de KINCAPS
                    </button>
                </h2>
                <div id="collapseUso" class="accordion-collapse collapse" aria-labelledby="headingUso" data-bs-parent="#termsAccordion">
                    <div class="accordion-body">
                        Para utilizar los servicios de KINCAPS, debes cumplir con los siguientes requisitos:
                        <ul>
                            <li>Ser mayor de 18 años, o tener el consentimiento de un padre o tutor legal si eres menor de edad.</li>
                            <li>Proveer información correcta, completa y actualizada cuando sea necesario, por ejemplo, al crear una cuenta.</li>
                            <li>No utilizar nuestros servicios para fines ilegales o no autorizados, ni para realizar actividades fraudulentas o que infrinjan derechos de propiedad intelectual de terceros.</li>
                            <li>Comprometerte a mantener la confidencialidad de tu cuenta y credenciales de acceso, y ser responsable de todas las actividades que ocurran bajo tu cuenta.</li>
                        </ul>
                        Si incumples cualquiera de estos requisitos, KINCAPS puede suspender o terminar tu acceso a los servicios sin previo aviso.
                    </div>
                </div>
            </div>

            <!-- Contenido en los Servicios de KINCAPS -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingContenido">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseContenido" aria-expanded="false" aria-controls="collapseContenido">
                        4. Contenido en los Servicios de KINCAPS
                    </button>
                </h2>
                <div id="collapseContenido" class="accordion-collapse collapse" aria-labelledby="headingContenido" data-bs-parent="#termsAccordion">
                    <div class="accordion-body">
                        Los servicios de KINCAPS incluyen diversos tipos de contenido, como textos, imágenes, videos, gráficos, y otros materiales (en adelante, "Contenido"). El contenido proporcionado por KINCAPS está protegido por derechos de autor, marcas registradas y otros derechos de propiedad intelectual. No puedes reproducir, distribuir, modificar, mostrar públicamente, o crear trabajos derivados de nuestro contenido sin nuestra autorización expresa.
                        <br><br>
                        El usuario acepta no subir, compartir ni distribuir contenido que sea ilegal, difamatorio, ofensivo, o que infrinja los derechos de propiedad intelectual de terceros. KINCAPS tiene el derecho de eliminar o bloquear el acceso a cualquier contenido que considere inapropiado o que infrinja estos Términos.
                    </div>
                </div>
            </div>

            <!-- Software en los Servicios de KINCAPS -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingSoftware">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSoftware" aria-expanded="false" aria-controls="collapseSoftware">
                        5. Software en los Servicios de KINCAPS
                    </button>
                </h2>
                <div id="collapseSoftware" class="accordion-collapse collapse" aria-labelledby="headingSoftware" data-bs-parent="#termsAccordion">
                    <div class="accordion-body">
                        Los servicios de KINCAPS pueden incluir software o aplicaciones proporcionadas para facilitar el acceso y uso de nuestras plataformas. Al utilizar estos servicios, se te concede una licencia limitada, no exclusiva, no transferible y revocable para usar el software conforme a los fines previstos.
                        <br><br>
                        No tienes derecho a copiar, modificar, distribuir, descompilar ni realizar ingeniería inversa sobre el software proporcionado. Todos los derechos sobre el software, incluidas las actualizaciones, parches y mejoras, permanecen con KINCAPS o sus licenciantes.
                    </div>
                </div>
            </div>

            <!-- En caso de problemas o discrepancias -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingProblemas">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseProblemas" aria-expanded="false" aria-controls="collapseProblemas">
                        6. En caso de problemas o discrepancias
                    </button>
                </h2>
                <div id="collapseProblemas" class="accordion-collapse collapse" aria-labelledby="headingProblemas" data-bs-parent="#termsAccordion">
                    <div class="accordion-body">
                        Si tienes alguna discrepancia o problema relacionado con el uso de los servicios de KINCAPS, te recomendamos que primero te pongas en contacto con nuestro soporte al cliente para tratar de resolverlo de manera amigable y directa.
                        <br><br>
                        En caso de que no se pueda resolver la discrepancia, las partes pueden recurrir a los mecanismos de resolución de conflictos que se detallan en los Términos de Uso, que incluyen, si es necesario, la mediación o arbitraje, según corresponda. Al usar nuestros servicios, aceptas que cualquier disputa será resuelta conforme a las leyes de Guatemala.
                    </div>
                </div>
            </div>

            <!-- Sobre estos Términos -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingSobre">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSobre" aria-expanded="false" aria-controls="collapseSobre">
                        7. Sobre estos Términos
                    </button>
                </h2>
                <div id="collapseSobre" class="accordion-collapse collapse" aria-labelledby="headingSobre" data-bs-parent="#termsAccordion">
                    <div class="accordion-body">
                        Estos Términos constituyen el acuerdo completo entre tú y KINCAPS en relación con el uso de los servicios de KINCAPS. Si alguna disposición de estos Términos se considera inválida o inaplicable, dicha disposición será modificada para reflejar la intención de las partes, sin afectar la validez de las disposiciones restantes.
                        <br><br>
                        Te recomendamos que revises regularmente estos Términos para estar al tanto de cualquier cambio o actualización. El uso continuo de los servicios de KINCAPS después de cualquier modificación será considerado como aceptación de los nuevos Términos.
                    </div>
                </div>
            </div>

            <!-- Contacto -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingContacto">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseContacto" aria-expanded="false" aria-controls="collapseContacto">
                        8. Contacto
                    </button>
                </h2>
                <div id="collapseContacto" class="accordion-collapse collapse" aria-labelledby="headingContacto" data-bs-parent="#termsAccordion">
                    <div class="accordion-body">
                        Si tienes preguntas sobre estos Términos y Condiciones, o necesitas asistencia con alguna parte de los servicios de KINCAPS, puedes ponerte en contacto con nosotros a través del siguiente correo electrónico: <a href="mailto:KinCaps@kinal.edu.gt">KinCaps@kinal.edu.gt</a>.
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</main>
 <footer class="bg-header text-white text-center py-4 mt-auto">
                                    <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
                                    <small>
                                        <a href="${pageContext.request.contextPath}/politica" class="text-white text-decoration-none me-3">Política de Privacidad</a>
                                        <a class="text-white text-decoration-none">Términos y Condiciones</a>
                                    </small>
                                </footer>
                                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
                                </body>
                                </html>