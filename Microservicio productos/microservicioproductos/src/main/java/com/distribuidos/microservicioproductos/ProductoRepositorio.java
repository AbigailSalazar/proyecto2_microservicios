package com.distribuidos.microservicioproductos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends CrudRepository<Producto,Integer>{
    List<Producto> findAllProductosByMarca(String marca);
    List<Producto> findAllProductosByCodigo(String codigo);
}
