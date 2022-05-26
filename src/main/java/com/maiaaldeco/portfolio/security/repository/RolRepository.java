package com.maiaaldeco.portfolio.security.repository;

import com.maiaaldeco.portfolio.security.entity.Rol;
import com.maiaaldeco.portfolio.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
