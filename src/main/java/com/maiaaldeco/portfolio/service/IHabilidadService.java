package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Habilidad;
import java.util.List;
import java.util.Optional;

public interface IHabilidadService {
    
    public List<Habilidad> list();
    public Optional<Habilidad> getOne(long id);
    public List<Habilidad> getByHabilidad(String habilidad);
    public void save(Habilidad habilidad);
    public void delete(long id);
    public boolean existsById(long id);
    public boolean existsByHabilidad(String habilidad);
    List<Habilidad>findByPersonaId(long id);
    void deleteByPersonaId(long personaId);
}
