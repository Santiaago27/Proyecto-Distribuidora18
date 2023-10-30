package com.distribuidora18.springboot.backend.apirest.models.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proveedores")
    public class Proveedor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id_proveedor;
        @Column(nullable = false, length = 50)


        private String nombre;
        @Column(nullable = false, length = 50)
        private String telefono;
        @Column(nullable = false, length = 50)
        private String Direccion;
        @Column(nullable = false, length = 50)
        private String medioPago;

    @OneToMany(mappedBy = "id_proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetalleCompraProveedor> detallesCompraProveedor = new HashSet<>();


    public Proveedor() {
    }

    public Proveedor(long id_proveedor, String nombre, String telefono, String direccion, String medioPago, Set<DetalleCompraProveedor> detallesCompraProveedor) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.Direccion = direccion;
        this.medioPago = medioPago;
        this.detallesCompraProveedor = detallesCompraProveedor;
    }

    public long getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(long id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public Set<DetalleCompraProveedor> getDetallesCompraProveedor() {
        return detallesCompraProveedor;
    }

    public void setDetallesCompraProveedor(Set<DetalleCompraProveedor> detallesCompraProveedor) {
        this.detallesCompraProveedor = detallesCompraProveedor;
    }

}
