document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('formularioPago');
    const nombreInput = document.getElementById('nombre');
    const apellidoInput = document.getElementById('apellido');
    const emailInput = document.getElementById('email');
    const telefonoInput = document.getElementById('telefono');
    const numeroTarjetaInput = document.getElementById('numeroTarjeta');
    const fechaVencimientoInput = document.getElementById('fechaVencimiento');
    const cvvInput = document.getElementById('cvv');

    // Validación del campo "nombre" y "apellido" (solo letras)
    [nombreInput, apellidoInput].forEach(input => {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ\s'-]/g, '');
        });
    });

    // Validación del campo "correo electrónico"
    emailInput.addEventListener('input', function () {
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,}$/; // Patrones válidos
        if (!emailPattern.test(this.value)) {
            this.setCustomValidity('El correo debe ser válido y no contener caracteres especiales como *#.');
        } else {
            this.setCustomValidity('');
        }
    });


    // Validación del campo "teléfono" (9 dígitos y debe empezar con 9)
    telefonoInput.addEventListener('input', function () {
        this.value = this.value.replace(/[^0-9]/g, ''); // Eliminar caracteres no numéricos
        if (this.value.length > 9) {
            this.value = this.value.slice(0, 9); // Limitar a 9 dígitos
        }
        if (!/^9\d{0,8}$/.test(this.value)) { // Permite hasta 9 dígitos, comenzando con 9
            this.setCustomValidity('El número debe comenzar con 9 y tener exactamente 9 dígitos.');
        } else {
            this.setCustomValidity('');
        }
    });


    // Validación y formateo del número de tarjeta
    numeroTarjetaInput.addEventListener('input', function () {
        let value = this.value.replace(/\D/g, ''); // Eliminar caracteres no numéricos

        // Limitar la longitud dependiendo del tipo de tarjeta
        if (value.startsWith('3')) { // American Express
            if (value.length > 15) {
                value = value.slice(0, 15); // Limitar a 15 dígitos
            }
        } else { // Visa, MasterCard, Discover
            if (value.length > 16) {
                value = value.slice(0, 16); // Limitar a 16 dígitos
            }
        }

        // Formatear con espacios cada 4 dígitos
        this.value = value.replace(/(\d{4})(?=\d)/g, '$1 ');

        // Identificación del tipo de tarjeta
        const tarjetaIcono = document.getElementById('tarjetaIcono');
        tarjetaIcono.style.display = 'none'; // Ocultar íconos por defecto
        tarjetaIcono.src = ''; // Limpiar imagen

        // Determinar el tipo de tarjeta y mostrar el icono
        if (value.startsWith('4')) {
            tarjetaIcono.src = '/img/visa.png'; // Ruta al ícono de Visa
            tarjetaIcono.style.display = 'inline'; // Mostrar ícono
        } else if (value.startsWith('5')) {
            tarjetaIcono.src = '/img/mastercard.png'; // Ruta al ícono de MasterCard
            tarjetaIcono.style.display = 'inline'; // Mostrar ícono
        } else if (value.startsWith('3')) {
            tarjetaIcono.src = '/img/american.png'; // Ruta al ícono de American Express
            tarjetaIcono.style.display = 'inline'; // Mostrar ícono
        }

        // Validación del número de tarjeta
        if (value.length === 16 && !/^\d{16}$/.test(value)) {
            this.setCustomValidity('El número de tarjeta debe tener exactamente 16 dígitos.');
        } else if (value.length === 15 && !/^\d{15}$/.test(value)) {
            this.setCustomValidity('El número de tarjeta debe tener exactamente 15 dígitos para American Express.');
        } else {
            this.setCustomValidity('');
        }
    });



    // Validación y formateo de fecha de vencimiento (MM/AA)
    fechaVencimientoInput.addEventListener('input', function () {
        let value = this.value.replace(/\D/g, ''); // Solo números
        if (value.length >= 2) {
            value = value.slice(0, 2) + '/' + value.slice(2, 4); // Formatear como MM/AA
        }
        if (value.length > 5) {
            value = value.slice(0, 5); // Limitar a 5 caracteres
        }
        this.value = value;

        // Validar fecha
        const [mes, año] = value.split('/').map(Number);
        const fechaActual = new Date();
        const añoActual = parseInt(fechaActual.getFullYear().toString().slice(-2));
        const mesActual = fechaActual.getMonth() + 1;

        if (mes < 1 || mes > 12 || año < añoActual || (año === añoActual && mes < mesActual)) {
            this.setCustomValidity('Ingrese una fecha válida (MM/AA).');
        } else {
            this.setCustomValidity('');
        }
    });

    // Validación del campo CVV
    cvvInput.addEventListener('input', function () {
        const cardNumber = numeroTarjetaInput.value.replace(/\D/g, '');
        const isAmex = cardNumber.startsWith('3'); // American Express
        const maxLength = isAmex ? 4 : 3; // CVV de Amex es de 4 dígitos
        let value = this.value.replace(/\D/g, ''); // Solo números
        if (value.length > maxLength) {
            value = value.slice(0, maxLength); // Limitar al máximo permitido
        }
        this.value = value;

        if (value.length !== maxLength) {
            this.setCustomValidity(`El CVV debe tener ${maxLength} dígitos.`);
        } else {
            this.setCustomValidity('');
        }
    });

    // Validación final al enviar el formulario
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        // Validar todos los campos
        const errores = [];
        if (!nombreInput.value.trim()) errores.push('El nombre es obligatorio.');
        if (!apellidoInput.value.trim()) errores.push('El apellido es obligatorio.');
        if (!emailInput.validity.valid) errores.push('El correo no es válido.');
        if (!telefonoInput.validity.valid) errores.push('El teléfono no es válido.');
        if (!numeroTarjetaInput.value.trim()) errores.push('El número de tarjeta es obligatorio.');
        if (!fechaVencimientoInput.validity.valid) errores.push('La fecha de vencimiento no es válida.');
        if (!cvvInput.validity.valid) errores.push('El CVV no es válido.');

        if (errores.length > 0) {
            Swal.fire('Errores en el formulario', errores.join('<br>'), 'error');
            return;
        }

        // Si todo es válido
        Swal.fire('Formulario enviado', 'El formulario se ha enviado correctamente.', 'success');
        form.submit();
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const btnRealizarPago = document.getElementById('realizar-pago');

    btnRealizarPago.addEventListener('click', function (event) {
        event.preventDefault(); // Evita que el formulario se envíe antes de realizar las validaciones

        // Obtener los datos del formulario
        const direccion = document.getElementById('direccion').value;
        const referencia = document.getElementById('referencia').value;
        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const email = document.getElementById('email').value;
        const telefono = document.getElementById('telefono').value;
        const metodoPago = document.querySelector('input[name="metodoPago"]:checked').value;
        const comprobante = document.querySelector('input[name="comprobante"]:checked').value;

        // Validar si el pago es con boleta y en efectivo
        if (comprobante === 'boleta' && metodoPago === 'efectivo') {
            // Crear el PDF
            const { jsPDF } = window.jspdf;
            const doc = new jsPDF();

            // Ruta al logo dentro del proyecto
            const logoPath = '/img/Logo_Gian.png'; // Asegúrate de que esta ruta sea correcta
            const imgX = 10, imgY = 10, imgWidth = 30, imgHeight = 30;

            // Cargar el logo desde el proyecto local
            const loadImage = (path) => {
                return new Promise((resolve, reject) => {
                    const img = new Image();
                    img.src = path;
                    img.onload = () => resolve(img);
                    img.onerror = (err) => reject(err);
                });
            };

            // Generar el PDF tras cargar el logo
            loadImage(logoPath)
                .then((logo) => {
                    // Agregar el logo al PDF
                    doc.addImage(logo, 'PNG', imgX, imgY, imgWidth, imgHeight);

                    // **Encabezado**
                    doc.setFontSize(16);
                    doc.setFont("Helvetica", "bold");
                    doc.text("GIANCARLO EIRL", 50, 20);
                    doc.setFontSize(12);
                    doc.text("Av. Siempre Viva 123, Ciudad X", 50, 28);
                    doc.text("R.U.C. 20530241905", 50, 34);
                    doc.setFontSize(14);
                    doc.text("BOLETA", 170, 20);
                    doc.setFontSize(12);
                    doc.text("001-503", 170, 28);

                    // **Datos del Cliente**
                    doc.setFillColor(230, 230, 230);
                    const rectHeight = referencia ? 40 : 30; // Ajusta la altura del rectángulo según la referencia
                    doc.rect(10, 45, 190, rectHeight, 'F');
                    let textY = 55;
                    doc.setFont("Helvetica", "normal");
                    doc.text(`Señor (es): ${nombre} ${apellido}`, 12, textY);
                    textY += 6;
                    doc.text(`Email: ${email}`, 12, textY);
                    textY += 6;
                    doc.text(`Teléfono: ${telefono}`, 12, textY);
                    textY += 6;
                    doc.text(`Dirección destino: ${direccion}`, 12, textY);
                    if (referencia) {
                        textY += 6;
                        doc.text(`Referencia: ${referencia}`, 12, textY);
                    }

                    // **Detalle de Productos**
                    doc.setLineWidth(0.5);
                    doc.line(10, 85, 200, 85); // Línea superior de la tabla
                    doc.setFontSize(10);
                    doc.setFont("Helvetica", "bold");
                    doc.text("CANTIDAD", 12, 90);
                    doc.text("DESCRIPCIÓN", 60, 90);
                    doc.text("PRECIO", 150, 90);
                    doc.text("TOTAL", 180, 90);
                    doc.line(10, 95, 200, 95); // Línea inferior de la cabecera de la tabla

                    // Obtener detalles del carrito
                    const itemsCarrito = JSON.parse(localStorage.getItem('carrito')) || [];
                    let y = 100; // Posición inicial de las filas
                    doc.setFont("Helvetica", "normal");
                    itemsCarrito.forEach((item, index) => {
                        doc.text(`${item.cantidad}`, 12, y);
                        doc.text(`${item.nombre}`, 60, y);
                        doc.text(`S/ ${item.precio.toFixed(2)}`, 150, y);
                        doc.text(`S/ ${(item.precio * item.cantidad).toFixed(2)}`, 180, y);
                        y += 10; // Incrementar posición para la siguiente fila
                    });

                    doc.line(10, y, 200, y); // Línea inferior de la tabla

                    // **Otros Datos**
                    const total = itemsCarrito.reduce((acc, item) => acc + (item.precio * item.cantidad), 0);
                    doc.setFont("Helvetica", "bold");
                    doc.text(`Total a pagar: S/ ${total.toFixed(2)}`, 150, y + 10);

                    // **Comentario**
                    doc.text("Gracias por su compra", 98, y + 45);

                    // Descargar el PDF
                    doc.save('Boleta.pdf');
                })
                .catch((error) => {
                    console.error("Error al cargar el logo:", error);
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'No se pudo cargar el logo. Por favor, verifica la ruta.'
                    });
                });
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error de Pago',
                text: 'Debe seleccionar "Boleta" y "Efectivo" para generar la boleta.'
            });
        }
    });
});



