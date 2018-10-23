package com.upn.webtransactional.dao;

import com.upn.webtransactional.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoDao extends JpaRepository<Producto, Integer> {

    List<Producto> findAllByEstadoEquals(String estado);
    Producto findByCodigoEquals(String codigo);

}
