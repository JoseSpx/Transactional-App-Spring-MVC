package com.upn.webtransactional.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.upn.webtransactional.model.Cliente;
import com.upn.webtransactional.model.Producto;
import com.upn.webtransactional.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/producto")
public class ProductoController {

    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public String lista(Model model){
        List<Producto> productoList = this.productoService.obtenerTodos();
        model.addAttribute("productoList", productoList);
        return "producto/lista";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.GET)
    public String registrar(){
        return "producto/registrar";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody String registrarData(@RequestBody Producto producto){

        Producto p = this.productoService.obtenerProductoPorCodigo(producto.getCodigo());

        if (p != null){
            return "fail";
        }

        producto.setEstado("1");
        return "ok";
    }

    @RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
    public String verDetalle(@PathVariable("id") int id, Model model){
        Producto producto = this.productoService.obtenerPorId(id);
        if (producto == null){
            return "redirect:/producto/lista";
        }
        model.addAttribute("producto", producto);
        return "producto/ver";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable("id") int id){
        Producto producto = this.productoService.obtenerPorId(id);
        producto.setEstado("0");
        this.productoService.insertar(producto);
        return "redirect:/producto/lista";
    }

    @JsonView(Producto.Basic.class)
    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> productoList = this.productoService.obtenerTodos();
        if (productoList == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productoList, HttpStatus.OK);
    }


}
