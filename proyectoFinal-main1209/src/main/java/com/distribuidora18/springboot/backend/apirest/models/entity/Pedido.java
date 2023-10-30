package com.distribuidora18.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pedido;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "forma_pago", nullable = false, length = 50)
    private String formaPago;
    @Column(nullable = false, length = 50)
    private String pagado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vendedor", referencedColumnName = "id_vendedor", nullable = false)
    @JsonIgnore
    private Vendedor id_vendedor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sucursal")
    @JsonIgnore
    private Sucursal id_sucursal;

    @OneToMany(mappedBy = "id_pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetallePedido> detalles_pedidos = new HashSet<>();

    public Pedido() {

    }

    public Pedido(long id_pedido, Date fecha, String formaPago, String pagado, Vendedor id_vendedor, Sucursal id_sucursal, Set<DetallePedido> detalles_pedidos) {
        this.id_pedido = id_pedido;
        this.fecha = fecha;
        this.formaPago = formaPago;
        this.pagado = pagado;
        this.id_vendedor = id_vendedor;
        this.id_sucursal = id_sucursal;
        this.detalles_pedidos = detalles_pedidos;
    }

    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public Vendedor getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(Vendedor id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public Sucursal getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Sucursal id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Set<DetallePedido> getDetalles_pedidos() {
        return detalles_pedidos;
    }

    public void setDetalles_pedidos(Set<DetallePedido> detalles_pedidos) {
        this.detalles_pedidos = detalles_pedidos;
    }

}
