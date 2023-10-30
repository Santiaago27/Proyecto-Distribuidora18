package com.distribuidora18.springboot.backend.apirest.models.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="vendedores")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_vendedor;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String Apellido;
    @Column(nullable = false, length = 50)
    private String telefono;
    @Column(nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "id_vendedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pedido> pedidos = new HashSet<>();

    public Vendedor() {
    }

    public Vendedor(long id_vendedor, String nombre, String apellido, String telefono, String email, Set<Pedido> pedidos) {
        this.id_vendedor = id_vendedor;
        this.nombre = nombre;
        Apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.pedidos = pedidos;
    }

    public long getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(long id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
