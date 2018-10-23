package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.PedidoProductoDao;
import com.upn.webtransactional.model.PedidoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoProductoServiceImpl implements PedidoProductoService {

    private PedidoProductoDao pedidoProductoDao;

    @Autowired
    public PedidoProductoServiceImpl(PedidoProductoDao pedidoProductoDao) {
        this.pedidoProductoDao = pedidoProductoDao;
    }

    @Override
    public void insertar(PedidoProducto pedidoProducto) {
        this.pedidoProductoDao.save(pedidoProducto);
    }

    @Override
    public PedidoProducto obtenerPorId(Integer id) {
        return this.pedidoProductoDao.findById(id).orElse(null);
    }

    @Override
    public List<PedidoProducto> obtenerTodos() {
        return this.pedidoProductoDao.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        this.pedidoProductoDao.deleteById(id);
    }
}
