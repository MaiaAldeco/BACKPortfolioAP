package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Experiencia;
import java.util.List;
import java.util.Optional;

public interface IExperienciaService {
    
    public List<Experiencia> list();
    public Optional<Experiencia> getOne(long id);
    public Optional<Experiencia> getByNombre(String nombre);
    public void save(Experiencia experiencia);
    public void delete(long id);
    public boolean existsById(long id);
    public boolean existsByNombre(String nombre);
}
