package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.Cliente;
import com.distribuidora18.springboot.backend.apirest.models.entity.Proveedor;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioCliente;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioProveedor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"GET","POST","PUT","DELETE"})
public class ControladorProveedor {

    private ServicioProveedor servicioProveedor;
    public ControladorProveedor( ServicioProveedor servicioProveedor){
        this.servicioProveedor=servicioProveedor;
    }

    @GetMapping("/listarProveedores")
    public ResponseEntity<?> listarProveedores (){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioProveedor.allProveedores(), HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarProveedor/{id}")
    public ResponseEntity<?> findByid(@PathVariable long id){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioProveedor.findById(id),HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al Encontrar en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

// FUNCIONA

    @PostMapping("/addproveedor")
    public ResponseEntity<?> addProveedores(@RequestBody Proveedor proveedor ){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioProveedor.addProveedor(proveedor),HttpStatus.CREATED);

        }catch (DataAccessException e){
            response.put("mensaje","Error al agregar en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizarProveedor/{id}")
    public ResponseEntity<String> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor nuevoProveedor) {
        String resultado = servicioProveedor.updateProveedor(id, nuevoProveedor);

        if (resultado.equals("Proveedor actualizado correctamente")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // FUNCIONA
    @DeleteMapping("/eliminarProveedor/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Long id) {
        String resultado = servicioProveedor.deleteProveedor(id);

        if (resultado.equals("Proveedor y detalle relacionados eliminados en cascada")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
