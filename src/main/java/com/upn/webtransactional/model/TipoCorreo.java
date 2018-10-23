package com.upn.webtransactional.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tipocorreo")
public class TipoCorreo {

    public interface Basic {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Basic.class)
    private int id;

    @Column(name = "nombre")
    @NotNull
    @JsonView(Basic.class)
    private String nombre;

    @Column(name = "estado", length = 1, columnDefinition = "char(1) default '1'")
    private String estado;

    public TipoCorreo() {}

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
