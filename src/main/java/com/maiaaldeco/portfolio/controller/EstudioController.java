package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.EstudioDto;
import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.entity.Estudio;
import com.maiaaldeco.portfolio.entity.Persona;
import com.maiaaldeco.portfolio.service.IEstudioService;
import com.maiaaldeco.portfolio.service.IPersonaService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/estudio")
public class EstudioController {

    @Autowired
    IEstudioService estudioService;
    
    @Autowired
    IPersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Estudio>> list() {
        List<Estudio> list = estudioService.list();
        return new ResponseEntity<List<Estudio>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Estudio> getById(@PathVariable("id") long id) {
        if (!estudioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        } else {
            Estudio estudio = estudioService.getOne(id).get();
            return new ResponseEntity<Estudio>(estudio, HttpStatus.OK);
        }
    }

    @GetMapping("/detailname/{curso}")
    public ResponseEntity<Estudio> getByNombre(@PathVariable("curso") String curso) {
        if (!estudioService.existsByCurso(curso)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        } else {
            Estudio estudio = estudioService.getByCurso(curso).get();
            return new ResponseEntity<Estudio>(estudio, HttpStatus.OK);
        }
    }
    
    @GetMapping("/persona/{persona_id}")
    public ResponseEntity<List<Estudio>> getAllPersonasByEstudioId(@PathVariable (value = "persona_id") long persona_id){
        if(!personaService.existsById(persona_id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        List<Estudio> estudios = estudioService.findByPersonaId(persona_id);
        return new ResponseEntity<>(estudios,HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody EstudioDto estudioDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
                }
        }
//        if (StringUtils.isBlank(estudioDto.getLugar())) { //common lang
//            return new ResponseEntity(new Mensaje("el lugar es obligatorio"), HttpStatus.BAD_REQUEST);
//        }
//        if (StringUtils.isBlank(estudioDto.getCurso())) { //common lang
//            return new ResponseEntity(new Mensaje("el curso es obligatorio"), HttpStatus.BAD_REQUEST);
//        }
        Estudio estudio = new Estudio(estudioDto.getLugar(),estudioDto.getCurso(),estudioDto.getFechaInicio(),estudioDto.getFechaFin(), estudioDto.getPersona());
        estudioService.save(estudio);
        return new ResponseEntity(new Mensaje("Estudio creado con éxito"), HttpStatus.OK);
    }

    @PostMapping("/create/{personaId}")
    public ResponseEntity<?> create(@PathVariable(value = "personaId") long personaId, @RequestBody EstudioDto estudioDto) {
        if (StringUtils.isBlank(estudioDto.getLugar())) { //common lang
            return new ResponseEntity(new Mensaje("el lugar es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(estudioDto.getCurso())) { //common lang
            return new ResponseEntity(new Mensaje("el curso es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String inicioFecha = df.format(estudioDto.getFechaInicio());
        if (StringUtils.isBlank(inicioFecha)) {
            return new ResponseEntity(new Mensaje("la fecha es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(!personaService.existsById(personaId)){
            return new ResponseEntity(new Mensaje("no existe esa persona"), HttpStatus.NOT_FOUND);
        }
        Persona  persona =  Persona.class.cast(personaService.getOne(personaId));
        Estudio estudio = new Estudio(estudioDto.getCurso(), estudioDto.getLugar(), estudioDto.getFechaInicio(), estudioDto.getFechaFin(), persona);
        estudioService.save(estudio);
        return new ResponseEntity(new Mensaje("estudio creado con éxito"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody EstudioDto estudioDto) {
        if (!estudioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(estudioDto.getLugar())) { //common lang
            return new ResponseEntity(new Mensaje("el lugar es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(estudioDto.getCurso())) { //common lang
            return new ResponseEntity(new Mensaje("el curso es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String inicioFecha = df.format(estudioDto.getFechaInicio());
        if (StringUtils.isBlank(inicioFecha)) {
            return new ResponseEntity(new Mensaje("la fecha es obligatorio"), HttpStatus.BAD_REQUEST);
        }
//        if (StringUtils.isBlank(estudioDto.getPersona().toString())) {
//            return new ResponseEntity(new Mensaje("debes especificar de quien es este estudio"), HttpStatus.BAD_REQUEST);
//        }

        Estudio estudio = estudioService.getOne(id).get();
        estudio.setCurso(estudioDto.getCurso());
        estudio.setLugar(estudioDto.getLugar());
        estudio.setFechaInicio(estudioDto.getFechaInicio());
        estudio.setFechaFin(estudioDto.getFechaFin());
        estudio.setPersona(estudioDto.getPersona());

        estudioService.save(estudio);
        return new ResponseEntity(new Mensaje("estudio actualizado con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!estudioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        estudioService.delete(id);
        return new ResponseEntity(new Mensaje("eliminado con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/estudio/{personaId}")
    public ResponseEntity<List<Estudio>>deleteAllEstudiosDePersonas(@PathVariable(value = "personaId") long personaId){
        if(!personaService.existsById(personaId)){
            return new ResponseEntity(new Mensaje("id no encontrado"), HttpStatus.NOT_FOUND);
        }
        estudioService.deleteByPersonaId(personaId);
        return new ResponseEntity(new Mensaje("Personas eliminadas con éxito"),HttpStatus.NO_CONTENT);
    }
}
