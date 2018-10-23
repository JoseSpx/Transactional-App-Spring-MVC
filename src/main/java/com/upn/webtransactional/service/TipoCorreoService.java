package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.Dao;
import com.upn.webtransactional.model.TipoCorreo;

import java.util.List;

public interface TipoCorreoService extends Dao<TipoCorreo, Integer> {

    @Override
    void insertar(TipoCorreo tipoCorreo);

    @Override
    TipoCorreo obtenerPorId(Integer id);

    @Override
    List<TipoCorreo> obtenerTodos();

    @Override
    void eliminar(Integer id);
}
