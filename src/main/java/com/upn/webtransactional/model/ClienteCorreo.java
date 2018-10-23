package com.upn.webtransactional.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "clientecorreo")
public class ClienteCorreo {

    public interface Detail {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Detail.class)
    private int id;

    @Column(name = "correo")
    @JsonView(Detail.class)
    private String correo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tipocorreo_id")
    @JsonView(Detail.class)
    private TipoCorreo tipoCorreo;

    @Column(name = "estado", length = 1, columnDefinition = "char(1) default '1'")
    private String estado;

    public ClienteCorreo(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoCorreo getTipoCorreo() {
        return tipoCorreo;
    }

    public void setTipoCorreo(TipoCorreo tipoCorreo) {
        this.tipoCorreo = tipoCorreo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
