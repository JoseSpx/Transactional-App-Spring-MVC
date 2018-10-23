package com.upn.webtransactional.dao;

import com.upn.webtransactional.exceptions.ClientTransactionException;
import com.upn.webtransactional.exceptions.PedidoTransactionException;
import com.upn.webtransactional.model.Cliente;
import com.upn.webtransactional.model.ClienteCorreo;
import com.upn.webtransactional.model.Pedido;
import com.upn.webtransactional.model.PedidoData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao {

    void saveClienteData(Cliente cliente, List<ClienteCorreo> clienteCorreoList) throws ClientTransactionException;
    void savePedidoData(Pedido pedido, List<PedidoData> pedidoDataList) throws PedidoTransactionException;
}
