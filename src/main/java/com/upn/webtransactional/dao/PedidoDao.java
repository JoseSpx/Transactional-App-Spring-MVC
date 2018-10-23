package com.upn.webtransactional.dao;

import com.upn.webtransactional.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoDao extends JpaRepository<Pedido, Integer> {

    List<Pedido> findAllByEstadoEquals(String estado);

}
