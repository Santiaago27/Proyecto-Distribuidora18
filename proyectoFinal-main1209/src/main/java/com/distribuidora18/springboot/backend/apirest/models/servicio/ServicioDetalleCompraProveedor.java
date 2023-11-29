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

    public Optional<DetalleCompraProveedor> findById(Long id) {
        return repoDetalleCompraProveedor.findById(id);
    }

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

    public DetalleCompraProveedor actualizarDetalleCompraProveedor(Long id, DetalleCompraProveedor detallecompraproveedorActualizado) {
        Optional<DetalleCompraProveedor> detallecompraproveedorOptional = repoDetalleCompraProveedor.findById(id);

        if (detallecompraproveedorOptional.isPresent()) {
            DetalleCompraProveedor detallecompraproveedorExistente = detallecompraproveedorOptional.get();
            // Actualiza los campos del detalle existente con los valores del detalle actualizado
            detallecompraproveedorExistente.setFecha(detallecompraproveedorActualizado.getFecha());
            detallecompraproveedorExistente.setCantidad(detallecompraproveedorActualizado.getCantidad());
            detallecompraproveedorExistente.setPrecio(detallecompraproveedorActualizado.getPrecio());

            // Actualiza las relaciones con producto y proveedor si es necesario
            if (detallecompraproveedorActualizado.getId_producto() != null) {
                detallecompraproveedorExistente.setId_producto(detallecompraproveedorActualizado.getId_producto());
            }
            if (detallecompraproveedorActualizado.getId_proveedor() != null) {
                detallecompraproveedorExistente.setId_proveedor(detallecompraproveedorActualizado.getId_proveedor());
            }

            return repoDetalleCompraProveedor.save(detallecompraproveedorExistente);
        }

        return null;
    }

    public String deleteDetalleCompraProveedor(Long id) {
        Optional<DetalleCompraProveedor> detallecompraproveedorOptional = repoDetalleCompraProveedor.findById(id);

        if (detallecompraproveedorOptional.isPresent()) {
            repoDetalleCompraProveedor.deleteById(id);
            return "elementos relacionados eliminados en cascada";
        } else {
            return "DetalleCompraProveedor no encontrado";
        }
    }

}
