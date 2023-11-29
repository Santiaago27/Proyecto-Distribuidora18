package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.DetallePedido;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioDetallePedido;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin (origins = "*", allowedHeaders = {"GET","POST","PUT","DELETE"})

public class ControladorDetPedido {
    private ServicioDetallePedido servicioDetallePedido;

    public ControladorDetPedido(ServicioDetallePedido servicioDetallePedido) {
        this.servicioDetallePedido = servicioDetallePedido;
    }


    // Listar Pedido
    @GetMapping("listardetallepedido")
    public ResponseEntity<?> listarDetallePedido() {
        Map<String, Object> response = new HashMap<>();
        try {
            return new ResponseEntity<>(servicioDetallePedido.allDetallePedido(), HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/buscarDetPedido/{idDetpedido}")
    public ResponseEntity<?> findByid(@PathVariable long idDetpedido){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioDetallePedido.findById(idDetpedido),HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al Encontrar en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregarDetPedido/{idproductos}/{idpedidos}")
    public ResponseEntity<Void> insertarDetallePedido(@PathVariable Long idproductos, @PathVariable Long idpedidos, @RequestBody DetallePedido detallepedido) {
        servicioDetallePedido.addDetallePedido(idproductos, idpedidos, detallepedido);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/actualizarPedido/{iddetallepedido}")
    public ResponseEntity<?> actualizarDetallePedido(@PathVariable Long iddetallepedido, @RequestBody DetallePedido detallepedidoActualizado) {
        Map<String, Object> response = new HashMap<>();

        try {
            DetallePedido detallepedido = servicioDetallePedido.actualizarDetallePedido(iddetallepedido, detallepedidoActualizado);
            if (detallepedido != null) {
                return new ResponseEntity<>(detallepedido, HttpStatus.OK);
            } else {
                response.put("mensaje", "Pedido no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @DeleteMapping("borrardetallepedido/{id}")
    public ResponseEntity<?> borrarPedido(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            return new ResponseEntity<>(servicioDetallePedido.deleteDetallePedido(id), HttpStatus.OK);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al borrar en  la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}