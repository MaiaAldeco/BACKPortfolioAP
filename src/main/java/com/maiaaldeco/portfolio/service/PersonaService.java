package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Persona;
import com.maiaaldeco.portfolio.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonaService implements IPersonaService{
    
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<Persona> list() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> getOne(long id) {
        return personaRepository.findById(id);
    }

    @Override
    public Optional<Persona> getByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }

    @Override
    public void save(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public void delete(long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return personaRepository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return personaRepository.existsByNombre(nombre);
    }

    @Override
    public List<Persona> findByContactoId(long id) {
        return personaRepository.findByContactoId(id);
    }

    @Override
    public void deleteByContactoId(long personaId) {
        personaRepository.deleteByContactoId(personaId);
    }

    @Override
    public boolean existsByApellido(String apellido) {
        return personaRepository.existsByApellido(apellido);
    }

    @Override
    public List<Persona> findByApellido(String apellido) {
        return personaRepository.findByApellido(apellido);
    }

}
