<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Politica de Privacidad | KINCAPS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="icon" href="${pageContext.request.contextPath}/img/Logo/logonobg.png" type="image/x-icon"> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tablas.css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg bg-header navbar-dark">
            <div class="container-fluid">
                <a href="${pageContext.request.contextPath}/login" class="navbar-brand fw-bold">Politica de Privacidad</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </nav>

        <main> 
            <div class="container my-5">
                <h1 class="mb-4 text-primary">Política de Privacidad de KINCAPS</h1>
                <p><strong>Fecha de vigencia:</strong> 30 de julio de 2025<br />
                    <strong>Dirección:</strong> 6A Avenida 13-54, Ciudad de Guatemala 01007, Guatemala<br />
                    <strong>Correo electrónico de contacto:</strong> <a href="mailto:KinCaps@kinal.edu.gt">KINCAPS@kinal.edu.gt</a>
                </p>

                <div class="accordion" id="privacyAccordion">

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingIntro">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseIntro" aria-expanded="true" aria-controls="collapseIntro">
                                1. Introducción
                            </button>
                        </h2>
                        <div id="collapseIntro" class="accordion-collapse collapse show" aria-labelledby="headingIntro" data-bs-parent="#privacyAccordion">
                            <div class="accordion-body">
                                <p>En KinCaps valoramos y respetamos la privacidad de todos nuestros usuarios. Esta Política de Privacidad explica detalladamente cómo recopilamos, usamos, almacenamos, protegemos y compartimos tu información personal cuando accedes y utilizas nuestro sitio web y servicios. Además, te informamos sobre tus derechos de privacidad y cómo puedes ejercerlos. Esta política cumple con los requisitos de las leyes de privacidad más importantes, incluyendo CalOPPA (California Online Privacy Protection Act), el Reglamento General de Protección de Datos (GDPR) de la Unión Europea y la Ley de Privacidad del Consumidor de California (CCPA).</p>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingInfo">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseInfo" aria-expanded="false" aria-controls="collapseInfo">
                                2. Información que recopilamos
                            </button>
                        </h2>
                        <div id="collapseInfo" class="accordion-collapse collapse" aria-labelledby="headingInfo" data-bs-parent="#privacyAccordion">
                            <div class="accordion-body">
                                <h5>2.1 Información que nos proporcionas directamente</h5>
                                <p>Cuando te registras o interactúas con KinCaps, recopilamos la información personal que nos proporcionas voluntariamente, incluyendo pero no limitada a:</p>
                                <ul>
                                    <li>Nombre completo o nombre de usuario</li>
                                    <li>Números de teléfono (móvil y/o fijo)</li>
                                    <li>Dirección de correo electrónico</li>
                                    <li>Dirección postal completa</li>
                                    <li>Dirección de facturación</li>
                                    <li>Contraseña y credenciales para acceso seguro</li>
                                </ul>

                                <h5>2.2 Información técnica y de uso</h5>
                                <p>Recopilamos automáticamente ciertos datos técnicos y de uso a través de tecnologías como cookies y web beacons para optimizar la funcionalidad de nuestro sitio y analizar su rendimiento. Esta información incluye:</p>
                                <ul>
                                    <li>Dirección IP</li>
                                    <li>Tipo y versión de navegador</li>
                                    <li>Sistema operativo</li>
                                    <li>Páginas visitadas, duración de las visitas y rutas de navegación</li>
                                    <li>Fecha y hora de acceso</li>
                                    <li>Datos del dispositivo utilizado para acceder</li>
                                </ul>
                                <p>Esta información nos ayuda a entender cómo interactúas con nuestro sitio y a mejorar continuamente nuestros servicios.</p>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingUso">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseUso" aria-expanded="false" aria-controls="collapseUso">
                                3. Cómo usamos tu información
                            </button>
                        </h2>
                        <div id="collapseUso" class="accordion-collapse collapse" aria-labelledby="headingUso" data-bs-parent="#privacyAccordion">
                            <div class="accordion-body">
                                <p>En KinCaps utilizamos tu información personal para los siguientes fines:</p>
                                <ul>
                                    <li><strong>Procesamiento de pagos:</strong> Gestionar y procesar pagos únicos y recurrentes de forma segura para la adquisición de nuestros productos o servicios.</li>
                                    <li><strong>Comunicación:</strong> Enviar correos electrónicos, boletines informativos, actualizaciones y otras comunicaciones que hayas solicitado o para las cuales hayas dado tu consentimiento explícito.</li>
                                    <li><strong>Mejora de servicios:</strong> Analizar patrones de uso para optimizar la experiencia del usuario, mejorar funcionalidades, identificar problemas técnicos y desarrollar nuevas características.</li>
                                    <li><strong>Soporte y atención al cliente:</strong> Responder a tus preguntas, solicitudes o quejas y proporcionarte asistencia personalizada.</li>
                                    <li><strong>Seguridad:</strong> Detectar, prevenir y responder a posibles fraudes, abusos o actividades ilícitas que puedan afectar a KinCaps o a sus usuarios.</li>
                                </ul>
                                <p>No vendemos, alquilamos ni compartimos tu información personal con anunciantes ni terceros con fines publicitarios. KinCaps no utiliza técnicas de publicidad basada en comportamiento, retargeting o píxeles de Facebook.</p>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingCompartir">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseCompartir" aria-expanded="false" aria-controls="collapseCompartir">
                                4. Compartir tu información con terceros
                            </button>
                        </h2>
                        <div id="collapseCompartir" class="accordion-collapse collapse" aria-labelledby="headingCompartir" data-bs-parent="#privacyAccordion">
                            <div class="accordion-body">
                                <p>Podemos compartir tu información con proveedores de servicios externos estrictamente para cumplir con los fines antes mencionados, tales como:</p>
                                <ul>
                                    <li>Proveedores de servicios de pago</li>
                                    <li>Plataformas tecnológicas para inicio de sesión seguro, como GitHub</li>
                                    <li>Empresas que nos asisten en análisis de datos y optimización del sitio</li>
                                    <li>Autoridades legales cuando sea requerido por ley</li>
                                </ul>
                                <p>Todos nuestros proveedores están obligados contractualmente a proteger tu información y a usarla exclusivamente para los fines que les hemos encargado. KinCaps no autoriza ni permite que estos terceros utilicen tu información para propósitos propios.</p>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingGitHub">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseGitHub" aria-expanded="false" aria-controls="collapseGitHub">
                                5. Inicio de sesión mediante GitHub
                            </button>
                        </h2>
                        <div id="collapseGitHub" class="accordion-collapse collapse" aria-labelledby="headingGitHub" data-bs-parent="#privacyAccordion">
                            <div class="accordion-body">
                                <p>Para mejorar la experiencia de usuario, ofrecemos la opción de iniciar sesión utilizando tu cuenta de GitHub. Este proceso permite una autenticación segura sin necesidad de crear una cuenta nueva. Solo compartimos con GitHub la información necesaria para autenticar tu acceso y no almacenamos información adicional sin tu consentimiento.</p>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingMenores">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseMenores" aria-expanded="false" aria-controls="collapseMenores">
                                6. Usuarios menores de 13 años
                            </button>
                        </h2>
                        <div id="collapseMenores" class="accordion-collapse collapse" aria-labelledby="headingMenores" data-bs-parent="#privacyAccordion">
                            <div class="accordion-body">
                                <p>KinCaps reconoce la importancia de proteger la privacidad de los menores de 13 años y cumple con la Ley de Protección de la Privacidad Infantil en Línea (COPPA). Nuestro sitio está dirigido también a usuarios menores de 13 años, pero no recopilamos intencionadamente más información personal que la estrictamente necesaria. Padres o tutores pueden solicitar el acceso, corrección o eliminación de la información personal de menores poniéndose en contacto con nosotros directamente.</p>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingSeguridad">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSeguridad" aria-expanded="false" aria-controls="collapseSeguridad">
                                7. Seguridad de tu información
                            </button>
                        </h2>
                        <div id="collapseSeguridad" class="accordion-collapse collapse" aria-labelledby="headingSeguridad" data-bs-parent="#privacyAccordion">
                            <div class="accordion-body">
                                <p>Implementamos medidas técnicas, administrativas y físicas razonables para proteger tu información personal contra accesos no autorizados, alteraciones, divulgación o destrucción. Esto incluye:</p>
                                <ul>
                                    <li>Encriptación de datos sensibles durante la transmisión y almacenamiento</li>
                                    <li>Control de acceso restringido al personal autorizado</li>
                                    <li>Monitoreo constante de nuestros sistemas de seguridad</li>
                                    <li>Procedimientos para detectar y responder ante incidentes de seguridad</li>
                                </ul>
                                <p>Sin embargo, ninguna transmisión por internet o método de almacenamiento electrónico es 100% seguro. Aunque hacemos esfuerzos razonables, no podemos garantizar una seguridad absoluta.</p>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingRetencion">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseRetencion" aria-expanded="false" aria-controls="collapseRetencion">
                                8. Retención de datos
                            </button>
                        </h2>
                        <div id="collapseRetencion" class="accordion-collapse collapse" aria-labelledby="headingRetencion" data-bs-parent="#privacyAccordion">
                            <div class="accordion-body">
                                <p>Retendremos tu información personal solo mientras sea necesario para los fines descritos en esta política o para cumplir con obligaciones legales, fiscales o contractuales. Una vez que la información ya no sea necesaria, será eliminada o anonimizada de forma segura para proteger tu privacidad.</p>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingDerechos">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseDerechos" aria-expanded="false" aria-controls="collapseDerechos">
                                9. Tus derechos de privacidad
                            </button>
                        </h2>
                        <div id="collapseDerechos" class="accordion-collapse collapse" aria-labelledby="headingDerechos" data-bs-parent="#privacyAccordion">
                            <div class="accordion-body">
                                <p>De acuerdo con las leyes CalOPPA, GDPR y CCPA, tienes varios derechos respecto a tu información personal:</p>
                                <ul>
                                    <li><strong>Acceso:</strong> Solicitar una copia de los datos que almacenamos sobre ti.</li>
                                    <li><strong>Rectificación:</strong> Corregir datos inexactos o incompletos.</li>
                                    <li><strong>Eliminación:</strong> Solicitar la eliminación de tus datos, sujeto a excepciones legales.</li>
                                    <li><strong>Oposición:</strong> Oponerte al tratamiento de tus datos para fines específicos, como marketing directo.</li>
                                    <li><strong>Limitación:</strong> Restringir ciertos tratamientos de tus datos en determinadas circunstancias.</li>
                                    <li><strong>Portabilidad:</strong> Obtener tus datos en formato electrónico estructurado para uso personal o transferencia.</li>
                                    <li><strong>No discriminación:</strong> En caso de ejercer derechos bajo CCPA, no serás objeto de discriminación por ello.</li>
                                </ul>
                                <p>Para ejercer estos
                                    </main>

                                <footer class="bg-header text-white text-center py-4 mt-auto">
                                    <p class="mb-1">2025 KINCAPS. Todos los derechos reservados.</p>
                                    <small>
                                        <a class="text-white text-decoration-none me-3">Política de Privacidad</a>
                                        <a href="${pageContext.request.contextPath}/terminos" class="text-white text-decoration-none">Términos y Condiciones</a>
                                    </small>
                                </footer>
                                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
                                </body>
                                </html>