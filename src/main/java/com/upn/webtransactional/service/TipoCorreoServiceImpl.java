package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.TipoCorreoDao;
import com.upn.webtransactional.model.TipoCorreo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoCorreoServiceImpl implements TipoCorreoService {

    private TipoCorreoDao tipoCorreoDao;

    @Autowired
    public TipoCorreoServiceImpl(TipoCorreoDao tipoCorreoDao) {
        this.tipoCorreoDao = tipoCorreoDao;
    }

    @Override
    public void insertar(TipoCorreo tipoCorreo) {
        this.tipoCorreoDao.save(tipoCorreo);
    }

    @Override
    public TipoCorreo obtenerPorId(Integer id) {
        return this.tipoCorreoDao.findById(id).orElse(null);
    }

    @Override
    public List<TipoCorreo> obtenerTodos() {
        return this.tipoCorreoDao.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        this.tipoCorreoDao.deleteById(id);
    }
}
