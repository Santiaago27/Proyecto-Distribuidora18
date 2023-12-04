package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.DetalleCompraProveedor;
import com.distribuidora18.springboot.backend.apirest.models.entity.Pedido;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioDetalleCompraProveedor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"GET","POST","PUT","DELETE"})
public class ControladorDetCompraProv {

    private ServicioDetalleCompraProveedor serviciodetallecompraproveedor;

    public ControladorDetCompraProv(ServicioDetalleCompraProveedor serviciodetallecompraproveedor) {
        this.serviciodetallecompraproveedor = serviciodetallecompraproveedor;
    }

    @GetMapping("listardetallecompraproveedor")
    public ResponseEntity<?> listarDetalleCompraProveedor(){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(serviciodetallecompraproveedor.allDetCompra(), HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/agregarDetalleCompraProveedor/{idproveedor}/{idproducto}")
    public ResponseEntity<Void> insertarDetalleCompraProveedor(@PathVariable Long idproveedor, @PathVariable Long idproducto, @RequestBody DetalleCompraProveedor detallecompraproveedor) {
        serviciodetallecompraproveedor.addDetCompra(idproveedor, idproducto, detallecompraproveedor);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
