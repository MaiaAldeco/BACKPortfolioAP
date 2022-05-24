package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.ExperienciaDto;
import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.entity.Experiencia;
import com.maiaaldeco.portfolio.entity.Persona;
import com.maiaaldeco.portfolio.service.IExperienciaService;
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
@RequestMapping("/experiencia")
public class ExperienciaController {

    @Autowired
    IExperienciaService experienciaService;

    @Autowired
    IPersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = experienciaService.list();
        return new ResponseEntity<List<Experiencia>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") long id) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        } else {
            Experiencia estudio = experienciaService.getOne(id).get();
            return new ResponseEntity<Experiencia>(estudio, HttpStatus.OK);
        }
    }

    @GetMapping("/persona/{persona_id}")
    public ResponseEntity<List<Experiencia>> getAllPersonasByEstudioId(@PathVariable(value = "persona_id") long persona_id) {
        if (!personaService.existsById(persona_id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Experiencia> exp = experienciaService.findByPersonaId(persona_id);
        return new ResponseEntity<>(exp, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<List<Experiencia>> getByNombre(@PathVariable(value = "nombre") String nombre) {
        if (!experienciaService.existsByNombre(nombre)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Experiencia> exp = experienciaService.getByNombre(nombre);
        return new ResponseEntity<>(exp, HttpStatus.OK);
    }

    //NO RECOMENDADO
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ExperienciaDto expDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        Experiencia exp = new Experiencia(expDto.getNombre(), expDto.getPuesto(), expDto.getTareas(), expDto.getFechaInicio(), expDto.getFechaFin(), expDto.getPersona());
        experienciaService.save(exp);
        return new ResponseEntity(new Mensaje("Experiencia creado con éxito"), HttpStatus.OK);
    }

    @PostMapping("/create/{personaId}")
    public ResponseEntity<?> create(@PathVariable(value = "personaId") long personaId, @Valid @RequestBody ExperienciaDto expDto, BindingResult bindingResult) {
        if (!personaService.existsById(personaId)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
//        if (StringUtils.isBlank(expDto.getNombre())) { //common lang
//            return new ResponseEntity(new Mensaje("el lugar es obligatorio"), HttpStatus.BAD_REQUEST);
//        }
//        if (StringUtils.isBlank(expDto.getPuesto())) { //common lang
//            return new ResponseEntity(new Mensaje("el curso es obligatorio"), HttpStatus.BAD_REQUEST);
//        }
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        String inicioFecha = df.format(expDto.getFechaInicio());
//        if (StringUtils.isBlank(inicioFecha)) {
//            return new ResponseEntity(new Mensaje("la fecha es obligatoria"), HttpStatus.BAD_REQUEST);
//        }
        Persona persona = personaService.getOne(personaId).get();
        Experiencia exp = new Experiencia(expDto.getNombre(), expDto.getPuesto(), expDto.getTareas(), expDto.getFechaInicio(), expDto.getFechaFin(), persona);
        experienciaService.save(exp);
        return new ResponseEntity(new Mensaje("Experiencia creado con éxito"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody ExperienciaDto expDto, BindingResult bindingResult) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe la experiencia a la que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        if (!personaService.existsById(expDto.getPersona().getId())) {
            return new ResponseEntity(new Mensaje("No existe la persona a la que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }

        Experiencia exp = experienciaService.getOne(id).get();
        exp.setNombre(expDto.getNombre());
        exp.setPuesto(expDto.getPuesto());
        exp.setTareas(expDto.getTareas());
        exp.setFechaInicio(expDto.getFechaInicio());
        exp.setFechaFin(expDto.getFechaFin());
        exp.setPersona(expDto.getPersona());

        experienciaService.save(exp);
        return new ResponseEntity(new Mensaje("Experiencia actualizado con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        experienciaService.delete(id);
        return new ResponseEntity(new Mensaje("Eliminado con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{personaId}/persona")
    public ResponseEntity<List<Experiencia>> deleteAllEstudiosDePersonas(@PathVariable(value = "personaId") long personaId) {
        if (!personaService.existsById(personaId)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        experienciaService.deleteByPersonaId(personaId);
        return new ResponseEntity(new Mensaje("Personas eliminadas con éxito"), HttpStatus.OK);
    }
}
