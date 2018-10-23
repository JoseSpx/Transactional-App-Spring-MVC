package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.ClienteCorreoDao;
import com.upn.webtransactional.model.ClienteCorreo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCorreoServiceImpl implements ClienteCorreoService {

    private ClienteCorreoDao clienteCorreoDao;

    @Autowired
    public ClienteCorreoServiceImpl(ClienteCorreoDao clienteCorreoDao){
        this.clienteCorreoDao = clienteCorreoDao;
    }

    @Override
    public void insertar(ClienteCorreo clienteCorreo) {
        this.clienteCorreoDao.save(clienteCorreo);
    }

    @Override
    public ClienteCorreo obtenerPorId(Integer id) {
        return this.clienteCorreoDao.findById(id).orElse(null);
    }

    @Override
    public List<ClienteCorreo> obtenerTodos() {
        return this.clienteCorreoDao.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        this.clienteCorreoDao.deleteById(id);
    }
}
