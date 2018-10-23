package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.Dao;
import com.upn.webtransactional.model.Producto;

import java.util.List;

public interface ProductoService extends Dao<Producto, Integer> {

    @Override
    void insertar(Producto producto);

    @Override
    Producto obtenerPorId(Integer id);

    @Override
    List<Producto> obtenerTodos();

    @Override
    void eliminar(Integer id);

    Producto obtenerProductoPorCodigo(String codigo);

}
