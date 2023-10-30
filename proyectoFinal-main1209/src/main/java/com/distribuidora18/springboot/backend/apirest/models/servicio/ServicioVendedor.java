package com.distribuidora18.springboot.backend.apirest.models.servicio;
import com.distribuidora18.springboot.backend.apirest.models.entity.Cliente;
import com.distribuidora18.springboot.backend.apirest.models.entity.Vendedor;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoVendedor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioVendedor {
    private RepoVendedor repoVendedor;

    public ServicioVendedor(RepoVendedor repoVendedor) {
        this.repoVendedor = repoVendedor;
    }

    public List<Vendedor> allVendedores() {
        return repoVendedor.findAll();
    }

    public Optional<Vendedor> findById(Long id){


        return repoVendedor.findById(id);
    }



    public String addVendedor(Vendedor vendedor) {

        if (repoVendedor.findById(vendedor.getId_vendedor()).isPresent()) {
            return "El Vendedor ya se encuentra";
        } else {
            repoVendedor.save(vendedor);
            return "Vendedor Guardado" + vendedor.getId_vendedor();
        }

    }

    public String updateVendedor(long id, Vendedor vendedorActualizado) {
        Optional<Vendedor> vendedorOptional = repoVendedor.findById(id);

        if (vendedorOptional.isPresent()) {
            Vendedor vendedorExistente = vendedorOptional.get();

            // Actualiza los campos de la categoría existente con los valores de la categoría actualizada
            vendedorExistente.setNombre(vendedorActualizado.getNombre());
            vendedorExistente.setApellido(vendedorActualizado.getApellido());
            vendedorExistente.setTelefono(vendedorActualizado.getTelefono());
            vendedorExistente.setEmail(vendedorActualizado.getEmail());

            // Guarda la categoría actualizada en la base de datos
            repoVendedor.save(vendedorExistente);

            return "Vendedor actualizado correctamente";
        } else {
            return "Vendedor no encontrado";
        }
    }

    public String deleteVendedor(long id) {
        Optional<Vendedor> vendedorOptional = repoVendedor.findById(id);

        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();

            // Elimina la categoría y los productos relacionados en cascada
            repoVendedor.delete(vendedor);

            return "Vendedor y pedidos relacionados eliminados en cascada";
        } else {
            return "Vendedor no encontrado";
        }
    }

    /*// CAMBIAR LUEGO DE PROBAR A STRING
    public String deleteVendedor(long id) {

        repoVendedor.deleteById(id);
        return "Vendedor = " + id + "Eliminado";
    }

     */





}

