package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.ProductoDao;
import com.upn.webtransactional.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoDao productoDao;

    @Autowired
    public ProductoServiceImpl(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @Override
    public void insertar(Producto producto) {
        this.productoDao.save(producto);
    }

    @Override
    public Producto obtenerPorId(Integer id) {
        return this.productoDao.findById(id).orElse(null);
    }

    @Override
    public List<Producto> obtenerTodos() {
        return this.productoDao.findAllByEstadoEquals("1");
    }

    @Override
    public void eliminar(Integer id) {
        this.productoDao.deleteById(id);
    }

    @Override
    public Producto obtenerProductoPorCodigo(String codigo) {
        return this.productoDao.findByCodigoEquals(codigo);
    }
}
