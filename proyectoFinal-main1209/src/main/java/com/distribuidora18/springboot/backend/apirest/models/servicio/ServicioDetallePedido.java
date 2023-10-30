package com.distribuidora18.springboot.backend.apirest.models.servicio;

import com.distribuidora18.springboot.backend.apirest.models.entity.*;
import com.distribuidora18.springboot.backend.apirest.models.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioDetallePedido {
    private RepoDetallePedido repoDetallePedido;
    private RepoPedido repopedido;
    private RepoProducto repoproducto;
    public ServicioDetallePedido (RepoPedido repoPedido, RepoProducto repoProducto, RepoDetallePedido repoDetallePedido){
        this.repopedido=repoPedido;
        this.repoproducto = repoProducto;
        this.repoDetallePedido = repoDetallePedido;

    }

    // Listar a todas los Pedidos.
    public List<DetallePedido> allDetallePedido(){return repoDetallePedido.findAll();}

    //Agregar Sucursal
    //Borrar el String y dejarlo void .

    public void addDetallePedido(long id_producto,long id_pedido, DetallePedido detallepedido){
        Optional<Pedido> pedidoOptional = repopedido.findById(id_pedido);
        Optional<Producto> productoOptional = repoproducto.findById(id_producto);
        if ((pedidoOptional.isPresent()) && (productoOptional.isPresent())) {
            Producto producto = productoOptional.get();
            Pedido pedido = pedidoOptional.get();
            detallepedido.setId_producto(producto);
            detallepedido.setId_pedido(pedido);
            repoDetallePedido.save(detallepedido);

        }

    }
    //Borrar Sucursal



    public String deleteDetallePedido (Long id){

        repoDetallePedido.deleteById(id);

        return "Sucursal de id "+id+"Eliminado";
    }
}
