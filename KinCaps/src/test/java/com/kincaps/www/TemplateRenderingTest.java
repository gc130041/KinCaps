package com.kincaps.www;

import com.kincaps.www.entity.Empleado;
import com.kincaps.www.entity.Cliente;
import com.kincaps.www.entity.Proveedor;
import com.kincaps.www.entity.Gorras;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateRenderingTest {

    @Test
    public void testEntityIdMethods() {
        // Test Empleado ID access
        Empleado empleado = new Empleado();
        empleado.setIdUsuario(1);
        empleado.setNombre("Juan");
        empleado.setApellido("Pérez");
        empleado.setPuesto("Administrador");
        empleado.setFechaContratacion(LocalDate.now());
        
        assertEquals(1, empleado.getIdEmpleado());
        assertEquals("Juan", empleado.getNombre());
        assertEquals("Administrador", empleado.getPuesto());
        
        // Test Cliente ID access
        Cliente cliente = new Cliente();
        cliente.setIdUsuario(2);
        cliente.setNombre("María");
        cliente.setApellido("González");
        cliente.setEstado(Cliente.Estado.ACTIVO);
        
        assertEquals(2, cliente.getIdCliente());
        assertEquals("María", cliente.getNombre());
        assertEquals(Cliente.Estado.ACTIVO, cliente.getEstado());
        
        // Test Proveedor
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(3);
        proveedor.setNombre("Proveedor Test");
        proveedor.setContacto("contacto@test.com");
        proveedor.setTelefono("123456789");
        
        assertEquals(3, proveedor.getIdProveedor());
        assertEquals("Proveedor Test", proveedor.getNombre());
        
        // Test Gorras
        Gorras gorra = new Gorras();
        gorra.setIdGorra(4);
        gorra.setNombreGorra("Gorra Test");
        gorra.setTipo(Gorras.Tipo.URBANA);
        gorra.setMarca("Nike");
        gorra.setColor("Azul");
        gorra.setPrecio(new BigDecimal("25.99"));
        gorra.setStock(10);
        
        assertEquals(4, gorra.getIdGorra());
        assertEquals("Gorra Test", gorra.getNombreGorra());
        assertEquals(Gorras.Tipo.URBANA, gorra.getTipo());
    }
}