package com.distribuidora18.springboot.backend.apirest.models.servicio;

import com.distribuidora18.springboot.backend.apirest.models.entity.*;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoDetallePedido;
import com.distribuidora18.springboot.backend.apirest.models.repository.RepoDevolucion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioDevolucion {
    private RepoDevolucion repoDevolucion;
    private RepoDetallePedido repoDetallePedido;

    public ServicioDevolucion (RepoDevolucion repoDevolucion, RepoDetallePedido repoDetallePedido){
        this.repoDevolucion=repoDevolucion;
        this.repoDetallePedido = repoDetallePedido;
    }

    public List<Devolucion> allDevoluciones(){
        return repoDevolucion.findAll();}

    public void addDevolucion(long id_detalle_pedido, Devolucion devolucion){
        Optional<DetallePedido> detallePedidoOptional = repoDetallePedido.findById(id_detalle_pedido);

        if (detallePedidoOptional.isPresent()) {
            DetallePedido detallePedido = detallePedidoOptional.get();
            devolucion.setId_detalle_pedido(detallePedido);
            repoDevolucion.save(devolucion);
        }
    }

    public Devolucion actualizarDevolucion(long id, Devolucion devolucionActualizada) {
        Optional<Devolucion> devolucionOptional = repoDevolucion.findById(id);

        if (devolucionOptional.isPresent()) {
            Devolucion devolucionExistente = devolucionOptional.get();
            devolucionExistente.setDescripcion(devolucionActualizada.getDescripcion());

            // Actualiza la relación con la categoría si es necesario
            if (devolucionActualizada.getId_detalle_pedido() != null) {
                DetallePedido detallePedido = repoDetallePedido.findById(devolucionActualizada.getId_detalle_pedido().getId_detalle_pedido()).orElse(null);
                devolucionExistente.setId_detalle_pedido(detallePedido);
            }

            return repoDevolucion.save(devolucionExistente);
        }

        return null; // Puedes manejar este caso de acuerdo a tus necesidades
    }

}
