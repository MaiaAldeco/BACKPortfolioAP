package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Estudio;
import java.util.List;
import java.util.Optional;

public interface IEstudioService {
    
    public List<Estudio> list();
    public Optional<Estudio> getOne(long id);
    public Optional<Estudio> getByCurso(String curso);
    public void save(Estudio estudio);
    public void delete(long id);
    public boolean existsById(long id);
    public boolean existsByCurso(String curso);
    List<Estudio>findByPersonaId(long id);
    void deleteByPersonaId(long personaId);
}
