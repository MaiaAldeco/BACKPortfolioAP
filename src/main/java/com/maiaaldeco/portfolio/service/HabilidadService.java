package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Habilidad;
import com.maiaaldeco.portfolio.repository.HabilidadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HabilidadService implements IHabilidadService{
    
    @Autowired
    HabilidadRepository habilidadRepository;

    @Override
    public List<Habilidad> list() {
        return habilidadRepository.findAll();
    }

    @Override
    public Optional<Habilidad> getOne(long id) {
        return habilidadRepository.findById(id);
    }

    @Override
    public Optional<Habilidad> getByHabilidad(String habilidad) {
        return habilidadRepository.findByHabilidad(habilidad);
    }

    @Override
    public void save(Habilidad habilidad) {
        habilidadRepository.save(habilidad);
    }

    @Override
    public void delete(long id) {
        habilidadRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return habilidadRepository.existsById(id);
    }

    @Override
    public boolean existsByHabilidad(String habilidad) {
        return habilidadRepository.existsByHabilidad(habilidad);
    }
}
