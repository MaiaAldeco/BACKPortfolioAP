package com.maiaaldeco.portfolio.repository;

import com.maiaaldeco.portfolio.entity.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    Optional<Persona>findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    List<Persona>findByContactoId(long id);
    void deleteByContactoId(long personaId);
}
