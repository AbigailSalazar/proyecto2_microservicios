package com.distribuidos.microservicioventas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
public class VentasController {
    
    @Autowired
    private VentasRepositorio ventasRepositorio;
    
    @PostMapping(path = "/add")
    public @ResponseBody int add(@RequestBody Venta venta){
        for(Detalle d:venta.getDetalles()){
            d.setVenta(venta);
        }
        ventasRepositorio.save(venta);

        return 201;
    } 

    @GetMapping(path = "/{id}")
    public @ResponseBody Venta getVenta(@PathVariable(value="id") int id){
            Venta venta = ventasRepositorio.findById(id).orElse(null);
            return venta;
    }

    @GetMapping(path = "/idCliente={idCliente}")
    public @ResponseBody Iterable<Venta> getInventarioByProducto(@PathVariable(value="idCliente") int idCliente){
            Iterable<Venta> ventas = ventasRepositorio.findAllVentasByIdCliente(idCliente);
            return ventas;
    }

    
    @GetMapping(path = "/fecha/date={date}")
    public @ResponseBody Iterable<Venta> getInventarioByFecha(@PathVariable(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha){
            Iterable<Venta> ventas = ventasRepositorio.findAllVentasByFecha(fecha);
            return ventas;
    }

    @GetMapping(path = "/folio={folio}")
    public @ResponseBody Venta getInventarioByProducto(@PathVariable(value="folio") String folio){
            Venta inventario = ventasRepositorio.findVentaByFolio(folio);
            return inventario;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Venta> getAllVentas() {
      return ventasRepositorio.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody int deleteVenta(@PathVariable("id") int id){
        Venta v= ventasRepositorio.findById(id).get();
        if(v!=null){
            ventasRepositorio.deleteById(id);
            return 200;
        }
        return 404;
    }
}
