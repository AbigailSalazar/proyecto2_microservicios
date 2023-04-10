package com.distribuidos.microservicioclientes;

import org.springframework.data.repository.CrudRepository;

public interface ClientesRepositorio extends CrudRepository<Cliente,Integer>{
    Cliente findClienteByRfc(String rfc);
}
