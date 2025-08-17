package com.kincaps.www.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gorras")
public class Gorras {

    public enum Tipo {
        URBANA, DEPORTIVA, FORMULA_1, OTRO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGorra")
    private int idGorra;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "nombreGorra", nullable = false, length = 80)
    private String nombreGorra;

    @Column(name = "color", nullable = false, length = 30)
    private String color;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "imagen", length = 80)
    private String imagen;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;

    public Gorras() {
    }

    public Gorras(Tipo tipo, String marca, String nombreGorra, String color, String descripcion, String imagen, BigDecimal precio, int stock, Proveedor proveedor) {
        this.tipo = tipo;
        this.marca = marca;
        this.nombreGorra = nombreGorra;
        this.color = color;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    public int getIdGorra() {
        return idGorra;
    }

    public void setIdGorra(int idGorra) {
        this.idGorra = idGorra;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getNombreGorra() {
        return nombreGorra;
    }

    public void setNombreGorra(String nombreGorra) {
        this.nombreGorra = nombreGorra;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}