package com.distribuidos.microservicioventas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VentasRepositorio extends CrudRepository<Venta,Integer> {
    Venta findVentaByFolio(String folio);
    List<Venta> findAllVentasByIdCliente(Integer idCliente);
    List<Venta> findAllVentasByFecha(LocalDate fecha);
    
}
