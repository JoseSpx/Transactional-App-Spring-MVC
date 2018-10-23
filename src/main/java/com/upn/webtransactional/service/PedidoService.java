package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.Dao;
import com.upn.webtransactional.model.Pedido;

import java.util.List;

public interface PedidoService extends Dao<Pedido, Integer> {

    @Override
    void insertar(Pedido pedido);

    @Override
    Pedido obtenerPorId(Integer id);

    @Override
    List<Pedido> obtenerTodos();

    @Override
    void eliminar(Integer id);
}
