package com.upn.webtransactional.service;

import com.upn.webtransactional.dao.TransactionDao;
import com.upn.webtransactional.exceptions.ClientTransactionException;
import com.upn.webtransactional.exceptions.PedidoTransactionException;
import com.upn.webtransactional.model.Cliente;
import com.upn.webtransactional.model.ClienteCorreo;
import com.upn.webtransactional.model.Pedido;
import com.upn.webtransactional.model.PedidoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public void insertarClienteDataT(Cliente cliente, List<ClienteCorreo> clienteCorreoList) throws ClientTransactionException{
        this.transactionDao.saveClienteData(cliente, clienteCorreoList);
    }

    @Override
    public void insertarPedidoData(Pedido pedido, List<PedidoData> pedidoDataList) throws PedidoTransactionException {
        this.transactionDao.savePedidoData(pedido, pedidoDataList);
    }

}
