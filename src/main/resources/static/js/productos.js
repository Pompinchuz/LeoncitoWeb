// src/main/resources/static/js/productos.js

// Espera a que el DOM se cargue completamente
document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('jwt');
    const headers = {
        'Content-Type': 'application/json'
    };
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    // Cargar categorías para el select (verifica si el elemento existe)
    const categoriaSelect = document.getElementById('categoriaSelect');
    if (categoriaSelect) {
        fetch('/api/categorias', {
            method: 'GET',
            headers: headers
        })
        .then(response => response.json())
        .then(data => {
            data.forEach(categoria => {
                const option = document.createElement('option');
                option.value = categoria.id;
                option.textContent = categoria.nombre;
                categoriaSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error cargando categorías:', error);
        });
    } else {
        console.warn('Elemento #categoriaSelect no encontrado. Saltando carga de categorías.');
    }

    // Cargar productos iniciales
    filtrarProductos();

    // Evento para abrir modal de imagen al hacer click en la imagen (verifica si el elemento existe)
    const productosContainer = document.getElementById('productosContainer');
    if (productosContainer) {
        productosContainer.addEventListener('click', (event) => {
            const image = event.target.closest('.card-img-top');
            if (image) {
                const modalImage = document.getElementById('modalImage');
                if (modalImage) {
                    modalImage.src = image.src;
                    const imageModal = new bootstrap.Modal(document.getElementById('imageModal'));
                    imageModal.show();
                } else {
                    console.error('Elemento #modalImage no encontrado.');
                }
            }
        });
    } else {
        console.warn('Elemento #productosContainer no encontrado. Saltando evento de imagen.');
    }
});

// Función para filtrar y cargar productos
function filtrarProductos() {
    const token = localStorage.getItem('jwt');
    const headers = {
        'Content-Type': 'application/json'
    };
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const search = document.getElementById('searchInput') ? document.getElementById('searchInput').value : '';
    const categoriaId = document.getElementById('categoriaSelect') ? document.getElementById('categoriaSelect').value : '';
    let url = '/api/productos';
    const params = new URLSearchParams();
    if (search) params.append('search', search);
    if (categoriaId) params.append('categoriaId', categoriaId);
    if (params.toString()) url += '?' + params.toString();

    fetch(url, {
        method: 'GET',
        headers: headers
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al cargar productos');
        }
        return response.json();
    })
    .then(data => {
        const container = document.getElementById('productosContainer');
        if (container) {
            container.innerHTML = '';
            data.forEach(producto => {
                const card = `
                    <div class="col-md-4 mb-4">
                        <div class="card h-100" style="background-color: #2c2c2c; color: white; border: 1px solid #444;">
                            <img src="${producto.imagenUrl}" class="card-img-top" alt="${producto.titulo}" style="height: 200px; object-fit: cover;">
                            <div class="card-body">
                                <h5 class="card-title">${producto.titulo}</h5>
                                <p class="card-text">Precio: $${producto.precio.toFixed(2)}</p>
                                <p class="card-text">Valoración: ${producto.valoracion}/5</p>
                                <button class="btn btn-primary" onclick="mostrarDescripcion('${producto.descripcion.replace(/'/g, "\\'")}')">Ver descripción</button>
                                <button class="btn btn-success" onclick="agregarAlCarrito(${producto.id})">Agregar al carrito</button>
                            </div>
                        </div>
                    </div>
                `;
                container.innerHTML += card;
            });
        } else {
            console.error('Elemento #productosContainer no encontrado. No se pueden renderizar productos.');
            alert('Error al generar los productos: Contenedor no encontrado.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('No se pudieron cargar los productos.');
    });
}

// Función para mostrar descripción en modal
function mostrarDescripcion(descripcion) {
    document.getElementById('modalDescripcion').innerText = descripcion;
    const modal = new bootstrap.Modal(document.getElementById('descripcionModal'));
    modal.show();
}


// Función para agregar al carrito (POST con token si disponible)
function agregarAlCarrito(id) {
    const token = localStorage.getItem('jwt');
    if (!token) {
        alert('Inicia sesión para agregar al carrito.');
        window.location.href = '/login';
        return;
    }

    fetch(`/api/carrito/${id}`, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ cantidad: 1 })  // Asume cantidad 1, ajusta si necesitas input
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al agregar al carrito');
        }
        alert('Producto agregado al carrito.');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('No se pudo agregar al carrito.');
    });
}