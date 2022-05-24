package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Trabajo;
import java.util.List;
import java.util.Optional;


public interface ITrabajoService {

    public List<Trabajo> list();
    public Optional<Trabajo> getOne(long id);
    public List<Trabajo> getByTitulo(String titulo);
    public void save(Trabajo trabajo);
    public void delete(long id);
    public boolean existsById(long id);
    public boolean existsByTitulo(String titulo);
    List<Trabajo>findByPersonaId(long id);
    void deleteByPersonaId(long personaId);
}
