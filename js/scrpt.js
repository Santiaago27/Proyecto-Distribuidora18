$(document).ready(function() {
    // Función para mostrar el contenido relacionado con Categorías de Producto
    function mostrarContenidoCategorias() {
        $('#categoriasProductoContent').show();
        $('#clientesContent, #vendedoresContent, #proveedoresContent, #pedidosContent').hide();
    }

    // Función para mostrar el contenido relacionado con Clientes
    function mostrarContenidoClientes() {
        $('#clientesContent').show();
        $('#categoriasProductoContent, #vendedoresContent, #proveedoresContent, #pedidosContent').hide();
    }


    // Función para mostrar el contenido relacionado con Vendedores
    function mostrarContenidoVendedores() {
        $('#vendedoresContent').show();
        $('#categoriasProductoContent, #clientesContent, #proveedoresContent, #pedidosContent').hide();
    }

    // Función para mostrar el contenido relacionado con Proveedores
    function mostrarContenidoProveedores() {
        $('#proveedoresContent').show();
        $('#categoriasProductoContent, #clientesContent, #vendedoresContent, #pedidosContent').hide();
    }

    // Función para mostrar el contenido relacionado con Pedidos
    function mostrarContenidoPedidos() {
      $('#pedidosContent').show();
      $('#categoriasProductoContent, #vendedoresContent, #proveedoresContent, #clientesContent').hide();
    }



    // Agregar eventos de clic a los enlaces del panel
    $('a[href="#clientes"]').click(mostrarContenidoClientes);
    $('a[href="#vendedores"]').click(mostrarContenidoVendedores);
    $('a[href="#proveedores"]').click(mostrarContenidoProveedores);
    $('a[href="#categorias_producto"]').click(mostrarContenidoCategorias);
    $('a[href="#pedidos"]').click(mostrarContenidoPedidos);

    // Resto de tus funciones para mostrar y ocultar contenido específico

    // Obtén referencias a los elementos del DOM
var openModalBtn = document.getElementById("openModalBtn");
var closeModalBtn = document.getElementById("closeModalBtn");
var modal = document.getElementById("myModal");

// Abre la ventana modal cuando se hace clic en el botón
openModalBtn.addEventListener("click", function() {
  modal.style.display = "block";
});

// Cierra la ventana modal cuando se hace clic en la "x"
closeModalBtn.addEventListener("click", function() {
  modal.style.display = "none";
});

// Cierra la ventana modal si se hace clic fuera de ella
window.addEventListener("click", function(event) {
  if (event.target === modal) {
    modal.style.display = "none";
  }
});








});