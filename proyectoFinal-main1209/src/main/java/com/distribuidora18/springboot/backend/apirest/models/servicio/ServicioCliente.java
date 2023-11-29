package com.distribuidora18.springboot.backend.apirest.models.servicio;

import com.distribuidora18.springboot.backend.apirest.models.entity.Cliente;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoCliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCliente {

    private RepoCliente repoCliente;

    public ServicioCliente(RepoCliente repoCliente) {
        this.repoCliente = repoCliente;
    }

    public List<Cliente> allClientes() {
        return repoCliente.findAll();
    }

    public Optional<Cliente> findById(Long id) {

        return repoCliente.findById(id);
    }


    public String addCliente(Cliente cliente) {

        if (repoCliente.findById(cliente.getId_cliente()).isPresent()) {
            return "El Cliente ya se encuentra";
        } else {
            repoCliente.save(cliente);
            return "Cliente Guardado" + cliente.getId_cliente();
        }

    }

    public String updateCliente(long id, Cliente clienteActualizado) {
        Optional<Cliente> clienteOptional = repoCliente.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente clienteExistente = clienteOptional.get();

            // Actualiza los campos de el cliente existente con los valores de la cliente actualizada
            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setIdentificacion(clienteActualizado.getIdentificacion());
            clienteExistente.setApellido(clienteActualizado.getApellido());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
            clienteExistente.setEmail(clienteActualizado.getEmail());

            // Guarda el cliente actualizada en la base de datos
            repoCliente.save(clienteExistente);

            return "Cliente actualizada correctamente";
        } else {
            return "Cliente no encontrado";
        }
    }

    public String deleteCliente(long id) {
        Optional<Cliente> clienteOptional = repoCliente.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();

            // Elimina la clientes y sucurslaes relacionados en cascada
            repoCliente.delete(cliente);

            return "elementos relacionados eliminados en cascada";
        } else {
            return "cliente no encontrado";
        }
    }

}