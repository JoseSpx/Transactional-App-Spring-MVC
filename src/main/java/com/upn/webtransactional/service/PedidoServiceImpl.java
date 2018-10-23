package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.PedidoDao;
import com.upn.webtransactional.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoDao pedidoDao;

    @Autowired
    public PedidoServiceImpl(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    @Override
    public void insertar(Pedido pedido) {
        this.pedidoDao.save(pedido);
    }

    @Override
    public Pedido obtenerPorId(Integer id) {
        return this.pedidoDao.findById(id).orElse(null);
    }

    @Override
    public List<Pedido> obtenerTodos() {
        return this.pedidoDao.findAllByEstadoEquals("1");
    }

    @Override
    public void eliminar(Integer id) {
        this.pedidoDao.deleteById(id);
    }
}
