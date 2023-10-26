$(document).ready(function() {
    
    // CONSULTAR LISTA DE CLIENTES  //

    $('#btnConsultarClientes').click(function(event) {
        let tabla = document.getElementById('tabla_body')
        tabla.innerHTML = ''
        event.preventDefault();
        $.ajax({
        url: 'http://localhost:8080/ControladorCliente/listarClientes',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
        tabla.innerHTML ='';
        for (i = 0; i <= response.length; i++){
        tabla.innerHTML += '<tr><td>' + response[i].identificacion +
        '</td><td>' + response[i].id_cliente +
        '</td><td>' + response[i].nombre +
        '</td><td>' + response[i].apellido +
        '</td><td>' + response[i].email +
        '</td><td>' + response[i].telefono +
        '</td><td><div class="text-center"><div class="btn-group"><button class="btnEditar">Editar<svg class="svg" viewBox="0 0 512 512"><path d="M410.3 231l11.3-11.3-33.9-33.9-62.1-62.1L291.7 89.8l-11.3 11.3-22.6 22.6L58.6 322.9c-10.4 10.4-18 23.3-22.2 37.4L1 480.7c-2.5 8.4-.2 17.5 6.1 23.7s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L387.7 253.7 410.3 231zM160 399.4l-9.1 22.7c-4 3.1-8.5 5.4-13.3 6.9L59.4 452l23-78.1c1.4-4.9 3.8-9.4 6.9-13.3l22.7-9.1v32c0 8.8 7.2 16 16 16h32zM362.7 18.7L348.3 33.2 325.7 55.8 314.3 67.1l33.9 33.9 62.1 62.1 33.9 33.9 11.3-11.3 22.6-22.6 14.5-14.5c25-25 25-65.5 0-90.5L453.3 18.7c-25-25-65.5-25-90.5 0zm-47.4 168l-144 144c-6.2 6.2-16.4 6.2-22.6 0s-6.2-16.4 0-22.6l144-144c6.2-6.2 16.4-6.2 22.6 0s6.2 16.4 0 22.6z"></path></svg></button><button class="noselect"><span class="text">Delete</span><span class="icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"></path></svg></span></button></div></div></td></tr>';
        }
        
        $('#resultadosConsultaClientes').html(JSON.stringify(response));
        console.log(typeof response)
        },
        error: function(xhr, status, error) {
        console.log(xhr);
        console.log(status);
        console.log(error);
        }
        });
        return false;
        });

//....................CONSULTAR CLIENTE ...............................//

$('#btnBuscarCliente').click(function(event) {
    var idCli = $('#clienteId').val();
    event.preventDefault();
    $.ajax({
    url: 'http://localhost:8080/ControladorCliente/buscarCliente/' + idCli,
    type: 'GET',
    dataType: 'json',
    success: function(response) {
        let tabla = document.getElementById('tabla_body')
        tabla.innerHTML ='';
        for (i = 0; i <= response.length; i++){
        tabla.innerHTML += '<tr><td>' + response[i].identificacion +
        '</td><td>' + response[i].id_cliente +
        '</td><td>' + response[i].nombre +
        '</td><td>' + response[i].apellido +
        '</td><td>' + response[i].email +
        '</td><td>' + response[i].telefono +
        '</td></tr>';
        }
    
    $('#resultadosConsultaClientes').html(JSON.stringify(response));
    console.log(typeof response)
    },
    error: function(xhr, status, error) {
    console.log(xhr);
    console.log(status);
    console.log(error);
    }
    });
    return false;
    });

    $('#clienteId').on('input', function() {
        let valorBusqueda = $(this).val().toLowerCase();
        let tabla = document.getElementById('tabla_body');
        let filas = tabla.getElementsByTagName('tr');
    
        for (let i = 0; i < filas.length; i++) {
            let celdas = filas[i].getElementsByTagName('td');
            let filaCoincide = false;
    
            for (let j = 0; j < celdas.length; j++) {
                let textoCelda = celdas[j].textContent.toLowerCase();
                if (textoCelda.includes(valorBusqueda)) {
                    filaCoincide = true;
                    break;
                }
            }
    
            if (filaCoincide) {
                filas[i].style.display = '';
            } else {
                filas[i].style.display = 'none';
            }
        }
    });
              

 //////////////////////////////////// agregar cliente //////////////////////////////

    $("#enviar").click(function(e) {
        e.preventDefault();
        
                  
        var listaDesplegable = document.getElementById("tipoDoc");
        var valorSeleccionado = listaDesplegable.value;
        var id_cliente = $("#id_cliente").val();
        var nombreC = $("#nombreC").val();
        var apellidoC = $("#apellido").val();
        var correo = $("#correo").val();
        var telefono = $("#telefono").val();

       if (valorSeleccionado === "-Elige una opción-") {
        alert("Por favor, elige un tipo de documento válido.");
        return; 
    }

        console.log("valorSeleccionado: " + valorSeleccionado);
        console.log("id_cliente: " + id_cliente);
        console.log("nombreC: " + nombreC);
        console.log("apellidoC: " + apellidoC);
        console.log("correo: " + correo);
        console.log("telefono: " + telefono);
                  
        var data = {
            identificacion: valorSeleccionado,
            id_cliente : id_cliente,
            nombre: nombreC,
            apellido: apellidoC,
            email: correo,
            telefono: telefono
        };
                  
        $.ajax({
            url: "http://localhost:8080/ControladorCliente/addcliente",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function() {
            $("#error-message").text("");   
            $("#mensaje").html("");
            },
            success: function(data) {
                console.log(data);
            
                                "identificacion: " + data.identificacion + "<br>" +
                                "idCliente: " + data.id_cliente + "<br>" +
                                "nombreC: " + data.nombre + "<br>" +
                                "apellido: " + data.apellido + "<br>" +
                                "email: " + data.email + "<br>" +
                                "telefono: " + data.telefono + "<br>";
                  
            alert("Los datos fueron registrados exitosamente");
            $("#tipoDoc").val("-Elige una opción-"); 
            $("#id_cliente").val(""); 
            $("#nombreC").val(""); 
            $("#apellido").val("");
            $("#correo").val(""); 
            $("#telefono").val(""); 
            
            },
            error: function(xhr, status, error) {
            if (xhr.status === 409) {
                $("#error-message").text("error");
            } else {
                    var errorMessage = xhr.responseText || "Internal Server Error";
                    $("#error-message").text(errorMessage);
                }
                console.log(status);
                console.log(error);
                }
            });
            });
                  

      // Agregar evento de escucha para el botón "Editar"

      $(document).on('click', '#btnEditar', function() {
       
    var fila = $(this).closest('tr');
    var idCliente = parseInt(fila.find('td:eq(1)').text());
    var identificacion = fila.find('td:eq(0)').text();
    var nombre = fila.find('td:eq(2)').text();
    var apellido = fila.find('td:eq(3)').text();
    var email = fila.find('td:eq(4)').text();
    var telefono = fila.find('td:eq(5)').text();

    $("#tipoDoc").val(identificacion);
    $("#id_cliente").val(idCliente);
    $("#nombre").val(nombre);
    $("#apellido").val(apellido);
    $("#correo").val(email);
    $("#telefono").val(telefono);

    $("#staticBackdropLabel").text("Editar Cliente");
    $("#enviar").text("Guardar Cambios");
    $("#enviar").attr("id", "guardarCambios"); // Cambia el ID del botón

    // Abre la ventana modal
    $('#staticBackdrop').modal('show');
});

$('#guardarCambios').click(function()  {
    var idCliente = $("#id_cliente").val();
    var identificacion = $("#tipoDoc").val();
    var nombre = $("#nombre").val();
    var apellido = $("#apellido").val();
    var email = $("#correo").val();
    var telefono = $("#telefono").val();

    var datosCliente = {
        idCliente: idCliente,
        identificacion: identificacion,
        nombre: nombre,
        apellido: apellido,
        email: email,
        telefono: telefono
    };

    console.log(datosCliente)

    $.ajax({
        type: 'POST', 
        url: 'http://localhost:8080/ControladorCliente/actualizarCliente'/idCliente, 
        data: datosCliente,
        success: function(response) {
            if (response === 'exito') {
                alert('Cliente actualizado con éxito.');
                $('#staticBackdrop').modal('hide');
            } else {
                alert('Error al actualizar el cliente.');
            }
        }
    });
});




// --------------------------- Eliminar Cliente --------------------//



// Agregar evento de escucha para el botón "Eliminar"
$(document).on('click', '.noselect', function() {

    fila = $(this).closest('tr');
    idCliente = parseInt(fila.find('td:eq(1)').text());
    $.ajax({
        url: 'http://localhost:8080/ControladorCliente/eliminarCliente/' + idCliente,
        type: 'DELETE',
        datatype: 'JSON',
        success: function() {
            console.log("ID del cliente a eliminar: " + idCliente);

            alert("cliente elminado")
            // Realizar acciones después de la eliminación, como actualizar la tabla.
        },
        error: function(xhr, status, error) {
            alert("Error al eliminar el cliente: " + error);
        }
        
    });



});




})







    
