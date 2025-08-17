package com.kincaps.www.repository;

import modelo.Gorras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GorraRepository extends JpaRepository<Gorras, Integer> {
    @Query(value = "SELECT g.* FROM gorras g " +
            "JOIN detalleFactura df ON g.idGorra = df.idGorra " +
            "GROUP BY g.idGorra " +
            "ORDER BY SUM(df.cantidad) DESC " +
            "LIMIT :limite", nativeQuery = true)
    List<Gorras> findMasVendidas(@Param("limite") int limite);

    @Query(value = "SELECT * FROM gorras ORDER BY RAND() LIMIT :limite", nativeQuery = true)
    List<Gorras> findAleatorias(@Param("limite") int limite);
}