package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.CategoriaProducto;
import com.distribuidora18.springboot.backend.apirest.models.entity.Cliente;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioCliente;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin (origins = "*", allowedHeaders = {"GET","POST","PUT","DELETE"})
public class ControladorCliente {

    private ServicioCliente servicioCliente;
    public ControladorCliente( ServicioCliente servicioCliente){
    this.servicioCliente=servicioCliente;
    }

    //FUNCIONA
@GetMapping("/listarClientes")
    public ResponseEntity<?> listarClientes (){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioCliente.allClientes(), HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
}

@GetMapping("/buscarCliente/{id}")
public ResponseEntity<?> findByid(@PathVariable long id){
    Map<String,Object> response= new HashMap<>();
    try{
        return new ResponseEntity<>(servicioCliente.findById(id),HttpStatus.OK);
    }catch (DataAccessException e){
        response.put("mensaje","Error al Encontrar en la base de datos");
        response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

// FUNCIONA

    @PostMapping("/addcliente")
    public ResponseEntity<?> addClientes(@RequestBody Cliente cliente ){
        Map<String,Object> response= new HashMap<>();
            try{
                return new ResponseEntity<>(servicioCliente.addCliente(cliente),HttpStatus.CREATED);

            }catch (DataAccessException e){
                response.put("mensaje","Error al agregar en la base de datos");
                response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PutMapping("/actualizarCliente/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable Long id, @RequestBody Cliente nuevoCliente) {
        String resultado = servicioCliente.updateCliente(id, nuevoCliente);

        if (resultado.equals("Cliente actualizado correctamente")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


// FUNCIONA
    @DeleteMapping("/eliminarCliente/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        String resultado = servicioCliente.deleteCliente(id);

        if (resultado.equals("Cliente y sucursales relacionados eliminados en cascada")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

/*
    .
    

    @PutMapping("/actualizarDatos/{codigo}")
    public ResponseEntity<Cliente> actualizarDatosCliente(@PathVariable String nombre, @PathVariable String apellido, @RequestBody Cliente cliente) {
        Cliente c = servicioCliente.findById(no);
        if (e != null) {
            estudianteA.setCodigo(codigo);
            servicios.actualizarDatos(estudianteA);
            return new ResponseEntity<>(estudianteA, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("")

    public ResponseEntity<Void> actualizarCliente(@RequestBody Cliente cliente){
        if (servicioCliente.allClientes()!= null) {
            servicioCliente.actualizarCliente(cliente);
            return new ResponseEntity<>(HttpStatus.OK);


        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





 */



}
