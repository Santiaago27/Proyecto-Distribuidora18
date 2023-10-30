package com.distribuidora18.springboot.backend.apirest.models.servicio;

import com.distribuidora18.springboot.backend.apirest.models.entity.CategoriaProducto;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoCategoriaProducto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCategoriaProducto {

        private RepoCategoriaProducto repocategoria;

        public ServicioCategoriaProducto(RepoCategoriaProducto repocategoria) {
                this.repocategoria = repocategoria;
        }

        public List<CategoriaProducto> allCategoria() {
                return repocategoria.findAll();
        }

        public Optional<CategoriaProducto> findById(long id) {


                return repocategoria.findById(id);
        }

        public String addCategoria(CategoriaProducto categoriaProducto) {

                if (repocategoria.findById(categoriaProducto.getId_categoria()).isPresent()) {
                        return "la categoria ya se encuentra";
                } else {
                        repocategoria.save(categoriaProducto);
                        return "Categoria Guardado" + categoriaProducto.getId_categoria();
                }

        }

        public String updateCategoria(long id, CategoriaProducto categoriaActualizada) {
                Optional<CategoriaProducto> categoriaOptional = repocategoria.findById(id);

                if (categoriaOptional.isPresent()) {
                        CategoriaProducto categoriaExistente = categoriaOptional.get();

                        // Actualiza los campos de la categoría existente con los valores de la categoría actualizada
                        categoriaExistente.setNombre(categoriaActualizada.getNombre());
                        categoriaExistente.setDescripcion(categoriaActualizada.getDescripcion());

                        // Guarda la categoría actualizada en la base de datos
                        repocategoria.save(categoriaExistente);

                        return "Categoria actualizada correctamente";
                } else {
                        return "Categoria no encontrada";
                }
        }

        // CAMBIAR LUEGO DE PROBAR A STRING
        public String deleteCategoria(long id) {
                Optional<CategoriaProducto> categoriaOptional = repocategoria.findById(id);

                if (categoriaOptional.isPresent()) {
                        CategoriaProducto categoria = categoriaOptional.get();

                        // Elimina la categoría y los productos relacionados en cascada
                        repocategoria.delete(categoria);

                        return "Categoria y productos relacionados eliminados en cascada";
                } else {
                        return "Categoria no encontrada";
                }
        }


}
