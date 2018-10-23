package com.upn.webtransactional.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.upn.webtransactional.exceptions.ClientTransactionException;
import com.upn.webtransactional.model.*;
import com.upn.webtransactional.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;
    private TipoCorreoService tipoCorreoService;
    private TransactionService transactionService;
    private PedidoService pedidoService;
    private ProductoService productoService;

    @Autowired
    public ClienteController(ClienteService clienteService, TipoCorreoService tipoCorreoService,
                             TransactionService transactionService,
                             PedidoService pedidoService,
                             ProductoService productoService) {
        this.clienteService = clienteService;
        this.tipoCorreoService = tipoCorreoService;
        this.transactionService = transactionService;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public String listar(Model model){
        List<Cliente> clienteList = this.clienteService.obtenerTodos();

        if (clienteList == null) {
            System.out.println("Lista clientes vacia");
        }
        else{
            System.out.println("Lista cliente llena");
            System.out.println("Tamano : " + clienteList.size());
            clienteList.forEach(cliente -> {
                System.out.println(cliente.getNombre());
            });
        }

        model.addAttribute("clienteList", clienteList);
        return "cliente/lista";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.GET)
    public String formRegistrar(Model model){
        List<TipoCorreo> tipoCorreoList = this.tipoCorreoService.obtenerTodos();
        model.addAttribute("tipoCorreoList", tipoCorreoList);
        return "cliente/registrar";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String insertar(@RequestBody ClienteJson clienteJson, BindingResult result) {

        if (result.hasErrors()){
            return "fail";
        }

        Cliente cliente  = new Cliente();
        cliente.setNombre(clienteJson.getNombre());
        cliente.setApellido(clienteJson.getApellido());
        cliente.setEstado("1");

        List<ClienteCorreo> clienteCorreoList = new ArrayList<>();
        for(int i = 0; i < clienteJson.getListaCorreos().size(); i++){
            CorreoData cd = clienteJson.getListaCorreos().get(i);
            TipoCorreo tipoCorreo = this.tipoCorreoService.obtenerPorId(Integer.parseInt(cd.get_tipo()));
            ClienteCorreo clienteCorreo = new ClienteCorreo();
            clienteCorreo.setCliente(cliente);
            clienteCorreo.setCorreo(cd.get_desCorreo());
            clienteCorreo.setTipoCorreo(tipoCorreo);
            clienteCorreo.setEstado("1");
            clienteCorreoList.add(clienteCorreo);
        }

        try {
            this.transactionService.insertarClienteDataT(cliente, clienteCorreoList);

        } catch (ClientTransactionException e) {
            return "fail";
        }

        return "ok";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable("id") int id){

        Cliente cliente = this.clienteService.obtenerPorId(id);
        if (cliente != null){
            cliente.setEstado("0");
            this.clienteService.insertar(cliente);
        }

        System.out.println("Se elimina : " + id);
        return "redirect:/cliente/lista";
    }

    interface ClienteCorreos extends Cliente.Basic, Cliente.DetailCorreo, ClienteCorreo.Detail, TipoCorreo.Basic {}

    @JsonView(ClienteCorreos.class)
    @RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
    public String verDetalleCliente(@PathVariable("id") int id, Model model){
        Cliente cliente = this.clienteService.obtenerPorId(id);
        if (cliente == null){
            return "redirect:/cliente/lista";
        }

        model.addAttribute("cliente", cliente);
        return "cliente/ver";
    }

    interface ClientePedido extends Cliente.Basic, Cliente.DetailPedido, Pedido.Basic {}

    @JsonView(ClientePedido.class)
    @RequestMapping(value = "/{id}/pedido", method = RequestMethod.GET)
    public String pedidoForm(@PathVariable("id") int id, Model model){
        Cliente cliente = this.clienteService.obtenerPorId(id);
        if (cliente == null){
            return "redirect:/cliente/lista";
        }

        Set<Pedido> pedidoList = cliente.getPedidoList();
        Set<Pedido> newPedidoList = new HashSet<>();
        for (Pedido p : pedidoList) {
            if (p.getEstado().equals("1")){
                newPedidoList.add(p);
            }
        }

        cliente.setPedidoList(newPedidoList);

        model.addAttribute("cliente", cliente);
        return "pedido/lista";
    }

    @RequestMapping(value = "/{id}/pedido/registrar", method = RequestMethod.GET)
    public String nuevoPedido(@PathVariable("id") int id, Model model){
        Cliente cliente = this.clienteService.obtenerPorId(id);
        if (cliente == null){
            return "redirect:/cliente/" + id + "/pedido";
        }

        // List<Producto> productoList = this.productoService.obtenerTodos();
        model.addAttribute("cliente", cliente);
        // model.addAttribute("productoList", productoList);

        return "pedido/registrar";
    }


}
