package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.Cliente;
import com.distribuidora18.springboot.backend.apirest.models.entity.Vendedor;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioVendedor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"GET","POST","PUT","DELETE"})

public class ControladorVendedor {

    private ServicioVendedor servicioVendedor;
    public ControladorVendedor( ServicioVendedor servicioVendedor){
        this.servicioVendedor=servicioVendedor;
    }

    //FUNCIONA
    @GetMapping("/listarVendedores")
    public ResponseEntity<?> listarVendedor (){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioVendedor.allVendedores(), HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarVendedor/{id}")
    public ResponseEntity<?> findByid(@PathVariable long id){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioVendedor.findById(id),HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al Encontrar en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

// FUNCIONA

    @PostMapping("/addvendedor")
    public ResponseEntity<?> addVendedor(@RequestBody Vendedor vendedor ){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioVendedor.addVendedor(vendedor),HttpStatus.CREATED);

        }catch (DataAccessException e){
            response.put("mensaje","Error al agregar en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizarVendedor/{id}")
    public ResponseEntity<String> actualizarVendedor(@PathVariable Long id, @RequestBody Vendedor nuevoVendedor) {
        String resultado = servicioVendedor.updateVendedor(id, nuevoVendedor);

        if (resultado.equals("Vendedor actualizado correctamente")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarVendedor/{id}")
    public ResponseEntity<String> eliminarVendedor(@PathVariable Long id) {
        String resultado = servicioVendedor.deleteVendedor(id);

        if (resultado.equals("Vendedor y pedidos relacionados eliminados en cascada")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // FUNCIONA
   /* @DeleteMapping("/deleteVendedor{id}")
    public ResponseEntity <?> delete (@PathVariable Long id){
        Map<String,Object> response= new HashMap<>();
        try {
            return new ResponseEntity<>(servicioVendedor.deleteVendedor(id),HttpStatus.OK);

        }catch (DataAccessException e){
            response.put("mensaje","Error al borrar en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    */
}
