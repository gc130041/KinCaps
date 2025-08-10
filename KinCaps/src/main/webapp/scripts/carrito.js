document.addEventListener('DOMContentLoaded', () => {
    const contextPath = document.body.dataset.contextPath || '';
    const cantidadInput = document.getElementById('cantidad-input');
    const mainContent = document.querySelector('main');

    function actualizarPaginaDetalle(producto) {
        const stockDisplay = document.getElementById('stock-display');
        const cantidadInput = document.getElementById('cantidad-input');
        const agregarBtn = document.querySelector('.btn-agregar-carrito');

        if (!stockDisplay || !cantidadInput || !agregarBtn) {
            return;
        }

        const stockRestante = producto.stockRestante;

        if (stockRestante > 0) {
            stockDisplay.innerHTML = `¡Disponible! (${stockRestante} en stock)`;
            cantidadInput.max = stockRestante;
            if (parseInt(cantidadInput.value) > stockRestante) {
                cantidadInput.value = stockRestante;
            }
        } else {
            stockDisplay.textContent = "No hay más unidades disponibles.";
            cantidadInput.disabled = true;
            agregarBtn.disabled = true;
            agregarBtn.innerHTML = "Stock Agotado";
        }
    }

    if (mainContent) {
        mainContent.addEventListener('click', (event) => {
            const target = event.target.closest('.btn-agregar-carrito');
            if (target) {
                const idGorra = target.dataset.id;
                const cantidad = cantidadInput ? parseInt(cantidadInput.value) : 1;
                agregarProductoAlCarrito(idGorra, cantidad);
            }
        });
    }
    async function agregarProductoAlCarrito(idGorra, cantidad) {
        const url = `${contextPath}/carrito/agregar`;
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: `idGorra=${idGorra}&cantidad=${cantidad}`
            });
            if (!response.ok) {
                const errorData = await response.json().catch(() => null);
                throw new Error(errorData?.message || `Error del servidor: ${response.status}`);
            }
            const data = await response.json();
            if (data.status === 'success') {
                actualizarVistaCarrito(data.producto);
                actualizarPaginaDetalle(data.producto);
                window.mostrarNotificacionGlobal(data.message, 'success');
            } else {
                window.mostrarNotificacionGlobal(data.message || 'Ocurrió un error.', 'error');
            }
        } catch (error) {
            console.error('Error al agregar al carrito:', error);
            window.mostrarNotificacionGlobal(error.message || 'Error de comunicación.', 'error');
        }
    }
    function actualizarVistaCarrito(producto) {
        const carritoItemsContainer = document.getElementById('carrito-items');
        if (!carritoItemsContainer)
            return;
        const carritoVacioMsg = document.getElementById('carrito-vacio');
        const carritoTotalEl = document.getElementById('carrito-total');
        if (carritoVacioMsg)
            carritoVacioMsg.style.display = 'none';
        const itemExistente = carritoItemsContainer.querySelector(`[data-id-gorra='${producto.idGorra}']`);
        if (itemExistente) {
            itemExistente.querySelector('.item-cantidad').textContent = `Cantidad: ${producto.cantidadTotalEnCarrito}`;
            const btnEliminar = itemExistente.querySelector('.btn-eliminar-item');
            if (btnEliminar)
                btnEliminar.dataset.cantidadActual = producto.cantidadTotalEnCarrito;
        } else {
            const itemHTML = `
                <div class="d-flex align-items-center mb-3" data-id-gorra="${producto.idGorra}">
                    <img src="${producto.imagen}" alt="${producto.nombre}" style="width: 60px; height: 60px; object-fit: cover; border-radius: 5px;">
                    <div class="ms-3 flex-grow-1">
                        <h6 class="mb-0">${producto.nombre}</h6>
                        <small class="text-muted item-cantidad">Cantidad: ${producto.cantidadTotalEnCarrito}</small>
                    </div>
                    <div class="d-flex align-items-center">
                        <strong class="me-3">Q${parseFloat(producto.precio).toFixed(2)}</strong>
                        <button class="btn btn-outline-danger btn-sm rounded-circle btn-eliminar-item" 
                                data-id-gorra="${producto.idGorra}"
                                data-nombre-gorra="${producto.nombre}"
                                data-cantidad-actual="${producto.cantidadTotalEnCarrito}"
                                title="Eliminar item">
                            <i class="bi bi-trash3"></i>
                        </button>
                    </div>
                </div>`;
            carritoItemsContainer.insertAdjacentHTML('beforeend', itemHTML);
        }
        let totalActual = parseFloat(carritoTotalEl.textContent.replace('Q', ''));
        totalActual += parseFloat(producto.precio) * producto.cantidadAgregada;
        carritoTotalEl.textContent = `Q${totalActual.toFixed(2)}`;
    }
});
document.addEventListener('DOMContentLoaded', () => {
    const contextPath = document.body.dataset.contextPath || '';
    let eliminarItemModalInstance = null;
    const carritoModalBody = document.querySelector('#miModal .modal-body');
    if (carritoModalBody) {
        carritoModalBody.addEventListener('click', (event) => {
            const eliminarBtn = event.target.closest('.btn-eliminar-item');
            if (eliminarBtn) {
                abrirModalEliminacion(eliminarBtn);
            }
        });
    }
    const confirmarEliminarBtn = document.getElementById('confirmar-eliminacion-btn');
    if (confirmarEliminarBtn) {
        confirmarEliminarBtn.addEventListener('click', procesarEliminacion);
    }
    function abrirModalEliminacion(btn) {
        const idGorra = btn.dataset.idGorra;
        const nombreGorra = btn.dataset.nombreGorra;
        const cantidadActual = parseInt(btn.dataset.cantidadActual);
        document.getElementById('nombre-item-eliminar').textContent = nombreGorra;
        document.getElementById('eliminar-id-gorra-input').value = idGorra;
        const cantidadInputEliminar = document.getElementById('eliminar-cantidad-input');
        cantidadInputEliminar.value = 1;
        cantidadInputEliminar.max = cantidadActual;
        if (!eliminarItemModalInstance) {
            eliminarItemModalInstance = new bootstrap.Modal(document.getElementById('eliminarItemModal'));
        }
        eliminarItemModalInstance.show();
    }
    function procesarEliminacion() {
        const idGorra = document.getElementById('eliminar-id-gorra-input').value;
        const cantidadAEliminar = parseInt(document.getElementById('eliminar-cantidad-input').value);
        eliminarProductoDelCarrito(idGorra, cantidadAEliminar);
        eliminarItemModalInstance.hide();
    }
    async function eliminarProductoDelCarrito(idGorra, cantidad) {
        const url = `${contextPath}/carrito/eliminar`;
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: `idGorra=${idGorra}&cantidad=${cantidad}`
            });
            if (!response.ok) {
                const errorData = await response.json().catch(() => null);
                throw new Error(errorData?.message || `Error del servidor: ${response.status}`);
            }
            const data = await response.json();
            if (data.status === 'success') {
                actualizarVistaEliminacion(data);
                window.mostrarNotificacionGlobal(data.message, 'success');
            } else {
                window.mostrarNotificacionGlobal(data.message || 'Ocurrió un error.', 'error');
            }
        } catch (error) {
            console.error('Error al eliminar del carrito:', error);
            window.mostrarNotificacionGlobal(error.message || 'Error de comunicación.', 'error');
        }
    }
    function actualizarVistaEliminacion(data) {
        const itemUI = document.querySelector(`[data-id-gorra='${data.idGorra}']`);
        if (!itemUI)
            return;
        if (data.action === 'deleted') {
            itemUI.remove();
        } else if (data.action === 'updated') {
            itemUI.querySelector('.item-cantidad').textContent = `Cantidad: ${data.nuevaCantidad}`;
            itemUI.querySelector('.btn-eliminar-item').dataset.cantidadActual = data.nuevaCantidad;
        }
        const carritoTotalEl = document.getElementById('carrito-total');
        let totalActual = parseFloat(carritoTotalEl.textContent.replace('Q', ''));
        const reduccion = data.precioUnitario * data.cantidadEliminada;
        totalActual -= reduccion;
        carritoTotalEl.textContent = `Q${totalActual.toFixed(2)}`;
        const carritoItemsContainer = document.getElementById('carrito-items');
        if (carritoItemsContainer.children.length === 0) {
            document.getElementById('carrito-vacio').style.display = 'block';
        }
    }
    if (!window.mostrarNotificacionGlobal) {
        window.mostrarNotificacionGlobal = function (mensaje, tipo) {
            const notificacion = document.createElement('div');
            notificacion.className = `notificacion ${tipo === 'success' ? 'notificacion-success' : 'notificacion-error'}`;
            notificacion.textContent = mensaje;
            document.body.appendChild(notificacion);
            setTimeout(() => notificacion.classList.add('show'), 10);
            setTimeout(() => {
                notificacion.classList.remove('show');
                setTimeout(() => notificacion.remove(), 500);
            }, 3000);
        }
    }

});