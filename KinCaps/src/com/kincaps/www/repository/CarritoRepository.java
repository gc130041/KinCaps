package com.kincaps.www.repository;

import modelo.Carrito;
import modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByClienteAndEstado(Cliente cliente, Carrito.Estado estado);

    @Query("SELECT DISTINCT c FROM Carrito c LEFT JOIN FETCH c.detalles d LEFT JOIN FETCH d.gorra g WHERE c.cliente.idCliente = :clienteId ORDER BY c.fechaCreacion DESC")
    List<Carrito> findPedidosByClienteIdWithDetalles(int clienteId);
}