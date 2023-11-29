package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.Producto;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioProducto;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"GET","POST","PUT","DELETE"})

public class ControladorProductos {

    private ServicioProducto servicioProducto;
    public ControladorProductos(ServicioProducto servicioProducto){
        this.servicioProducto=servicioProducto;}



    // Listar Producto
    @GetMapping("/listarproductos")
    public ResponseEntity<?> listarProductos(){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioProducto.allProducto(), HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/agregarProducto/{idcategoria}")
    public ResponseEntity<Void> insertarProducto(@PathVariable Long idcategoria, @RequestBody Producto producto) {
        servicioProducto.addProducto( idcategoria, producto);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("/actualizarProducto/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto productoActualizadoActual = servicioProducto.updateProducto(id, productoActualizado);
            if (productoActualizadoActual != null) {
                return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
            } else {
                response.put("mensaje", "No se encontró el producto con ID " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el producto en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("borrarproducto/{id}")
    public  ResponseEntity<?> borrarProducto(Long id){
        Map<String,Object> response= new HashMap<>();
        try {
            return new ResponseEntity<>(servicioProducto.deleteProducto(id),HttpStatus.OK);

        }catch (DataAccessException e){
            response.put("mensaje","Error al borrar en  la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}
