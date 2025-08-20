package com.kincaps.www.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
@DiscriminatorValue("EMPLEADO")
@PrimaryKeyJoinColumn(name = "idEmpleado")
public class Empleado extends Usuario {

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
    
    // Getter específico para compatibilidad con templates
    public int getIdEmpleado() {
        return getIdUsuario();
    }
}