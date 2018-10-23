package com.upn.webtransactional.model;

import java.util.List;

public class ClienteJson {

    private String nombre;
    private String apellido;
    private List<CorreoData> listaCorreos;

    public ClienteJson(){}

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

    public List<CorreoData> getListaCorreos() {
        return listaCorreos;
    }

    public void setListaCorreos(List<CorreoData> listaCorreos) {
        this.listaCorreos = listaCorreos;
    }

    public void mostrarListaCorreos(){
        this.listaCorreos.forEach(c -> System.out.println(c.get_desCorreo() + " - " + c.get_tipo()));
    }

}

