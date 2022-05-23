package com.maiaaldeco.portfolio.repository;

import com.maiaaldeco.portfolio.entity.Trabajo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long>{
    Optional<Trabajo>findByTitulo(String titulo);
    boolean existsByTitulo(String titulo);
    List<Trabajo>findByPersonaId(long id);
    void deleteByPersonaId(long personaId);
}
