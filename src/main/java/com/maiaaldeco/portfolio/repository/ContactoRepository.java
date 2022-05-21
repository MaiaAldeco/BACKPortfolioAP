package com.maiaaldeco.portfolio.repository;

import com.maiaaldeco.portfolio.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long>{
    
}
