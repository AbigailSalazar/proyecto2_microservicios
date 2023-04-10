package com.distribuidos.microservicioinventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
    
    @Autowired
    InventarioRepositorio inventarioRepositorio;

    @PostMapping(path = "/add")
    public @ResponseBody int add(@RequestBody Inventario inventario){
        
        inventarioRepositorio.save(inventario);
       
        return 201;
    } 

    @GetMapping(path = "/{id}")
    public @ResponseBody Inventario getInventario(@PathVariable(value="id") int id){
            Inventario inventario = inventarioRepositorio.findById(id).orElse(null);
            return inventario;
    }

    @GetMapping(path = "/idProducto={idproducto}")
    public @ResponseBody Inventario getInventarioByProducto(@PathVariable(value="idproducto") int id){
            Inventario inventario = inventarioRepositorio.findInventarioByIdProducto(id);
            return inventario;
    }


    
    @PutMapping(path = "/inventariar")
    public @ResponseBody int inventariar(@RequestBody Inventario inventario){
        
        Inventario i = inventarioRepositorio.findInventarioByIdProducto(inventario.getIdProducto());
        i.setCantidad(inventario.getCantidad()+i.getCantidad());
        inventarioRepositorio.save(i);
        
        return 201;
    } 
    @PutMapping(path = "/desinventariar")
    public @ResponseBody int desinventariar(@RequestBody Inventario inventario){
        
        Inventario i = inventarioRepositorio.findInventarioByIdProducto(inventario.getIdProducto());
        double cantidad =i.getCantidad()-inventario.getCantidad();
        if(cantidad>=0){
            i.setCantidad(cantidad);
            inventarioRepositorio.save(i);
            return 201;
        }
        
        return 400;
    } 

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Inventario> getAllInventario() {
      return inventarioRepositorio.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody int deleteInventario(@PathVariable("id") int id){
        Inventario p= inventarioRepositorio.findById(id).get();
        if(p!=null){
            inventarioRepositorio.deleteById(id);
            return 200;
        }
        return 404;
    }
}
