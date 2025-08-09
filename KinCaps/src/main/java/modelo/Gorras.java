package modelo;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

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

    public Gorras(Tipo tipo, String marca, String color, String descripcion, String imagen, BigDecimal precio, int stock, Proveedor proveedor) {
        this.tipo = tipo;
        this.marca = marca;
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