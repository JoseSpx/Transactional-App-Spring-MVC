package com.upn.webtransactional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente {

    public interface Basic {}
    public interface DetailCorreo {}
    public interface DetailPedido {}

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Basic.class)
    private int id;

    @Column(name = "nombre")
    @NotNull
    @JsonView(Basic.class)
    private String nombre;

    @Column(name = "apellido")
    @JsonView(Basic.class)
    private String apellido;

    @Column(name = "estado", length = 2, columnDefinition = "char(1) default '1'")
    @JsonView(Basic.class)
    private String estado;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @JsonView(DetailCorreo.class)
    private Set<ClienteCorreo> clienteCorreoList = new HashSet<>();

    @OneToMany(mappedBy = "clientePedido", fetch = FetchType.EAGER)
    @JsonView(DetailPedido.class)
    private Set<Pedido> pedidoList = new HashSet<>();

    public Cliente() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<ClienteCorreo> getClienteCorreoList() {
        return clienteCorreoList;
    }

    public void setClienteCorreoList(Set<ClienteCorreo> clienteCorreoList) {
        this.clienteCorreoList = clienteCorreoList;
    }

    public Set<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(Set<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }
}
