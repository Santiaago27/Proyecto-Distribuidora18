package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.Pedido;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioPedido;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"GET","POST","PUT","DELETE"})

public class ControladorPedido {

    private ServicioPedido servicioPedido;
    public ControladorPedido(ServicioPedido servicioPedido){
        this.servicioPedido=servicioPedido;}



    // Listar Pedido
    @GetMapping("listarpedidos")
    public ResponseEntity<?> listarPedidos(){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioPedido.allPedido(), HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/agregarPedido/{idsucursal}/{idvendedor}")
    public ResponseEntity<Void> insertarPedido(@PathVariable Long idsucursal, @PathVariable Long idvendedor, @RequestBody Pedido pedido) {
        servicioPedido.addPedido( idsucursal, idvendedor, pedido);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("borrarpedido/{id}")
    public  ResponseEntity<?> borrarPedido(Long id){
        Map<String,Object> response= new HashMap<>();
        try {
            return new ResponseEntity<>(servicioPedido.deletePedido(id),HttpStatus.OK);

        }catch (DataAccessException e){
            response.put("mensaje","Error al borrar en  la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }
}
