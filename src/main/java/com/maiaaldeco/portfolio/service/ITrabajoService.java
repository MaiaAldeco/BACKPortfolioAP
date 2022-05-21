package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Trabajo;
import java.util.List;
import java.util.Optional;


public interface ITrabajoService {

    public List<Trabajo> list();
    public Optional<Trabajo> getOne(long id);
    public Optional<Trabajo> getByTitulo(String titulo);
    public void save(Trabajo trabajo);
    public void delete(long id);
    public boolean existsById(long id);
    public boolean existsByTitulo(String titulo);
}
