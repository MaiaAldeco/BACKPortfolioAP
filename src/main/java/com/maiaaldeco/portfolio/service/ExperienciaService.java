package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Experiencia;
import com.maiaaldeco.portfolio.repository.ExperienciaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExperienciaService implements IExperienciaService{
    
    @Autowired
    ExperienciaRepository experienciaRepository;

    @Override
    public List<Experiencia> list() {
        return experienciaRepository.findAll();
    }

    @Override
    public Optional<Experiencia> getOne(long id) {
         return experienciaRepository.findById(id);
    }

    @Override
    public List<Experiencia> getByNombre(String nombre) {
        return experienciaRepository.findByNombre(nombre);
    }

    @Override
    public void save(Experiencia experiencia) {
        experienciaRepository.save(experiencia);
    }

    @Override
    public void delete(long id) {
        experienciaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return experienciaRepository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return experienciaRepository.existsByNombre(nombre);
    }

    @Override
    public List<Experiencia> findByPersonaId(long id) {
        return experienciaRepository.findByPersonaId(id);
    }

    @Override
    public void deleteByPersonaId(long personaId) {
        experienciaRepository.deleteByPersonaId(personaId);
    }
}
