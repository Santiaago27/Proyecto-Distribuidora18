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

    public ServicioDetallePedido(RepoPedido repoPedido, RepoProducto repoProducto, RepoDetallePedido repoDetallePedido) {
        this.repopedido = repoPedido;
        this.repoproducto = repoProducto;
        this.repoDetallePedido = repoDetallePedido;

    }

    // Listar a todas los Pedidos.
    public List<DetallePedido> allDetallePedido() {
        return repoDetallePedido.findAll();
    }

    //buscar por ID
    public Optional<DetallePedido> findById(Long id) {

        return repoDetallePedido.findById(id);
    }

    //Agregar Pedido
    public void addDetallePedido(long id_producto, long id_pedido, DetallePedido detallepedido) {
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

    public DetallePedido actualizarDetallePedido(Long id, DetallePedido detallepedidoActualizado) {
        Optional<DetallePedido> detallepedidoOptional = repoDetallePedido.findById(id);

        if (detallepedidoOptional.isPresent()) {
            DetallePedido detallepedidoExistente = detallepedidoOptional.get();
            // Actualiza los campos del detalle existente con los valores del detalle actualizado

            detallepedidoExistente.setCantidad(detallepedidoActualizado.getCantidad());
            detallepedidoExistente.setDescuento(detallepedidoActualizado.getDescuento());
            detallepedidoExistente.setPrecio(detallepedidoActualizado.getPrecio());
            detallepedidoExistente.setSubTotal(detallepedidoActualizado.getSubTotal());
            detallepedidoExistente.setTotal(detallepedidoActualizado.getTotal());


            if (detallepedidoActualizado.getId_producto() != null) {
                detallepedidoExistente.setId_producto(detallepedidoActualizado.getId_producto());
            }
            if (detallepedidoActualizado.getId_pedido() != null) {
                detallepedidoExistente.setId_pedido(detallepedidoActualizado.getId_pedido());
            }

            return repoDetallePedido.save(detallepedidoExistente);
        }

        return null;
    }


    public String deleteDetallePedido(long id) {
        Optional<DetallePedido> detallepedidoOptional = repoDetallePedido.findById(id);

        if (detallepedidoOptional.isPresent()) {
            DetallePedido detallepedido = detallepedidoOptional.get();


            repoDetallePedido.delete(detallepedido);

            return "detallepedido relacionados eliminados en cascada";
        } else {
            return "detallepedido no encontrado";
        }
    }
}