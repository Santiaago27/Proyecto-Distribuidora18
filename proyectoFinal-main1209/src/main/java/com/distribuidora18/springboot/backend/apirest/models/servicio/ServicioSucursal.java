package com.distribuidora18.springboot.backend.apirest.models.servicio;

import com.distribuidora18.springboot.backend.apirest.models.entity.Cliente;
import com.distribuidora18.springboot.backend.apirest.models.entity.Sucursal;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoCliente;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoSucursal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioSucursal {
    private RepoSucursal repoSucursal;
    private RepoCliente repocliente;
    public ServicioSucursal (RepoSucursal repoSucursal, RepoCliente repoCliente){
        this.repoSucursal=repoSucursal;
        this.repocliente = repoCliente;
    }

    // Listar a todas las sucursales.
    public List<Sucursal> allSucursales(){
        return repoSucursal.findAll();}

    //Agregar Sucursal
    //Borrar el String y dejarlo void

     public void addSucursal(long id_cliente, Sucursal sucursal){
         Optional<Cliente> clienteOptional = repocliente.findById(id_cliente);

         if (clienteOptional.isPresent()) {
             Cliente cliente = clienteOptional.get();
             sucursal.setCliente(cliente);
             repoSucursal.save(sucursal);
         }


     }

    public Sucursal actualizarSucursal(long id, Sucursal sucursalActualizada) {
        Optional<Sucursal> sucursalOptional = repoSucursal.findById(id);

        if (sucursalOptional.isPresent()) {
            Sucursal sucursalExistente = sucursalOptional.get();
            sucursalExistente.setNombre(sucursalActualizada.getNombre());
            sucursalExistente.setDireccion(sucursalActualizada.getDireccion());
            sucursalExistente.setDepartamento(sucursalActualizada.getDepartamento());
            sucursalExistente.setMunicipio(sucursalActualizada.getMunicipio());
            sucursalExistente.setEmail(sucursalActualizada.getEmail());

            // Actualiza la relación con el cliente si es necesario
            if (sucursalActualizada.getCliente() != null) {
                Cliente cliente = sucursalActualizada.getCliente();
                sucursalExistente.setCliente(cliente);
            }

            return repoSucursal.save(sucursalExistente);
        }

        return null; // Puedes manejar este caso de acuerdo a tus necesidades
    }
     //Borrar Sucursal

    public String deleteSucursal (Long id){
        repoSucursal.deleteById(id);
        return "Sucursal de id "+id+"Eliminado";
    }


}
