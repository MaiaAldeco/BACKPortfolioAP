package com.maiaaldeco.portfolio.repository;

import com.maiaaldeco.portfolio.entity.Estudio;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudioRepository extends JpaRepository<Estudio, Long>{
    
    Optional<Estudio>findByCurso(String curso);
    boolean existsByCurso(String curso);
    List<Estudio>findByPersonaId(long id);
    void deleteByPersonaId(long personaId);
}
