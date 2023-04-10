package com.distribuidos.microservicioventas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DetalleRepositorio extends CrudRepository<Detalle,Integer> {
    List<Detalle> findByidVenta(Venta venta);
    Detalle findByidVentaAndIdProducto(Integer venta, Integer idProducto);
}
