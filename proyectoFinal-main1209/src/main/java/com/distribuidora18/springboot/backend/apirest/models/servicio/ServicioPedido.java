package com.distribuidora18.springboot.backend.apirest.models.servicio;

import com.distribuidora18.springboot.backend.apirest.models.entity.*;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoPedido;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoSucursal;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoVendedor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioPedido {

    private RepoPedido repoPedido;
    private RepoVendedor repovendedor;
    private RepoSucursal reposucursal;
    public ServicioPedido (RepoPedido repoPedido, RepoSucursal repoSucursal, RepoVendedor repoVendedor){
        this.repoPedido=repoPedido;
        this.reposucursal = repoSucursal;
        this.repovendedor = repoVendedor;

    }

    // Listar a todas los Pedidos.
    public List<Pedido> allPedido(){return repoPedido.findAll();}

    public Optional<Pedido> findById(Long id) {
        return repoPedido.findById(id);
    }


    //Agregar Pedido

    public void addPedido(long id_vendedor,long id_sucursal, Pedido pedido){
        Optional<Vendedor> vendedorOptional = repovendedor.findById(id_vendedor);
        Optional<Sucursal> sucursalOptional = reposucursal.findById(id_sucursal);
        if (sucursalOptional.isPresent() && (vendedorOptional.isPresent())) {
            Sucursal sucursal = sucursalOptional.get();
            Vendedor vendedor = vendedorOptional.get();
            pedido.setId_vendedor(vendedor);
            pedido.setId_sucursal(sucursal);
            repoPedido.save(pedido);
        }
    }

    public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {
        Optional<Pedido> pedidoOptional = repoPedido.findById(id);

        if (pedidoOptional.isPresent()) {
            Pedido pedidoExistente = pedidoOptional.get();
            // Actualiza los campos del pedido existente con los valores del pedido actualizado
            pedidoExistente.setFecha(pedidoActualizado.getFecha());
            pedidoExistente.setFormaPago(pedidoActualizado.getFormaPago());
            pedidoExistente.setPagado(pedidoActualizado.getPagado());

            // Actualiza las relaciones con vendedor y sucursal si es necesario
            if (pedidoActualizado.getId_vendedor() != null) {
                pedidoExistente.setId_vendedor(pedidoActualizado.getId_vendedor());
            }
            if (pedidoActualizado.getId_sucursal() != null) {
                pedidoExistente.setId_sucursal(pedidoActualizado.getId_sucursal());
            }

            return repoPedido.save(pedidoExistente);
        }

        return null; // Puedes manejar este caso de acuerdo a tus necesidades
    }


    //Borrar Pedido

    public String deletePedido(Long id) {
        Optional<Pedido> pedidoOptional = repoPedido.findById(id);

        if (pedidoOptional.isPresent()) {
            repoPedido.deleteById(id);
            return "Pedido y registros relacionados eliminados en cascada";
        } else {
            return "Pedido no encontrado";
        }
    }
}
