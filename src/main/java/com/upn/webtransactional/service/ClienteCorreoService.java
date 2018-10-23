package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.Dao;
import com.upn.webtransactional.model.ClienteCorreo;

import java.util.List;

public interface ClienteCorreoService extends Dao<ClienteCorreo, Integer> {

    @Override
    void insertar(ClienteCorreo clienteCorreo);

    @Override
    ClienteCorreo obtenerPorId(Integer id);

    @Override
    List<ClienteCorreo> obtenerTodos();

    @Override
    void eliminar(Integer id);
}
