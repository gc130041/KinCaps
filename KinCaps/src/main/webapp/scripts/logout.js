document.addEventListener('DOMContentLoaded', () => {
    const logoutLink = document.getElementById('logout-link');

    if (logoutLink) {
        const originalText = logoutLink.innerHTML;

        logoutLink.addEventListener('mouseenter', () => {
            logoutLink.innerHTML = '¿Quieres cerrar sesión?';
        });

        logoutLink.addEventListener('mouseleave', () => {
            logoutLink.innerHTML = originalText;
        });
    }
});