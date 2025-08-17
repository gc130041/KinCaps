package com.kincaps.www.repository;

import com.kincaps.www.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}