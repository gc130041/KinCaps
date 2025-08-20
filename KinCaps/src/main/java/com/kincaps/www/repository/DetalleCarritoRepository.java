package com.kincaps.www.repository;

import com.kincaps.www.entity.Carrito;
import com.kincaps.www.entity.DetalleCarrito;
import com.kincaps.www.entity.Gorras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Integer> {
    Optional<DetalleCarrito> findByCarritoAndGorra(Carrito carrito, Gorras gorra);

    @Query("SELECT d FROM DetalleCarrito d JOIN FETCH d.gorra WHERE d.carrito = :carrito")
    List<DetalleCarrito> findByCarritoWithGorras(Carrito carrito);
}