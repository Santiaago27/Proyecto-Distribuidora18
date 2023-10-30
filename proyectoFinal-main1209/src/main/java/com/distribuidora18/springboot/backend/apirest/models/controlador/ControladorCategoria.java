package com.distribuidora18.springboot.backend.apirest.models.controlador;

import com.distribuidora18.springboot.backend.apirest.models.entity.CategoriaProducto;
import com.distribuidora18.springboot.backend.apirest.models.servicio.ServicioCategoriaProducto;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ControladorCategoria {

    private ServicioCategoriaProducto servicioCategoriaProducto;
    public ControladorCategoria( ServicioCategoriaProducto servicioCategoriaProducto){
        this.servicioCategoriaProducto=servicioCategoriaProducto;
    }

    @GetMapping("/listarCategorias")
    public ResponseEntity<?> listarCategorias (){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioCategoriaProducto.allCategoria(), HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarCategoria/{id}")
    public ResponseEntity<?> findByid(@PathVariable Long id){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioCategoriaProducto.findById(id),HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("mensaje","Error al Encontrar en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/addCategoria")
    public ResponseEntity<?> addCategoria(@RequestBody CategoriaProducto categoriaProducto ){
        Map<String,Object> response= new HashMap<>();
        try{
            return new ResponseEntity<>(servicioCategoriaProducto.addCategoria(categoriaProducto),HttpStatus.CREATED);

        }catch (DataAccessException e){
            response.put("mensaje","Error al agregar en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizarCategoria/{id}")
    public ResponseEntity<String> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaProducto nuevaCategoria) {
        String resultado = servicioCategoriaProducto.updateCategoria(id, nuevaCategoria);

        if (resultado.equals("Categoria actualizada correctamente")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarCategoria/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        String resultado = servicioCategoriaProducto.deleteCategoria(id);

        if (resultado.equals("Categoria y productos relacionados eliminados en cascada")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
