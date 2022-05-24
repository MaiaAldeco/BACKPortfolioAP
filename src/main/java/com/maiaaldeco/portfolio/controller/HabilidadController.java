package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.HabilidadDto;
import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.entity.Habilidad;
import com.maiaaldeco.portfolio.entity.Persona;
import com.maiaaldeco.portfolio.service.IHabilidadService;
import com.maiaaldeco.portfolio.service.IPersonaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/skill")
public class HabilidadController {

    @Autowired
    IHabilidadService habilidadService;

    @Autowired
    IPersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list() {
        List<Habilidad> list = habilidadService.list();
        return new ResponseEntity<List<Habilidad>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") long id) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        } else {
            Habilidad habilidad = habilidadService.getOne(id).get();
            return new ResponseEntity<Habilidad>(habilidad, HttpStatus.OK);
        }
    }

    @GetMapping("/detailname/{habilidad}")
    public ResponseEntity<List<Habilidad>> getByNombre(@PathVariable(value = "habilidad") String habilidad) {
        if (!habilidadService.existsByHabilidad(habilidad)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Habilidad> skill = habilidadService.getByHabilidad(habilidad);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @GetMapping("/persona/{persona_id}")
    public ResponseEntity<List<Habilidad>> getAllPersonasByEstudioId(@PathVariable(value = "persona_id") long persona_id) {
        if (!personaService.existsById(persona_id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Habilidad> skill = habilidadService.findByPersonaId(persona_id);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    //NO RECOMENDADO
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody HabilidadDto habiDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        if (habiDto.getPorcentaje() == 0) {
            return new ResponseEntity(new Mensaje("Porcentaje no puede ser cero"), HttpStatus.NOT_FOUND);
        }
        if (!personaService.existsById(habiDto.getPersona().getId())) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
//        if (StringUtils.isBlank(habiDto.getHabilidadNombre())) { //common lang
//            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
//        }
//        if (StringUtils.isBlank(String.valueOf(habiDto.getPorcentaje()))) { //common lang
//            return new ResponseEntity(new Mensaje("el porcentaje es obligatorio"), HttpStatus.BAD_REQUEST);
//        }
        Habilidad habilidad = new Habilidad(habiDto.getHabilidadNombre(), habiDto.getPorcentaje(), habiDto.getPersona());
        habilidadService.save(habilidad);
        return new ResponseEntity(new Mensaje("Habilidad creada con éxito"), HttpStatus.OK);
    }

    @PostMapping("/create/{personaId}")
    public ResponseEntity<?> create(@PathVariable(value = "personaId") long personaId, @Valid @RequestBody HabilidadDto habiDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }

        if (habiDto.getPorcentaje() == 0) {
            return new ResponseEntity(new Mensaje("Porcentaje no puede ser cero"), HttpStatus.NOT_FOUND);
        }

        if (!personaService.existsById(personaId)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        Persona persona = personaService.getOne(personaId).get();
        Habilidad skill = new Habilidad(habiDto.getHabilidadNombre(), habiDto.getPorcentaje(), persona);
        habilidadService.save(skill);
        return new ResponseEntity(new Mensaje("Experiencia creado con éxito"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody HabilidadDto habiDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe la habilidad a la que intenta acceder"), HttpStatus.NOT_FOUND);
        }

        if (!personaService.existsById(habiDto.getPersona().getId())) {
            return new ResponseEntity(new Mensaje("No existe la persona a la que intenta acceder"), HttpStatus.NOT_FOUND);
        }
//        if (StringUtils.isBlank(habiDto.getHabilidadNombre())) { //common lang
//            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
//        }
//        if (StringUtils.isBlank(String.valueOf(habiDto.getPorcentaje()))) { //common lang
//            return new ResponseEntity(new Mensaje("el porcentaje es obligatorio"), HttpStatus.BAD_REQUEST);
//        }

        Habilidad habi = habilidadService.getOne(id).get();
        habi.setHabilidad(habiDto.getHabilidadNombre());
        habi.setPorcentaje(habiDto.getPorcentaje());
        habi.setPersona(habiDto.getPersona());

        habilidadService.save(habi);
        return new ResponseEntity(new Mensaje("habilidad actualizado con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        habilidadService.delete(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{personaId}/skill")
    public ResponseEntity<List<Habilidad>> deleteAllEstudiosDePersonas(@PathVariable(value = "personaId") long personaId) {
        if (!personaService.existsById(personaId)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        habilidadService.deleteByPersonaId(personaId);
        return new ResponseEntity(new Mensaje("Persona eliminada con éxito"), HttpStatus.OK);
    }
}
