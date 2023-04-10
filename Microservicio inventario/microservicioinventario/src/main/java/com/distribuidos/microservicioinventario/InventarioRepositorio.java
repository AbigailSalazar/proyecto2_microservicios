package com.distribuidos.microservicioinventario;

import org.springframework.data.repository.CrudRepository;

public interface InventarioRepositorio extends CrudRepository<Inventario,Integer> {
    Inventario findInventarioByIdProducto(int id);
}
