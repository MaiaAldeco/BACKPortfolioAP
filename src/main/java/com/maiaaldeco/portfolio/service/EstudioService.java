package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Estudio;
import com.maiaaldeco.portfolio.repository.EstudioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstudioService implements IEstudioService{
    
    @Autowired
    EstudioRepository estudioRepository;

    @Override
    public List<Estudio> list() {
        return estudioRepository.findAll();
    }

    @Override
    public Optional<Estudio> getOne(long id) {
        return estudioRepository.findById(id);
    }

    @Override
    public Optional<Estudio> getByCurso(String curso) {
        return estudioRepository.findByCurso(curso);
    }

    @Override
    public void save(Estudio estudio) {
        estudioRepository.save(estudio);
    }

    @Override
    public void delete(long id) {
        estudioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return estudioRepository.existsById(id);
    }

    @Override
    public boolean existsByCurso(String curso) {
        return estudioRepository.existsByCurso(curso);
    }

    @Override
    public List<Estudio> findByPersonaId(long id) {
        return estudioRepository.findByPersonaId(id);
    }

    @Override
    public void deleteByPersonaId(long personaId) {
        
    }
}
