package com.distribuidora18.springboot.backend.apirest.models.servicio;

import com.distribuidora18.springboot.backend.apirest.models.entity.Cliente;
import com.distribuidora18.springboot.backend.apirest.models.entity.Proveedor;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoCliente;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoProveedor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProveedor {
    private RepoProveedor repoProveedor;

    public ServicioProveedor(RepoProveedor repoProveedor) {
        this.repoProveedor = repoProveedor;
    }

    public List<Proveedor> allProveedores() {
        return repoProveedor.findAll();
    }

    public Optional<Proveedor> findById(Long id) {


        return repoProveedor.findById(id);
    }


    public String addProveedor(Proveedor proveedor) {

        if (repoProveedor.findById(proveedor.getId_proveedor()).isPresent()) {
            return "El Proveedor ya se encuentra";
        } else {
            repoProveedor.save(proveedor);
            return "Proveedor Guardado" + proveedor.getId_proveedor();
        }

    }

    public String updateProveedor(long id, Proveedor proveedorActualizado) {
        Optional<Proveedor> proveedorOptional = repoProveedor.findById(id);

        if (proveedorOptional.isPresent()) {
            Proveedor proveedorExistente = proveedorOptional.get();

            // Actualiza los campos de la categoría existente con los valores de la categoría actualizada
            proveedorExistente.setNombre(proveedorActualizado.getNombre());
            proveedorExistente.setMedioPago(proveedorActualizado.getMedioPago());
            proveedorExistente.setTelefono(proveedorActualizado.getTelefono());
            proveedorExistente.setDireccion(proveedorActualizado.getDireccion());

            // Guarda la categoría actualizada en la base de datos
            repoProveedor.save(proveedorExistente);

            return "Proveedor actualizado correctamente";
        } else {
            return "Proveedor no encontrado";
        }
    }

    public String deleteProveedor(long id) {
        Optional<Proveedor> proveedorOptional = repoProveedor.findById(id);

        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();

            // Elimina la categoría y los productos relacionados en cascada
            repoProveedor.delete(proveedor);

            return "Proveedor y detalles relacionados eliminados en cascada";
        } else {
            return "proveedor no encontrado";
        }
    }
}
