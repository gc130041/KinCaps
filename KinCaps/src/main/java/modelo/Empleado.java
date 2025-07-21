package modelo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado")
    private int idEmpleado;

    @Column(name = "puesto", length = 50)
    private String puesto;

    @Column(name = "fechaContratacion")
    private LocalDate fechaContratacion;

    public Empleado() {
        super();
    }

    public Empleado(String nombre, String apellido, String email, String telefono, String direccion, String contrasenaHash, String puesto, LocalDate fechaContratacion) {
        super(nombre, apellido, email, telefono, direccion, contrasenaHash);
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Empleado{"
                + "idEmpleado=" + idEmpleado
                + ", nombre='" + getNombre() + '\''
                + ", apellido='" + getApellido() + '\''
                + ", email='" + getEmail() + '\''
                + ", puesto='" + puesto + '\''
                + '}';
    }
}
