package com.upn.webtransactional.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.upn.webtransactional.exceptions.PedidoTransactionException;
import com.upn.webtransactional.model.*;
import com.upn.webtransactional.service.ClienteService;
import com.upn.webtransactional.service.PedidoService;
import com.upn.webtransactional.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pedido")
public class PedidoController {

    private PedidoService pedidoService;
    private ClienteService clienteService;
    private TransactionService transactionService;

    @Autowired
    public PedidoController(PedidoService pedidoService, ClienteService clienteService, TransactionService transactionService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/{idpedido}/cliente/{idcliente}/eliminar", method = RequestMethod.GET)
    public String eliminar(@PathVariable("idpedido") int idpedido, @PathVariable("idcliente") int idcliente){
        Pedido pedido = this.pedidoService.obtenerPorId(idpedido);
        if (pedido == null){
            return "redirect:/cliente/" + idcliente + "/pedido";
        }

        pedido.setEstado("0");
        this.pedidoService.insertar(pedido);
        return "redirect:/cliente/" + idcliente + "/pedido";

    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public @ResponseBody String registrar(@RequestBody PedidoJson pedidoJson, BindingResult result) {
        if (result.hasErrors()){
            return "fail";
        }

        Cliente cliente = this.clienteService.obtenerPorId(pedidoJson.getClienteid());
        if (cliente == null){
            return "fail";
        }

        try{

            double monto = 0.0;
            List<PedidoData> pedidoDataList = pedidoJson.getProductoPedidoList();

            for(PedidoData pd : pedidoDataList){
                monto += Double.parseDouble(pd.getMonto());
            }

            Pedido pedido = new Pedido();
            pedido.setEstado("1");
            pedido.setClientePedido(cliente);
            pedido.setMonto(monto);

            this.transactionService.insertarPedidoData(pedido, pedidoDataList);
        }catch (PedidoTransactionException e){
            return "fail";
        }

        return "ok";
    }

    interface Pedidos extends Pedido.Basic, Pedido.Detail, PedidoProducto.Basic,PedidoProducto.DetailProducto,
            Producto.Basic {}

    @JsonView(Pedidos.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ver(@PathVariable("id") int id, Model model){
        Pedido pedido = this.pedidoService.obtenerPorId(id);
        if (pedido == null) {
            return "redirect:/";
        }

        model.addAttribute("pedido",pedido);
        return "/pedido/ver";
    }

}
