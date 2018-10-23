package com.upn.webtransactional.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producto")
public class Producto {

    public interface Basic {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Basic.class)
    private int id;

    @Column(name = "codigo")
    @NotNull
    @JsonView(Basic.class)
    private String codigo;

    @Column(name = "nombre")
    @NotNull
    @JsonView(Basic.class)
    private String nombre;

    @Column(name = "descripcion")
    @JsonView(Basic.class)
    private String descripcion;

    @Column(name = "precio")
    @JsonView(Basic.class)
    private double precio;

    @Column(name = "cantidad")
    @JsonView(Basic.class)
    private int cantidad;

    @OneToMany(mappedBy = "producto",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PedidoProducto> pedidoProductoList = new ArrayList<>();

    @Column(name = "estado", length = 1, columnDefinition = "char(1) default '1'")
    @JsonView(Basic.class)
    private String estado;

    public Producto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<PedidoProducto> getPedidoProductoList() {
        return pedidoProductoList;
    }

    public void setPedidoProductoList(List<PedidoProducto> pedidoProductoList) {
        this.pedidoProductoList = pedidoProductoList;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
