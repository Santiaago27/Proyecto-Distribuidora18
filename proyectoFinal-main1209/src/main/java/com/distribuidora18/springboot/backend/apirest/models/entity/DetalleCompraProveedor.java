package com.distribuidora18.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "detalles_compras_proveedores")
public class DetalleCompraProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_detalle_compra_proveedor;

    private long cantidad;
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private long precio;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
    @JsonIgnore
    private Producto id_producto;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor", nullable = false)
    @JsonIgnore
    private Proveedor id_proveedor;

    public DetalleCompraProveedor() {
    }

    public DetalleCompraProveedor(long id_detalle_compra_proveedor, long cantidad, Date fecha, long precio, Producto id_producto, Proveedor id_proveedor) {
        this.id_detalle_compra_proveedor = id_detalle_compra_proveedor;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.precio = precio;
        this.id_producto = id_producto;
        this.id_proveedor = id_proveedor;
    }

    public long getId_detalle_compra_proveedor() {
        return id_detalle_compra_proveedor;
    }

    public void setId_detalle_compra_proveedor(long id_detalle_compra_proveedor) {
        this.id_detalle_compra_proveedor = id_detalle_compra_proveedor;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public Producto getId_producto() {
        return id_producto;
    }

    public void setId_producto(Producto id_producto) {
        this.id_producto = id_producto;
    }

    public Proveedor getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Proveedor id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
}

