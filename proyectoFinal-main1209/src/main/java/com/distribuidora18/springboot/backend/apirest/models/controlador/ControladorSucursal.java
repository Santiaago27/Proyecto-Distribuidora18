package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.Sucursal;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioCliente;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioSucursal;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"GET","POST","PUT","DELETE"})
public class ControladorSucursal {

    // se instancia el servicio
    private ServicioSucursal servicioSucursal;

    public ControladorSucursal(ServicioSucursal servicioSucursal) {
        this.servicioSucursal = servicioSucursal;
    }


    // Listar Sucursales
    @GetMapping("listarsucursales")
    public ResponseEntity<?> listarSucursales() {
        Map<String, Object> response = new HashMap<>();
        try {
            return new ResponseEntity<>(servicioSucursal.allSucursales(), HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/buscarSucursal/{idsucursal}")
    public ResponseEntity<?> findByid(@PathVariable long idsucursal){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioSucursal.findById(idsucursal),HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al Encontrar en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/actualizarSucursal/{id}")
    public ResponseEntity<?> actualizarSucursal(@PathVariable Long id, @RequestBody Sucursal sucursalActualizada) {
        Map<String, Object> response = new HashMap<>();
        try {
            Sucursal sucursalActualizadaActual = servicioSucursal.actualizarSucursal(id, sucursalActualizada);
            if (sucursalActualizadaActual != null) {
                return new ResponseEntity<>(sucursalActualizadaActual, HttpStatus.OK);
            } else {
                response.put("mensaje", "No se encontró la sucursal con ID " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la sucursal en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("borrarsucursal/" + "{id}")
    public  ResponseEntity<?> borrarSucursal(Long id){
        Map<String,Object> response= new HashMap<>();
        try {
            return new ResponseEntity<>(servicioSucursal.deleteSucursal(id),HttpStatus.OK);

        }catch (DataAccessException e){
            response.put("mensaje","Error al borrar en  la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping("/agregarSucursal/{idcliente}")
    public ResponseEntity<Void> insertarSucursal(@PathVariable Long idcliente, @RequestBody Sucursal sucursal) {
        servicioSucursal.addSucursal( idcliente, sucursal);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
