package com.distribuidora18.springboot.backend.apirest.models.servicio;

import com.distribuidora18.springboot.backend.apirest.models.entity.CategoriaProducto;
import com.distribuidora18.springboot.backend.apirest.models.entity.Producto;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoCategoriaProducto;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoProducto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto {

    private RepoCategoriaProducto repocategoriaproducto;
    private RepoProducto repoProducto;

    public ServicioProducto (RepoProducto repoProducto, RepoCategoriaProducto repocategoriaproducto){
        this.repoProducto=repoProducto;
        this.repocategoriaproducto =repocategoriaproducto;

    }

    // Listar a todas los Productos.
    public List<Producto> allProducto(){return repoProducto.findAll();}

    //Agregar Producto
    //Borrar el String y dejarlo void .

    public void addProducto(long id_categoria, Producto producto){
        Optional<CategoriaProducto> categoriaOptional = repocategoriaproducto.findById(id_categoria);

        if (categoriaOptional.isPresent()) {
            CategoriaProducto categoriaproducto = categoriaOptional.get();
            producto.setId_categoria(categoriaproducto);
            repoProducto.save(producto);
        }


    }

    public Producto updateProducto(long id, Producto productoActualizado) {
        Optional<Producto> productoOptional = repoProducto.findById(id);

        if (productoOptional.isPresent()) {
            Producto productoExistente = productoOptional.get();
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setUnidadMedida(productoActualizado.getUnidadMedida());
            productoExistente.setCantidad(productoActualizado.getCantidad());
            productoExistente.setDescripcion(productoActualizado.getDescripcion());

            // Actualiza la relación con la categoría si es necesario
            if (productoActualizado.getId_categoria() != null) {
                CategoriaProducto categoriaProducto = repocategoriaproducto.findById(productoActualizado.getId_categoria().getId_categoria()).orElse(null);
                productoExistente.setId_categoria(categoriaProducto);
            }

            return repoProducto.save(productoExistente);
        }

        return null; // Puedes manejar este caso de acuerdo a tus necesidades
    }

    //Borrar Sucursal

    public String deleteProducto (Long id){
        repoProducto.deleteById(id);
        return "Sucursal de id "+id+"Eliminado";
    }
}
