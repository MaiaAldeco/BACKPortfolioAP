package com.maiaaldeco.portfolio.security.service;

import com.maiaaldeco.portfolio.security.entity.Usuario;
import com.maiaaldeco.portfolio.security.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;
    
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    
    public void save(Usuario usu) {
        usuarioRepository.save(usu);
    }
    
}
