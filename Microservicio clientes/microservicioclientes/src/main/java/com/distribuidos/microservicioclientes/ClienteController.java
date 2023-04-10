package com.distribuidos.microservicioclientes;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    ClientesRepositorio clientesRepositorio;

    @PostMapping(path = "/add")
    public @ResponseBody int addProductoMedida(@RequestBody Cliente cliente){
        clientesRepositorio.save(cliente);
       
        return 201;
    } 

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Cliente> getAllClientes() {
      return clientesRepositorio.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Cliente getCliente(@PathVariable(value="id") int id){
            Cliente producto = clientesRepositorio.findById(id).orElse(null);
            return producto;
    }

    @GetMapping(path="/rfc={rfc}")
    public @ResponseBody Cliente findClienteByRfc(@PathVariable(value="rfc") String rfc){
        return clientesRepositorio.findClienteByRfc(rfc);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody int deleteProducto(@PathVariable("id") int id){
        try{
            Cliente p= clientesRepositorio.findById(id).get();
            if(p!=null){
                clientesRepositorio.deleteById(id);
                return 200;
            }
            return 404;
        }
        catch(Exception ex){
            throw new ResponseStatusException(
           HttpStatus.METHOD_NOT_ALLOWED, "No se puede eliminar este cliente porque ha realizado ventas", ex);
        }
  
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody int updateProducto(@RequestBody Cliente cliente, @PathVariable("id") int id){
        Cliente c= clientesRepositorio.findById(id).get();
        c.setContraseña(cliente.getContraseña());
        c.setCorreo(cliente.getCorreo());
        c.setNombre(cliente.getNombre());
        c.setRfc(cliente.getRfc());
        c.setTelefono(cliente.getTelefono());
        clientesRepositorio.save(c);
        return 200;
    }

}
