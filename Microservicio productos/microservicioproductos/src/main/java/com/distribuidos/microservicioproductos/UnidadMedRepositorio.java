package com.distribuidos.microservicioproductos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedRepositorio extends CrudRepository<UnidadMedida,Integer>{
    
}
