package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.ClienteDao;
import com.upn.webtransactional.exceptions.ClientTransactionException;
import com.upn.webtransactional.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteDao clienteDao;

    @Autowired
    public ClienteServiceImpl(ClienteDao clienteDao){
        this.clienteDao = clienteDao;
    }

    @Override
    public void insertarT(Cliente cliente) throws ClientTransactionException {
        try{
            this.clienteDao.save(cliente);
        }catch (Exception e){
            throw new ClientTransactionException("hola");
        }
    }

    @Override
    public void insertar(Cliente cliente) {
        this.clienteDao.save(cliente);
    }

    @Override
    public Cliente obtenerPorId(Integer id) {
        return this.clienteDao.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return this.clienteDao.findAllByEstadoEquals("1");
    }

    @Override
    public void eliminar(Integer id) {
        this.clienteDao.deleteById(id);
    }
}
