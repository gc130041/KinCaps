package com.kincaps.www.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
@DiscriminatorValue("CLIENTE")
@PrimaryKeyJoinColumn(name = "idCliente")
public class Cliente extends Usuario {

    public enum Estado {
        ACTIVO, INACTIVO
    }

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                ", nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", estado=" + estado +
                '}';
    }
}