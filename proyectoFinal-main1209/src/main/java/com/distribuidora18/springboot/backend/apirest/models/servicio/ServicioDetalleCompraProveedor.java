package com.distribuidora18.springboot.backend.apirest.models.servicio;

import com.distribuidora18.springboot.backend.apirest.models.entity.*;
import com.distribuidora18.springboot.backend.apirest.models.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioDetalleCompraProveedor {
    private RepoDetalleCompraProveedor repoDetalleCompraProveedor;
    private RepoProveedor repoproveedor;
    private RepoProducto repoproducto;

    public ServicioDetalleCompraProveedor(RepoDetalleCompraProveedor repoDetalleCompraProveedor, RepoProveedor repoproveedor, RepoProducto repoproducto) {
        this.repoDetalleCompraProveedor = repoDetalleCompraProveedor;
        this.repoproveedor = repoproveedor;
        this.repoproducto = repoproducto;
    }

    public List<DetalleCompraProveedor> allDetCompra(){return repoDetalleCompraProveedor.findAll();}

    public void addDetCompra(long id_proveedor,long id_producto, DetalleCompraProveedor detallecompraproveedor){
        Optional<Proveedor> proveedorOptional = repoproveedor.findById(id_proveedor);
        Optional<Producto> productoOptional = repoproducto.findById(id_producto);
        if (productoOptional.isPresent() && (proveedorOptional.isPresent())) {
            Proveedor proveedor = proveedorOptional.get();
            Producto producto = productoOptional.get();
            detallecompraproveedor.setId_proveedor(proveedor);
            detallecompraproveedor.setId_producto(producto);
            repoDetalleCompraProveedor.save(detallecompraproveedor);
        }

    }

}
