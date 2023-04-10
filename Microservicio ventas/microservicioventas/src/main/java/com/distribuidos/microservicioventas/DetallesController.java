package com.distribuidos.microservicioventas;

import java.util.List;

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
@RequestMapping("/ventas")
public class DetallesController {
    
    @Autowired
    private VentasRepositorio ventasRepositorio;

    @Autowired
    private  DetalleRepositorio detalleRepositorio;
    

    @PostMapping(path = "/{idVenta}/detalles")
    public @ResponseBody int addDetalle(@PathVariable(value = "idVenta") Integer idVenta, @RequestBody Detalle detalle){
        Venta v = ventasRepositorio.findById(idVenta).orElse(null);
        if(v!=null){
            detalle.setVenta(v);

            v.getDetalles().add(detalle);
            v.setTotal(v.getTotal()+detalle.getTotal());
            ventasRepositorio.save(v);

            return 200;
        }
        return 404;
    } 

    @PutMapping(path = "/{idVenta}/detalles/{id}")
    public @ResponseBody int updateDetalle(@PathVariable(value = "idVenta") Integer idVenta,@PathVariable(value = "id") Integer id, @RequestBody Detalle detalle){
        Venta v = ventasRepositorio.findById(idVenta).orElse(null);
        Detalle d = detalleRepositorio.findById(id).orElse(null);
        if(v!=null&&d!=null){
            d.setVenta(v);
            d.setCantidad(detalle.getCantidad());
            d.setIdProducto(detalle.getIdProducto());
            d.setTotal(detalle.getTotal());
            double total = v.getTotal()-d.getTotal()+detalle.getTotal();
            v.setTotal(total);
            ventasRepositorio.save(v);
            return 200;
        }
        return 404;
    } 

    
    @DeleteMapping(path = "/{idVenta}/detalles/{id}")
    public @ResponseBody int deleteVenta(@PathVariable("idVenta") int idVenta,@PathVariable("id") int id){
        Detalle d = detalleRepositorio.findById(id).orElse(null);
        Venta v = ventasRepositorio.findById(idVenta).orElse(null);
        if(d!=null&&v!=null){
            List<Detalle> detalles= v.getDetalles();
            detalles.remove(d);
            v.setTotal(v.getTotal()-d.getTotal());
            ventasRepositorio.save(v);
            return 200;
        }
        return 404;
    }

}
