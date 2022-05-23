package com.maiaaldeco.portfolio.repository;

import com.maiaaldeco.portfolio.entity.Experiencia;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long>{
    Optional<Experiencia>findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    List<Experiencia>findByPersonaId(long id);
    void deleteByPersonaId(long personaId);
}
