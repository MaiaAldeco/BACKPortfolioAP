package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.dto.PersonaDto;
import com.maiaaldeco.portfolio.entity.Contacto;
import com.maiaaldeco.portfolio.entity.Persona;
import com.maiaaldeco.portfolio.security.entity.Usuario;
import com.maiaaldeco.portfolio.security.service.UsuarioService;
import com.maiaaldeco.portfolio.service.IContactoService;
import com.maiaaldeco.portfolio.service.IPersonaService;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    IPersonaService personaService;

    @Autowired
    IContactoService contactoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaService.list();
        return new ResponseEntity<List<Persona>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") long id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        } else {
            Persona persona = personaService.getOne(id).get();
            return new ResponseEntity<Persona>(persona, HttpStatus.OK);
        }
    }

    @GetMapping("/contacto/{contactoId}")
    public ResponseEntity<List<Persona>> getAllPersonasByEstudioId(@PathVariable(value = "contactoId") long contactoId) {
        if (!contactoService.existsById(contactoId)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Persona> contactos = personaService.findByContactoId(contactoId);
        return new ResponseEntity<>(contactos, HttpStatus.OK);
    }

    @GetMapping("/detailname/{apellido}")
    public ResponseEntity<List<Persona>> getByNombre(@PathVariable(value = "apellido") String apellido) {
        if (!personaService.existsByApellido(apellido)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Persona> persona = personaService.findByApellido(apellido);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    //NO RECOMENDADO
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PersonaDto persoDto, @CurrentSecurityContext(expression="authentication?.name")String username, BindingResult bindingResult) {
        System.out.println("username " + username);
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        if (!contactoService.existsById(persoDto.getContacto().getId())) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.BAD_REQUEST);
        }

//        if (!usuarioService.existsById(persoDto.getUsuario().getId())) {
//            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.BAD_REQUEST);
//        }
        
        for (Persona lista : personaService.findByContactoId(persoDto.getContacto().getId())) {
            if (lista.getContacto().getId() == persoDto.getContacto().getId()) {
                return new ResponseEntity(new Mensaje("El contacto no esta libre"), HttpStatus.BAD_REQUEST);
            }
        }
        Usuario usuario = usuarioService.getByNombreUsuario(username).get();
        System.out.println("USUARIO "+ usuario.getId() + " " + usuario.getNombre() + " " + usuario.getEmail());
//        for (Persona lista : personaService.findByUsuarioId(persoDto.getUsuario().getId())) {
//            if (lista.getUsuario().getId() == persoDto.getUsuario().getId()) {
//                return new ResponseEntity(new Mensaje("El usuario no esta libre"), HttpStatus.BAD_REQUEST);
//            }
//        }
        Persona persona = new Persona(persoDto.getNombre(), persoDto.getApellido(), persoDto.getStack(), persoDto.getTecnologia(), persoDto.getDescripcion(), usuario, persoDto.getContacto());
        System.out.println("PERSONA " + persona.toString());
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona creado con éxito"), HttpStatus.OK);
    }

    @PostMapping("/create/{contactoId}")
    public ResponseEntity<?> create(@PathVariable(value = "contactoId") long contactoId, @Valid @RequestBody PersonaDto persoDto,@CurrentSecurityContext(expression="authentication?.name")String username, BindingResult bindingResult) {
        if (!contactoService.existsById(contactoId)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        for (Persona lista : personaService.findByContactoId(contactoId)) {
            if (lista.getContacto().getId() == contactoId) {
                return new ResponseEntity(new Mensaje("El contacto no esta libre"), HttpStatus.BAD_REQUEST);
            }
        }
        Usuario usuario = usuarioService.getByNombreUsuario(username).get();
        Contacto contacto = contactoService.getOne(contactoId).get();
        Persona persona = new Persona(persoDto.getNombre(), persoDto.getApellido(), persoDto.getStack(), persoDto.getStack(), persoDto.getTecnologia(),usuario, contacto);
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona creado con éxito"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody PersonaDto persoDto, @CurrentSecurityContext(expression="authentication?.name")String username, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe la persona a al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        if (!contactoService.existsById(persoDto.getContacto().getId())) {
            return new ResponseEntity(new Mensaje("No existe el contacto al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        if (!(personaService.getOne(id).get().getContacto().getId() == persoDto.getContacto().getId())) {
            for (Persona lista : personaService.findByContactoId(persoDto.getContacto().getId())) {
                if (lista.getContacto().getId() == persoDto.getContacto().getId()) {
                    return new ResponseEntity(new Mensaje("El contacto no esta libre"), HttpStatus.BAD_REQUEST);
                }
            }
        }

        Persona persona = personaService.getOne(id).get();
        persona.setNombre(persoDto.getNombre());
        persona.setApellido(persoDto.getApellido());
        persona.setStack(persoDto.getStack());
        persona.setTecnologia(persoDto.getTecnologia());
        persona.setDescripcion(persoDto.getDescripcion());
        persona.setUsuario(usuarioService.getByNombreUsuario(username).get());
        persona.setContacto(persoDto.getContacto());

        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizado con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("Eliminado con éxito"), HttpStatus.OK);
    }
}
