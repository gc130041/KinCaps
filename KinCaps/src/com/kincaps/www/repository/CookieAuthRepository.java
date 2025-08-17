package com.kincaps.www.repository;

import com.kincaps.www.entity.CookieAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CookieAuthRepository extends JpaRepository<CookieAuth, Integer> {
    Optional<CookieAuth> findBySelector(String selector);
    void deleteBySelector(String selector);
}