package com.maiaaldeco.portfolio.service;

import com.maiaaldeco.portfolio.entity.Contacto;
import java.util.List;
import java.util.Optional;


public interface IContactoService {
    
    public List<Contacto> list();
    public Optional<Contacto> getOne(long id);
    public void save(Contacto contacto);
    public void delete(long id);
    public boolean existsById(long id);
    boolean existsByEmail(String email);
    List<Contacto>findByEmail(String email);
}
