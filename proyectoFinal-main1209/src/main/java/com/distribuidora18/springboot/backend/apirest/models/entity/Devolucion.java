package com.distribuidora18.springboot.backend.apirest.models.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "devoluciones")
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_devolucion;
    @Column(nullable = false, length = 50)
    private String descripcion;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_detalle_pedido", referencedColumnName = "id_detalle_pedido")
    private DetallePedido id_detalle_pedido;

    public Devolucion() {
    }

    public Devolucion(long id_devolucion, String descripcion, DetallePedido id_detalle_pedido) {
        this.id_devolucion = id_devolucion;
        this.descripcion = descripcion;
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public long getId_devolucion() {
        return id_devolucion;
    }

    public void setId_devolucion(long id_devolucion) {
        this.id_devolucion = id_devolucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DetallePedido getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(DetallePedido id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }
}
