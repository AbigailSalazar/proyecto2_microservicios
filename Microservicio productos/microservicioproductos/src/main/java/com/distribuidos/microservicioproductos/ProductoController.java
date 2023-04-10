package com.distribuidos.microservicioproductos;



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
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    ProductoRepositorio productoRepositorio;

    @Autowired
    UnidadMedRepositorio uMedRepositorio;

    @PostMapping(path = "/add/{idMedida}")
    public @ResponseBody int addProducto(@RequestBody Producto producto, @PathVariable(value="idMedida") int id){
        UnidadMedida um = uMedRepositorio.findById(id).get();

        if(um!=null){
            producto.setUnidadMedida(um);
            productoRepositorio.save(producto);
        }
       
        return 201;
    } 

    @PostMapping(path = "/addMedida")
    public @ResponseBody int addProductoMedida(@RequestBody UnidadMedida uMedida){
        uMedRepositorio.save(uMedida);
       
        return 201;
    } 

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Producto> getAlladdProductos() {
      return productoRepositorio.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Producto getProducto(@PathVariable(value="id") int id){
            Producto producto = productoRepositorio.findById(id).orElse(null);
            return producto;
    }

    @GetMapping(path="/marca={marca}")
    public @ResponseBody Iterable<Producto> findProductosByMarca(@PathVariable(value="marca") String marca){
        return productoRepositorio.findAllProductosByMarca(marca);
    }
    @GetMapping(path="/codigo={codigo}")
    public @ResponseBody Iterable<Producto> findProductosByCodigo(@PathVariable(value="codigo") String codigo){
        return productoRepositorio.findAllProductosByCodigo(codigo);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody int deleteProducto(@PathVariable("id") int id){
        Producto p= productoRepositorio.findById(id).get();
        if(p!=null){
            productoRepositorio.deleteById(id);
            return 200;
        }
        return 404;
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody int updateProducto(@RequestBody Producto producto, @PathVariable("id") int id){
        Producto p= productoRepositorio.findById(id).get();
        p.setCodigo(producto.getCodigo());
        p.setMarca(producto.getMarca());
        p.setNombre(producto.getNombre());
        p.setPrecio(producto.getPrecio());

        UnidadMedida um = uMedRepositorio.findById(producto.getUnidadMedida().getId()).get();
        p.setUnidadMedida(um);
        productoRepositorio.save(p);
        return 200;
    }
}
