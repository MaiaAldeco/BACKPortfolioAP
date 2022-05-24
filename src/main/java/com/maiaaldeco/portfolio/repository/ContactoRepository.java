package com.maiaaldeco.portfolio.repository;

import com.maiaaldeco.portfolio.entity.Contacto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long>{
    boolean existsByEmail(String email);
    List<Contacto>findByPersonaId(long id);
    List<Contacto>findByEmail(String email);
}
