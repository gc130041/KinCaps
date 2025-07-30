package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {

    public enum Estado {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private int idCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    public Cliente() {
        super();
    }

    public Cliente(String nombre, String apellido, String email, String telefono, String direccion, String contrasenaHash, Estado estado) {
        super(nombre, apellido, email, telefono, direccion, contrasenaHash);
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{"
                + "idCliente=" + idCliente
                + ", nombre='" + getNombre() + '\''
                + ", apellido='" + getApellido() + '\''
                + ", email='" + getEmail() + '\''
                + ", estado=" + estado
                + '}';
    }
}