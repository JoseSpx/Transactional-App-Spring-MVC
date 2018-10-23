package com.upn.webtransactional.dao;

import com.upn.webtransactional.exceptions.ClientTransactionException;
import com.upn.webtransactional.exceptions.PedidoTransactionException;
import com.upn.webtransactional.model.*;
import com.upn.webtransactional.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    private ClienteService clienteService;
    private ClienteCorreoService clienteCorreoService;
    private PedidoService pedidoService;
    private ProductoService productoService;
    private PedidoProductoService pedidoProductoService;

    @Autowired
    public TransactionDaoImpl(ClienteService clienteService,
                              ClienteCorreoService clienteCorreoService,
                              PedidoService pedidoService,
                              ProductoService productoService,
                              PedidoProductoService pedidoProductoService) {
        this.clienteService = clienteService;
        this.clienteCorreoService = clienteCorreoService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
        this.pedidoProductoService = pedidoProductoService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ClientTransactionException.class)
    public void saveClienteData(Cliente cliente, List<ClienteCorreo> clienteCorreoList) throws ClientTransactionException{
        try{
            this.clienteService.insertarT(cliente);
            clienteCorreoList.forEach(clienteCorreo -> this.clienteCorreoService.insertar(clienteCorreo));
        }catch (Exception e){
            throw new ClientTransactionException(e.toString());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ClientTransactionException.class)
    public void savePedidoData(Pedido pedido, List<PedidoData> pedidoDataList) throws PedidoTransactionException {
        try{
            this.pedidoService.insertar(pedido);

            for(PedidoData pd : pedidoDataList){
                int idProducto = pd.getId();
                Producto producto = this.productoService.obtenerPorId(idProducto);
                if (producto == null) {
                    throw new PedidoTransactionException("");
                }

                int cantidad = producto.getCantidad();
                producto.setCantidad(cantidad - Integer.parseInt(pd.getCantidad()));
                this.productoService.insertar(producto);

                PedidoProducto pedidoProducto = new PedidoProducto();
                pedidoProducto.setPedido(pedido);
                pedidoProducto.setCantidad(Integer.parseInt(pd.getCantidad()));
                pedidoProducto.setPrecio(Double.parseDouble(pd.getPrecio()));
                pedidoProducto.setProducto(producto);

                this.pedidoProductoService.insertar(pedidoProducto);

            }

        }catch (Exception e){
            throw new PedidoTransactionException(e.toString());
        }
    }


}
