// Función para agregar productos al carrito
function agregarAlCarrito(boton) {
    const card = boton.closest('.product-card');
    const producto = {
        id: card.getAttribute('data-id'),
        nombre: card.querySelector('.product-title').textContent,
        precio: parseFloat(card.querySelector('.product-price').textContent.replace('S/ ', '')),
        imagen: card.querySelector('.product-img').src,
        cantidad: 1
    };

    console.log('Producto a agregar:', producto); // Debug

    // Obtener el carrito actual del localStorage
    let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

    // Verificar si el producto ya está en el carrito
    const productoExistente = carrito.find(item => item.id === producto.id);

    if (productoExistente) {
        productoExistente.cantidad++;
    } else {
        carrito.push(producto);
    }

    // Guardar el carrito actualizado
    localStorage.setItem('carrito', JSON.stringify(carrito));

    // Mostrar notificación
    mostrarNotificacion('Producto agregado al carrito');

    // Actualizar el contador del carrito
    actualizarContadorCarrito();
}

// Función para mostrar notificación
function mostrarNotificacion(mensaje) {
    Swal.fire({
        title: '¡Éxito!',
        text: mensaje,
        icon: 'success',
        timer: 1500,
        showConfirmButton: false
    });
}

// Función para actualizar el contador del carrito
function actualizarContadorCarrito() {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const contador = carrito.reduce((total, item) => total + item.cantidad, 0);

    const contadorElement = document.querySelector('.cart-counter');
    if (contadorElement) {
        contadorElement.textContent = contador;
    }
}

// Función para filtrar productos por categoría
document.addEventListener('DOMContentLoaded', function() {
    const categoriaFilter = document.getElementById('categoriaFilter');
    if (categoriaFilter) {
        categoriaFilter.addEventListener('change', function() {
            const categoriaSeleccionada = this.value;
            const productos = document.querySelectorAll('.producto-card');

            productos.forEach(producto => {
                const categoriaProducto = producto.getAttribute('data-categoria');
                if (!categoriaSeleccionada || categoriaProducto === categoriaSeleccionada) {
                    producto.style.display = 'flex';
                } else {
                    producto.style.display = 'none';
                }
            });
        });
    }

    // Inicializar el contador del carrito
    actualizarContadorCarrito();
});