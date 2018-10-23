package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.Dao;
import com.upn.webtransactional.exceptions.ClientTransactionException;
import com.upn.webtransactional.model.Cliente;

import java.util.List;

public interface ClienteService extends Dao<Cliente, Integer> {

    void insertarT(Cliente cliente) throws ClientTransactionException;

    @Override
    void insertar(Cliente cliente);

    @Override
    Cliente obtenerPorId(Integer id);

    @Override
    List<Cliente> obtenerTodos();

    @Override
    void eliminar(Integer id);
}
