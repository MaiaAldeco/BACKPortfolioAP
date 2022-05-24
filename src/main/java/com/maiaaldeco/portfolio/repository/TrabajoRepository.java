package com.maiaaldeco.portfolio.repository;

import com.maiaaldeco.portfolio.entity.Trabajo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long>{
    List<Trabajo>findByTitulo(String titulo);
    boolean existsByTitulo(String titulo);
    List<Trabajo>findByPersonaId(long id);
    void deleteByPersonaId(long personaId);
}
