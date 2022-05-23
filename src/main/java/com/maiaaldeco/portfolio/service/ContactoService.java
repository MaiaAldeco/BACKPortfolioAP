package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Contacto;
import com.maiaaldeco.portfolio.repository.ContactoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactoService implements IContactoService{
    
    @Autowired
    ContactoRepository contactoRepository;

    @Override
    public List<Contacto> list() {
        return contactoRepository.findAll();
    }

    @Override
    public Optional<Contacto> getOne(long id) {
        return contactoRepository.findById(id);
    }

    @Override
    public void save(Contacto contacto) {
        contactoRepository.save(contacto);
    }

    @Override
    public void delete(long id) {
        contactoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return contactoRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return contactoRepository.existsByEmail(email);
    }
}
