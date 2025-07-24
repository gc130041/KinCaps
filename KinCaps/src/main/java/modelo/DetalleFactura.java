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
@Table(name = "detalleFactura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleFactura")
    private int idDetalleFactura;

    @ManyToOne
    @JoinColumn(name = "idFactura", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "idGorra", nullable = false)
    private Gorras gorra;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precioVenta", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    public DetalleFactura() {
    }

    public DetalleFactura(Factura factura, Gorras gorra, int cantidad, BigDecimal precioVenta) {
        this.factura = factura;
        this.gorra = gorra;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Gorras getGorra() {
        return gorra;
    }

    public void setGorra(Gorras gorra) {
        this.gorra = gorra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
}