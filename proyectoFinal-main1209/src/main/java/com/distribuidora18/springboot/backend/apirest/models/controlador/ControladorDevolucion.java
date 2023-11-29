package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.Devolucion;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioDevolucion;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"GET","POST","PUT","DELETE"})
public class ControladorDevolucion {
    private ServicioDevolucion servicioDevolucion;

    public ControladorDevolucion(ServicioDevolucion servicioDevolucion) {
        this.servicioDevolucion = servicioDevolucion;
    }

    //listar
    @GetMapping("listarDevoluciones")
    public ResponseEntity<?> listarDevoluciones() {
        Map<String, Object> response = new HashMap<>();
        try {
            return new ResponseEntity<>(servicioDevolucion.allDevoluciones(), HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/agregarDevolucion/{iddetpedido}")
    public ResponseEntity<Void> insertarDevolucin(@PathVariable Long iddetpedido, @RequestBody Devolucion devolucion) {
        servicioDevolucion.addDevolucion( iddetpedido, devolucion);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("/actualizarDevolucion/{id}")
    public ResponseEntity<?> actualizarDevolucion(@PathVariable Long id, @RequestBody Devolucion devolucionActualizada) {
        Map<String, Object> response = new HashMap<>();
        try {
            Devolucion devolucionActualizadaActual = servicioDevolucion.actualizarDevolucion(id, devolucionActualizada);
            if (devolucionActualizadaActual != null) {
                return new ResponseEntity<>(devolucionActualizadaActual, HttpStatus.OK);
            } else {
                response.put("mensaje", "No se encontró la devolucion con ID " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la devolucion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("borrarsdevolucion/" + "{id}")
    public  ResponseEntity<?> borrarDevolucion(Long id){
        Map<String,Object> response= new HashMap<>();
        try {
            return new ResponseEntity<>(servicioDevolucion.deleteDevolucion(id),HttpStatus.OK);

        }catch (DataAccessException e){
            response.put("mensaje","Error al borrar en  la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}
