$(document).ready(function(){

    $('#btnConsultarCategorias').click(function(event) {
        let tabla = document.getElementById('tablaCategorias')
        tabla.innerHTML = ''
        event.preventDefault();
        $.ajax({
        url: 'http://localhost:8080/listarCategorias',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
        tabla.innerHTML ='';
        for (i = 0; i <= response.length; i++){
        tabla.innerHTML += '<tr><td>' + response[i].id_categoria +
        '</td><td>' + response[i].nombre +
        '</td><td>' + response[i].descripcion +
        '</td><td><div class="text-center"><div class="btn-group"><button class="btnEditar">Editar<svg class="svg" viewBox="0 0 512 512"><path d="M410.3 231l11.3-11.3-33.9-33.9-62.1-62.1L291.7 89.8l-11.3 11.3-22.6 22.6L58.6 322.9c-10.4 10.4-18 23.3-22.2 37.4L1 480.7c-2.5 8.4-.2 17.5 6.1 23.7s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L387.7 253.7 410.3 231zM160 399.4l-9.1 22.7c-4 3.1-8.5 5.4-13.3 6.9L59.4 452l23-78.1c1.4-4.9 3.8-9.4 6.9-13.3l22.7-9.1v32c0 8.8 7.2 16 16 16h32zM362.7 18.7L348.3 33.2 325.7 55.8 314.3 67.1l33.9 33.9 62.1 62.1 33.9 33.9 11.3-11.3 22.6-22.6 14.5-14.5c25-25 25-65.5 0-90.5L453.3 18.7c-25-25-65.5-25-90.5 0zm-47.4 168l-144 144c-6.2 6.2-16.4 6.2-22.6 0s-6.2-16.4 0-22.6l144-144c6.2-6.2 16.4-6.2 22.6 0s6.2 16.4 0 22.6z"></path></svg></button><button class="noselect"><span class="text">Delete</span><span class="icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"></path></svg></span></button></div></div></td></tr>';
        }
        
        $('#resultadosConsultaCategorias').html(JSON.stringify(response));
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


        $("#enviarCategoria").click(function(e) {
            e.preventDefault();
            
            var id_categoria = $("#id_categoria").val();
            var nombreCat = $("#nombreCat").val();
            var descripcion = $("#descripcion").val();
        
            var data = {
                id_categoria: id_categoria,
                nombre: nombreCat,
                descripcion: descripcion,

            };
            console.log(data)
                      
            $.ajax({
                url: "http://localhost:8080/addCategoria",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                beforeSend: function() {
                $("#error-message").text("");   
                $("#mensaje").html("");
                },
                success: function(data) {
                    console.log(data);
                
                                    "id_categoria: " + data.id_categoria + "<br>" +
                                    "nombreCat: " + data.nombre + "<br>" +
                                    "descripcion: " + data.descripcion + "<br>" +
                                    
                alert("Los datos fueron registrados exitosamente");
                $("#id_categoria").val(""); 
                $("#nombreCat").val(""); 
                $("#descripcion").val("");
                 
                
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
    


    
})