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

    //Agregar Sucursal
    //Borrar el String y dejarlo void .

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

    public Pedido updatePedido(long id, Pedido pedidoActualizado) {
        Optional<Pedido> pedidoOptional = repoPedido.findById(id);

        if (pedidoOptional.isPresent()) {
            Pedido pedidoExistente = pedidoOptional.get();
            pedidoExistente.setFecha(pedidoActualizado.getFecha());
            pedidoExistente.setFormaPago(pedidoActualizado.getFormaPago());
            pedidoExistente.setPagado(pedidoActualizado.getPagado());


            // Actualiza la relación con la categoría si es necesario
            if (pedidoActualizado.getId_sucursal() != null) {
                Sucursal sucursal = reposucursal.findById(pedidoActualizado.getId_sucursal().getId_sucursal()).orElse(null);
                pedidoExistente.setId_sucursal(sucursal);
            }

            return repoPedido.save(pedidoExistente);
        }

        return null; // Puedes manejar este caso de acuerdo a tus necesidades
    }
    //Borrar Sucursal

    public String deletePedido (Long id){
        repoPedido.deleteById(id);
        return "Sucursal de id "+id+"Eliminado";
    }
}
