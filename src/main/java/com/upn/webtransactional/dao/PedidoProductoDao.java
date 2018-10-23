package com.upn.webtransactional.dao;

import com.upn.webtransactional.model.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoProductoDao extends JpaRepository<PedidoProducto, Integer> {
}
