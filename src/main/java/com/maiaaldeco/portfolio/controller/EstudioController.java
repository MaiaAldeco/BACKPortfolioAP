package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.EstudioDto;
import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.entity.Estudio;
import com.maiaaldeco.portfolio.entity.Persona;
import com.maiaaldeco.portfolio.service.IEstudioService;
import com.maiaaldeco.portfolio.service.IPersonaService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    @ApiOperation("Muestra una lista de estudios")
    @GetMapping("/lista")
    public ResponseEntity<List<Estudio>> list() {
        List<Estudio> list = estudioService.list();
        return new ResponseEntity<List<Estudio>>(list, HttpStatus.OK);
    }

    @ApiOperation("Muestra el detalle de un estudio por id")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Estudio> getById(@PathVariable("id") long id) {
        if (!estudioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        } else {
            Estudio estudio = estudioService.getOne(id).get();
            return new ResponseEntity<Estudio>(estudio, HttpStatus.OK);
        }
    }

    @ApiOperation("Muestra un estudio por id de la persona")
    @GetMapping("/persona/{persona_id}")
    public ResponseEntity<List<Estudio>> getAllPersonasByEstudioId(@PathVariable(value = "persona_id") long persona_id) {
        if (!personaService.existsById(persona_id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Estudio> estudios = estudioService.findByPersonaId(persona_id);
        return new ResponseEntity<>(estudios, HttpStatus.OK);
    }

    @ApiOperation("Muestra el detalle de un estudio por nombre del curso")
    @GetMapping("/detailname/{curso}")
    public ResponseEntity<List<Estudio>> getByNombre(@PathVariable(value = "curso") String curso) {
        if (!estudioService.existsByCurso(curso)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Estudio> estudio2 = estudioService.getByCurso(curso);
        return new ResponseEntity<>(estudio2, HttpStatus.OK);
    }

    //SE ASIGNA A LA PERSONA DUEÑA DEL PORTFOLIO
    @ApiOperation("Crea un nuevo estudio")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody EstudioDto estudioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        if (personaService.list().isEmpty()) {
            return new ResponseEntity(new Mensaje("No existe una persona para crear los datos"), HttpStatus.NOT_FOUND);
        }
        Persona persona = personaService.list().stream().filter(e -> e != null).findFirst().orElse(null);
        Estudio estudio = new Estudio(estudioDto.getLugar(), estudioDto.getCurso(), estudioDto.getFechaInicio(), estudioDto.getFechaFin(), persona);
        estudioService.save(estudio);
        return new ResponseEntity(new Mensaje("Estudio creado con éxito"), HttpStatus.OK);
    }

    @ApiOperation("Crea un nuevo estudio por id de la persona")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{personaId}")
    public ResponseEntity<?> create(@PathVariable(value = "personaId") long personaId, @Valid @RequestBody EstudioDto estudioDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        if (!personaService.existsById(personaId)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        Persona persona = personaService.getOne(personaId).get();
        Estudio estudio = new Estudio(estudioDto.getCurso(), estudioDto.getLugar(), estudioDto.getFechaInicio(), estudioDto.getFechaFin(), persona);
        estudioService.save(estudio);
        return new ResponseEntity(new Mensaje("Estudio creado con éxito"), HttpStatus.CREATED);
    }

    @ApiOperation("Actualizar un estudio por id")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody EstudioDto estudioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        if (!estudioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el estudio al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        
        if (personaService.list().isEmpty()) {
            return new ResponseEntity(new Mensaje("No existe una persona para asignar este estudio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.list().stream().filter(e -> e != null).findFirst().orElse(null);

        Estudio estudio = estudioService.getOne(id).get();
        estudio.setCurso(estudioDto.getCurso());
        estudio.setLugar(estudioDto.getLugar());
        estudio.setFechaInicio(estudioDto.getFechaInicio());
        estudio.setFechaFin(estudioDto.getFechaFin());
        estudio.setPersona(persona);

        estudioService.save(estudio);
        return new ResponseEntity(new Mensaje("Estudio actualizado con éxito"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un estudio por id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!estudioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        estudioService.delete(id);
        return new ResponseEntity(new Mensaje("Eliminado con éxito"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un estudio por id de persona")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{personaId}/persona")
    public ResponseEntity<List<Estudio>> deleteAllEstudiosDePersonas(@PathVariable(value = "personaId") long personaId) {
        if (!personaService.existsById(personaId)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        System.out.println("ID PERSONA " + personaId);
        estudioService.deleteByPersonaId(personaId);
        return new ResponseEntity(new Mensaje("Persona eliminada de los trabajos con éxito"), HttpStatus.OK);
    }
}
