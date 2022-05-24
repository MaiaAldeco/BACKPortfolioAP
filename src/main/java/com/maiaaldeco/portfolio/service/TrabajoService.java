package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Trabajo;
import com.maiaaldeco.portfolio.repository.TrabajoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrabajoService implements ITrabajoService{
    
    @Autowired
    TrabajoRepository trabajoRepository;

    @Override
    public List<Trabajo> list() {
        return trabajoRepository.findAll();
    }

    @Override
    public Optional<Trabajo> getOne(long id) {
        return trabajoRepository.findById(id);
    }

    @Override
    public List<Trabajo> getByTitulo(String titulo) {
        return trabajoRepository.findByTitulo(titulo);
    }

    @Override
    public void save(Trabajo trabajo) {
        trabajoRepository.save(trabajo);
    }

    @Override
    public void delete(long id) {
        trabajoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return trabajoRepository.existsById(id);
    }

    @Override
    public boolean existsByTitulo(String titulo) {
        return trabajoRepository.existsByTitulo(titulo);
    }

    @Override
    public List<Trabajo> findByPersonaId(long id) {
        return trabajoRepository.findByPersonaId(id);
    }

    @Override
    public void deleteByPersonaId(long personaId) {
        trabajoRepository.deleteByPersonaId(personaId);
    }
}
