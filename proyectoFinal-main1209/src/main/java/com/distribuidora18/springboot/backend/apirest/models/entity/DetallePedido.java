package com.distribuidora18.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "detalles_pedidos")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_detalle_pedido;

    private long precio;

    private long subTotal;

    private long descuento;

    private long cantidad;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido",referencedColumnName = "id_pedido",nullable = false)
    @JsonIgnore
    private Pedido id_pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
    @JsonIgnore
    private Producto id_producto;

    @OneToMany(mappedBy = "id_detalle_pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Devolucion> devoluciones = new HashSet<>();

    public DetallePedido() {
    }

    public DetallePedido(long id_detalle_pedido, long precio, long subTotal, long descuento, long cantidad, Pedido id_pedido, Producto id_producto, Set<Devolucion> devoluciones) {
        this.id_detalle_pedido = id_detalle_pedido;
        this.precio = precio;
        this.subTotal = subTotal;
        this.descuento = descuento;
        this.cantidad = cantidad;
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.devoluciones = devoluciones;
    }

    public long getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(long id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public long getDescuento() {
        return descuento;
    }

    public void setDescuento(long descuento) {
        this.descuento = descuento;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Pedido id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Producto getId_producto() {
        return id_producto;
    }

    public void setId_producto(Producto id_producto) {
        this.id_producto = id_producto;
    }

    public Set<Devolucion> getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(Set<Devolucion> devoluciones) {
        this.devoluciones = devoluciones;
    }
}
