package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.Dao;
import com.upn.webtransactional.model.PedidoProducto;

import java.util.List;

public interface PedidoProductoService extends Dao<PedidoProducto, Integer> {

    @Override
    void insertar(PedidoProducto pedidoProducto);

    @Override
    PedidoProducto obtenerPorId(Integer id);

    @Override
    List<PedidoProducto> obtenerTodos();

    @Override
    void eliminar(Integer id);
}
