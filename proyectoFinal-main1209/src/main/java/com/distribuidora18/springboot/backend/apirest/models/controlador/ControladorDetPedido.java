package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.DetallePedido;
import com.distribuidora18.springboot.backend.apirest.models.entity.Pedido;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioDetallePedido;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioPedido;
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

    @PostMapping("/agregarDetPedido/{idproductos}/{idpedidos}")
    public ResponseEntity<Void> insertarDetallePedido(@PathVariable Long idproductos, @PathVariable Long idpedidos, @RequestBody DetallePedido detallepedido) {
        servicioDetallePedido.addDetallePedido(idproductos, idpedidos, detallepedido);
        return new ResponseEntity<>(HttpStatus.OK);
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