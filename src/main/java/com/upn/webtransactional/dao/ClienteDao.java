package com.upn.webtransactional.dao;

import com.upn.webtransactional.exceptions.ClientTransactionException;
import com.upn.webtransactional.model.Cliente;
import com.upn.webtransactional.model.ClienteJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Integer> {

    List<Cliente> findAllByEstadoEquals(String estado);



}
