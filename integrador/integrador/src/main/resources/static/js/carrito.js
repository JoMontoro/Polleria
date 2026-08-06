// Ejecutar cuando el DOM esté cargado
document.addEventListener('DOMContentLoaded', function() {
    cargarCarrito();
    setupEventListeners();
});

function cargarCarrito() {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const contenedor = document.getElementById('items-carrito');
    let total = 0;

    // Limpiar el contenedor
    contenedor.innerHTML = '';

    // Agregar cada item al carrito
    carrito.forEach(item => {
        const subtotal = item.precio * item.cantidad;
        total += subtotal;

        contenedor.innerHTML += `
            <div class="item-carrito mb-3">
                <div class="d-flex align-items-center">
                    <img src="${item.imagen}" alt="${item.nombre}" class="item-imagen me-3" style="width: 100px; height: 100px; object-fit: cover;">
                    <div class="flex-grow-1">
                        <h5 class="mb-1">${item.nombre}</h5>
                        <p class="mb-1">Precio: S/ ${item.precio.toFixed(2)}</p>
                        <div class="d-flex align-items-center mb-2">
                            <button onclick="cambiarCantidad('${item.id}', -1)" class="btn btn-sm btn-outline-secondary me-2">-</button>
                            <span>${item.cantidad}</span>
                            <button onclick="cambiarCantidad('${item.id}', 1)" class="btn btn-sm btn-outline-secondary ms-2">+</button>
                        </div>
                        <p class="mb-0">Subtotal: S/ ${subtotal.toFixed(2)}</p>
                    </div>
                    <button onclick="eliminarItem('${item.id}')" class="btn btn-danger btn-sm ms-3">
                        <i class="fas fa-trash"></i>
                    </button>
                </div>
            </div>
            <hr>
        `;
    });

    // Actualizar el total
    document.getElementById('total').textContent = `S/ ${total.toFixed(2)}`;

    // Actualizar el contador del carrito en el header
    actualizarContadorCarrito();
}

function cambiarCantidad(id, cambio) {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const item = carrito.find(i => i.id === id);

    if (item) {
        item.cantidad = Math.max(1, item.cantidad + cambio);
        localStorage.setItem('carrito', JSON.stringify(carrito));
        cargarCarrito();
    }
}

function eliminarItem(id) {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const nuevoCarrito = carrito.filter(i => i.id !== id);
    localStorage.setItem('carrito', JSON.stringify(nuevoCarrito));
    cargarCarrito();

    // Mostrar notificación
    Swal.fire({
        title: 'Eliminado',
        text: 'Producto eliminado del carrito',
        icon: 'success',
        timer: 1500,
        showConfirmButton: false
    });
}

function actualizarContadorCarrito() {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const contador = carrito.reduce((total, item) => total + item.cantidad, 0);

    const contadorElement = document.querySelector('.cart-counter');
    if (contadorElement) {
        contadorElement.textContent = contador;
    }
}

function setupEventListeners() {
    // Mostrar/ocultar campos de factura
    const radioFactura = document.getElementById('factura');
    const radioBoleta = document.getElementById('boleta');
    const datosFactura = document.getElementById('datosFactura');

    if (radioFactura && radioBoleta && datosFactura) {
        radioFactura.addEventListener('change', function() {
            datosFactura.style.display = 'block';
        });

        radioBoleta.addEventListener('change', function() {
            datosFactura.style.display = 'none';
        });
    }

    // Mostrar/ocultar formularios según método de pago
    const metodoPagoInputs = document.querySelectorAll('input[name="metodoPago"]');
    const datosPersonales = document.getElementById('datosPersonales');
    const datosTarjeta = document.getElementById('datosTarjeta');

    metodoPagoInputs.forEach(input => {
        input.addEventListener('change', function() {
            // Siempre mostrar datos personales al seleccionar cualquier método
            datosPersonales.style.display = 'block';

            // Mostrar formulario de tarjeta solo si es pago en línea
            if (this.value === 'online') {
                datosTarjeta.style.display = 'block';
            } else {
                datosTarjeta.style.display = 'none';
            }
        });
    });

    // Botón realizar pago
    const btnPago = document.getElementById('realizar-pago');
    if (btnPago) {
        btnPago.addEventListener('click', procesarPedido);
    }
}

async function procesarPedido() {
    // Validar que haya productos en el carrito
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    if (carrito.length === 0) {
        Swal.fire({
            icon: 'error',
            title: 'Carrito vacío',
            text: 'Agregue productos al carrito antes de realizar el pedido'
        });
        return;
    }

    // Validar dirección
    const direccion = document.getElementById('direccion').value;
    if (!direccion) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor ingrese una dirección de entrega'
        });
        return;
    }

    // Validar datos personales
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const email = document.getElementById('email').value;
    const telefono = document.getElementById('telefono').value;

    if (!nombre || !apellido || !email || !telefono) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor complete todos los datos personales'
        });
        return;
    }

    // Validar formato de email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor ingrese un correo electrónico válido'
        });
        return;
    }

    // Obtener datos del formulario
    const tipoComprobante = document.querySelector('input[name="comprobante"]:checked').value;
    const metodoPago = document.querySelector('input[name="metodoPago"]:checked').value;

    // Validar datos de factura si es necesario
    if (tipoComprobante === 'factura') {
        const ruc = document.getElementById('ruc').value;
        const razonSocial = document.getElementById('razonSocial').value;

        if (!ruc || !razonSocial) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Complete los datos de facturación'
            });
            return;
        }
    }

    // Validar datos de tarjeta si es pago en línea
    if (metodoPago === 'online') {
        const numeroTarjeta = document.getElementById('numeroTarjeta').value;
        const fechaVencimiento = document.getElementById('fechaVencimiento').value;
        const cvv = document.getElementById('cvv').value;
        const nombreTarjeta = document.getElementById('nombreTarjeta').value;

        if (!numeroTarjeta || !fechaVencimiento || !cvv || !nombreTarjeta) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Por favor complete todos los datos de la tarjeta'
            });
            return;
        }

        // Validar formato de tarjeta (básico)
        if (!/^\d{16}$/.test(numeroTarjeta.replace(/\s/g, ''))) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Número de tarjeta inválido'
            });
            return;
        }

        // Validar formato de fecha MM/AA
        if (!/^\d{2}\/\d{2}$/.test(fechaVencimiento)) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Formato de fecha inválido (MM/AA)'
            });
            return;
        }

        // Validar CVV
        if (!/^\d{3,4}$/.test(cvv)) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'CVV inválido'
            });
            return;
        }
    }

    try {
        // Mostrar loading
        Swal.fire({
            title: 'Procesando pedido',
            text: 'Por favor espere...',
            allowOutsideClick: false,
            didOpen: () => {
                Swal.showLoading();
            }
        });


        // Por ahora simulamos un delay
        await new Promise(resolve => setTimeout(resolve, 2000));

        // Limpiar carrito
        localStorage.removeItem('carrito');

        // Mostrar éxito
        Swal.fire({
            icon: 'success',
            title: '¡Pedido realizado!',
            text: 'Su pedido ha sido procesado correctamente',
            confirmButtonText: 'Aceptar'
        }).then(() => {
            window.location.href = '/menu'; // Redirigir al menú
        });

    } catch (error) {
        console.error('Error al procesar el pedido:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Hubo un problema al procesar su pedido. Por favor intente nuevamente.'
        });
    }
}