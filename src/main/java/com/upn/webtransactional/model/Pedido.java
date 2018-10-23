package com.upn.webtransactional.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    public interface Basic {}
    public interface Detail {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Basic.class)
    private int id;

    @Column(name = "monto")
    @JsonView(Basic.class)
    private Double monto;

    @Column(name = "fechapedido")
    @JsonView(Basic.class)
    private LocalDateTime fechaPedido;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente clientePedido;

    @OneToMany(mappedBy = "pedido",fetch = FetchType.EAGER)
    @JsonView(Detail.class)
    private List<PedidoProducto> pedidoProductoList = new ArrayList<>();

    @Column(name = "estado", length = 1, columnDefinition = "char(1) default '1'")
    @JsonView(Basic.class)
    private String estado;

    public Pedido() {}

    @PrePersist
    public void prePersis(){
        this.fechaPedido = LocalDateTime.now();
        this.estado = "1";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public void setClientePedido(Cliente clientePedido) {
        this.clientePedido = clientePedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<PedidoProducto> getPedidoProductoList() {
        return pedidoProductoList;
    }

    public void setPedidoProductoList(List<PedidoProducto> pedidoProductoList) {
        this.pedidoProductoList = pedidoProductoList;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
