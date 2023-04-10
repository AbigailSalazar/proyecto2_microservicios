package com.distribuidos.microservicioventas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Integer idCliente;
    private double total;
    private String folio;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate fecha;

    @OneToMany( mappedBy = "idVenta", cascade = CascadeType.ALL,orphanRemoval = true,  fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Detalle> detalles = new ArrayList<>();

    public Venta() {
    }

    public Venta(Integer idCliente, double total, String folio, LocalDate fecha) {
        this.idCliente = idCliente;
        this.total = total;
        this.folio = folio;
        this.fecha = fecha;
    }

    public Venta(Integer idCliente, double total, String folio, LocalDate fecha, List<Detalle> detalles) {
        this.idCliente = idCliente;
        this.total = total;
        this.folio = folio;
        this.fecha = fecha;
        this.detalles = detalles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }


    
}
