package com.maiaaldeco.portfolio.repository;

import com.maiaaldeco.portfolio.entity.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long>{
    Optional<Habilidad>findByHabilidad(String habilidad);
    boolean existsByHabilidad(String habilidad);
}
