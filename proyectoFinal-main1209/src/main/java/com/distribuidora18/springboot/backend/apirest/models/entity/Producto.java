package com.distribuidora18.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_producto;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column( name = "unidad_medida",nullable = false, length = 50)
    private String unidadMedida;
    private long cantidad;
    @Column(nullable = false, length = 50)
    private String descripcion;




    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", nullable = false)
    @JsonIgnore
    private CategoriaProducto id_categoria;

    @OneToMany(mappedBy = "id_producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetallePedido> detalles_pedidos = new HashSet<>();

    @OneToMany(mappedBy = "id_producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetalleCompraProveedor> detalles_compras_proveedores = new HashSet<>();

    public Producto() {
    }

    public Producto(long id_producto, String nombre, String unidadMedida, long cantidad, String descripcion, CategoriaProducto id_categoria, Set<DetallePedido> detalles_pedidos, Set<DetalleCompraProveedor> detalles_compras_proveedores) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.id_categoria = id_categoria;
        this.detalles_pedidos = detalles_pedidos;
        this.detalles_compras_proveedores = detalles_compras_proveedores;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaProducto getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(CategoriaProducto id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Set<DetallePedido> getDetalles_pedidos() {
        return detalles_pedidos;
    }

    public void setDetalles_pedidos(Set<DetallePedido> detalles_pedidos) {
        this.detalles_pedidos = detalles_pedidos;
    }

    public Set<DetalleCompraProveedor> getDetalles_compras_proveedores() {
        return detalles_compras_proveedores;
    }

    public void setDetalles_compras_proveedores(Set<DetalleCompraProveedor> detalles_compras_proveedores) {
        this.detalles_compras_proveedores = detalles_compras_proveedores;
    }
}
