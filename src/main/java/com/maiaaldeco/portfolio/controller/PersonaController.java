package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.dto.PersonaDto;
import com.maiaaldeco.portfolio.entity.Contacto;
import com.maiaaldeco.portfolio.entity.Persona;
import com.maiaaldeco.portfolio.service.IContactoService;
import com.maiaaldeco.portfolio.service.IPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaService.list();
        return new ResponseEntity<List<Persona>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") long id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        } else {
            Persona persona = personaService.getOne(id).get();
            return new ResponseEntity<Persona>(persona, HttpStatus.OK);
        }
    }
    
    @GetMapping("/contacto/{contactoId}")
    public ResponseEntity<List<Persona>> getAllPersonasByEstudioId(@PathVariable (value = "contactoId") long contactoId){
        if(!contactoService.existsById(contactoId)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        List<Persona> contactos = personaService.findByContactoId(contactoId);
        return new ResponseEntity<>(contactos,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonaDto persoDto) {
        if (StringUtils.isBlank(persoDto.getNombre())) { //common lang
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getApellido())) { //common lang
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getStack())) {
            return new ResponseEntity(new Mensaje("el stack es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getTecnologia())) {
            return new ResponseEntity(new Mensaje("la tecnologia es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = new Persona(persoDto.getNombre(), persoDto.getApellido(), persoDto.getStack(), persoDto.getTecnologia(), persoDto.getDescripcion(), persoDto.getContacto());
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("persona creado con éxito"), HttpStatus.OK);
    }
    
    @PostMapping("/create/{contactoId}")
    public ResponseEntity<?> create(@PathVariable (value = "contactoId") long contactoId, @RequestBody PersonaDto persoDto) {
        if(!contactoService.existsById(contactoId)){
            return new ResponseEntity(new Mensaje("no existe ese contacto"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(persoDto.getNombre())) { //common lang
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getApellido())) { //common lang
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getStack())) {
            return new ResponseEntity(new Mensaje("el stack es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getTecnologia())) {
            return new ResponseEntity(new Mensaje("la tecnologia es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
;
        Contacto contacto = contactoService.getOne(contactoId).get();
        Persona persona = new Persona(persoDto.getNombre(),persoDto.getApellido(),persoDto.getStack(),persoDto.getStack(),persoDto.getTecnologia(), contacto);
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("persona creado con éxito"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody PersonaDto persoDto) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(persoDto.getNombre())) { //common lang
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getApellido())) { //common lang
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getStack())) {
            return new ResponseEntity(new Mensaje("el stack es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getTecnologia())) {
            return new ResponseEntity(new Mensaje("la tecnologia es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persoDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).get();
        persona.setNombre(persoDto.getNombre());
        persona.setApellido(persoDto.getApellido());
        persona.setStack(persoDto.getStack());
        persona.setTecnologia(persoDto.getTecnologia());
        persona.setDescripcion(persoDto.getDescripcion());
        persona.setContacto(persoDto.getContacto());

        personaService.save(persona);
        return new ResponseEntity(new Mensaje("persona actualizado con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("eliminado con éxito"), HttpStatus.OK);
    }
}
