package com.upn.webtransactional.service;

import com.upn.webtransactional.exceptions.ClientTransactionException;
import com.upn.webtransactional.exceptions.PedidoTransactionException;
import com.upn.webtransactional.model.Cliente;
import com.upn.webtransactional.model.ClienteCorreo;
import com.upn.webtransactional.model.Pedido;
import com.upn.webtransactional.model.PedidoData;

import java.util.List;

public interface TransactionService {

    void insertarClienteDataT(Cliente cliente, List<ClienteCorreo> clienteCorreoList) throws ClientTransactionException;
    void insertarPedidoData(Pedido pedido, List<PedidoData> pedidoDataList) throws PedidoTransactionException;
}
