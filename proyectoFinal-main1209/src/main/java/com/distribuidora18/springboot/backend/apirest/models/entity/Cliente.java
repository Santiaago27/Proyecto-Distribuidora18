package com.distribuidora18.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table(name="clientes")
public class Cliente {
@Id
private long id_cliente;
@Column(nullable=false,length=50)
private String identificacion;
@Column(nullable = false, length = 50)
private String nombre;
@Column(nullable = false, length = 50)
private String apellido;
@Column(nullable = false, length = 50,unique = true)
private String email;
    @Column(nullable = false,length = 20)
    private String telefono;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Sucursal> sucursales;


    public Cliente() {
    }

    public Cliente(long id_cliente, String identificacion, String nombre, String apellido, String email, String telefono, Set<Sucursal> sucursales) {
        this.id_cliente = id_cliente;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.sucursales = sucursales;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(Set<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
