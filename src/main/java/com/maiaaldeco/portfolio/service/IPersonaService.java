package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Persona;
import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    
    public List<Persona> list();
    public Optional<Persona> getOne(long id);
    public Optional<Persona> getByNombre(String nombre);
    public void save(Persona persona);
    public void delete(long id);
    public boolean existsById(long id);
    public boolean existsByNombre(String nombre);
    List<Persona>findByContactoId(long id);
    void deleteByContactoId(long personaId);
}
