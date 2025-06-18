document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const categoriaSeleccionada = urlParams.get('categoria');

    if (categoriaSeleccionada) {
        // Establecer el valor del select
        const select = document.getElementById('categoriaFilter');
        select.value = categoriaSeleccionada;

        // Aplicar el filtro
        filtrarProductos(categoriaSeleccionada);
    }

    // Agregar listener para cambiar de categoría manualmente
    document.getElementById('categoriaFilter').addEventListener('change', function() {
        filtrarProductos(this.value);
    });
});

function filtrarProductos(categoriaSeleccionada) {
    fetch(`/productos?categoria=${categoriaSeleccionada}`)
        .then(response => response.json())
        .then(data => {
            const contenedorProductos = document.getElementById('productos-container');
            contenedorProductos.innerHTML = '';

            data.forEach(producto => {
                const productoHTML = `
                <div class="col-12 col-md-6 col-xl-4 d-flex justify-content-center producto-card">
                    <div class="card product-card" data-id="${producto.producto_id}">
                        <img src="/img/uploads/${producto.imagen_url}" class="product-img" alt="Imagen del producto">
                        <div class="card-body text-center">
                            <h5 class="product-title">${producto.nombre_producto}</h5>
                            <p class="product-description">${producto.descripcion}</p>
                            <p class="product-price">S/ ${producto.precio}</p>
                            <button class="btn btn-cart" onclick="agregarAlCarrito(this)">
                                <i class="fas fa-shopping-cart"></i>
                            </button>
                        </div>
                    </div>
                </div>`;
                contenedorProductos.innerHTML += productoHTML;
            });
        })
        .catch(error => console.error('Error al cargar productos:', error));
}

