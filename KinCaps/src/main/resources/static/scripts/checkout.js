document.addEventListener('DOMContentLoaded', () => {
    const contextPath = document.body.dataset.contextPath || '';
    const metodoPagoRadios = document.querySelectorAll('input[name="metodoPago"]');
    const tarjetaInfoDiv = document.getElementById('tarjeta-info');
    const contraEntregaInfoDiv = document.getElementById('contra-entrega-info');
    const numeroTarjetaInput = document.getElementById('numeroTarjeta');
    const cardIcon = document.getElementById('card-icon');
    const tarjetaFields = tarjetaInfoDiv.querySelectorAll('input');

    const cardPatterns = {
        visa: /^4/,
        mastercard: /^5[1-5]/,
        amex: /^3[47]/
    };
    
    function togglePaymentFields() {
        if (document.getElementById('pagoTarjeta').checked) {
            tarjetaInfoDiv.classList.remove('d-none');
            contraEntregaInfoDiv.classList.add('d-none');
            tarjetaFields.forEach(field => field.required = true);
        } else {
            tarjetaInfoDiv.classList.add('d-none');
            contraEntregaInfoDiv.classList.remove('d-none');
            tarjetaFields.forEach(field => field.required = false);
        }
    }

    metodoPagoRadios.forEach(radio => radio.addEventListener('change', togglePaymentFields));

    numeroTarjetaInput.addEventListener('input', () => {
        const numero = numeroTarjetaInput.value;
        let cardType = 'default';

        for (const card in cardPatterns) {
            if (cardPatterns[card].test(numero)) {
                cardType = card;
                break;
            }
        }
        cardIcon.src = `${contextPath}/img/tarjeta/${cardType}.png`;
    });

    // Estado inicial
    togglePaymentFields();
});