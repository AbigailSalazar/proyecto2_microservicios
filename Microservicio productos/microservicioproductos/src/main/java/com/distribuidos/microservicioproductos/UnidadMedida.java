package com.distribuidos.microservicioproductos;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String codigo;

    @OneToMany(mappedBy = "unidadMedida")
    private Set<Producto> productos;

    
    public UnidadMedida() {
    }
    
    public UnidadMedida(String nombre, String codigo, Set<Producto> productos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.productos = productos;
    }

    public UnidadMedida(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void addProducto(Producto producto){
        productos.add(producto);
    }

    public void removeProducto(Producto producto){
        productos.remove(producto);
    }

}
