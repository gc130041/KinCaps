package com.kincaps.www.repository;

import com.kincaps.www.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    Optional<Empleado> findByEmail(String email);
    boolean existsByEmail(String email);
}