package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.dto.TrabajoDto;
import com.maiaaldeco.portfolio.entity.Persona;
import com.maiaaldeco.portfolio.entity.Trabajo;
import com.maiaaldeco.portfolio.service.IPersonaService;
import com.maiaaldeco.portfolio.service.ITrabajoService;
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
@RequestMapping("/trabajo")
public class TrabajoController {

    @Autowired
    ITrabajoService trabajoService;

    @Autowired
    IPersonaService personaService;

    @ApiOperation("Muestra una lista de proyectos")
    @GetMapping("/lista")
    public ResponseEntity<List<Trabajo>> list() {
        List<Trabajo> list = trabajoService.list();
        return new ResponseEntity<List<Trabajo>>(list, HttpStatus.OK);
    }

    @ApiOperation("Muestra un proyecto por id")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Trabajo> getById(@PathVariable("id") long id) {
        if (!trabajoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        } else {
            Trabajo trabajo = trabajoService.getOne(id).get();
            return new ResponseEntity<Trabajo>(trabajo, HttpStatus.OK);
        }
    }

    @ApiOperation("Muestra una lista de proyectos por nombre")
    @GetMapping("/detailname/{titulo}")
    public ResponseEntity<List<Trabajo>> getByNombre(@PathVariable(value = "titulo") String titulo) {
        if (!trabajoService.existsByTitulo(titulo)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Trabajo> skill = trabajoService.getByTitulo(titulo);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @ApiOperation("Muestra una lista de proyectos por id persona")
    @GetMapping("/persona/{persona_id}")
    public ResponseEntity<List<Trabajo>> getAllPersonasByEstudioId(@PathVariable(value = "persona_id") long persona_id) {
        if (!personaService.existsById(persona_id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Trabajo> exp = trabajoService.findByPersonaId(persona_id);
        return new ResponseEntity<>(exp, HttpStatus.OK);
    }

    //CREA UN TRABAJO ASIGNANDOLO AUTOMATICAMENTE A LA PERSONA DUEÑA DEL PORTFOLIO
    @ApiOperation("Crea un proyecto")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody TrabajoDto trabajoDto, BindingResult bindingResult) {
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
        Trabajo trabajo = new Trabajo(trabajoDto.getTitulo(), trabajoDto.getDescripcion(), persona);
        trabajoService.save(trabajo);
        return new ResponseEntity(new Mensaje("trabajo creado con éxito"), HttpStatus.OK);
    }

    @ApiOperation("Crea un trabajo por id persona")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{personaId}")
    public ResponseEntity<?> create(@PathVariable(value = "personaId") long personaId, @Valid @RequestBody TrabajoDto trabajoDto, BindingResult bindingResult) {

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
        Trabajo trabajo = new Trabajo(trabajoDto.getTitulo(), trabajoDto.getDescripcion(), persona);
        trabajoService.save(trabajo);
        return new ResponseEntity(new Mensaje("Estudio creado con éxito"), HttpStatus.CREATED);
    }

    @ApiOperation("Actualiza una proyecto por id")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody TrabajoDto trabajoDto, BindingResult bindingResult) {
        if (!trabajoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el trabajo al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        if (personaService.list().isEmpty()) {
            return new ResponseEntity(new Mensaje("No existe una persona para asignar este estudio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.list().stream().filter(e -> e != null).findFirst().orElse(null);
        Trabajo trabajo = trabajoService.getOne(id).get();
        trabajo.setTitulo(trabajoDto.getTitulo());
        trabajo.setDescripcion(trabajoDto.getDescripcion());
        trabajo.setPersona(persona);

        trabajoService.save(trabajo);
        return new ResponseEntity(new Mensaje("Trabajo actualizado con éxito"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un proyecto por id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!trabajoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        trabajoService.delete(id);
        return new ResponseEntity(new Mensaje("Trabajo eliminado con éxito"), HttpStatus.OK);
    }

    @ApiOperation("Elimina los proyectos por id persona")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{personaId}/trabajo")
    public ResponseEntity<List<Trabajo>> deleteAllEstudiosDePersonas(@PathVariable(value = "personaId") long personaId) {
        if (!personaService.existsById(personaId)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        trabajoService.deleteByPersonaId(personaId);
        return new ResponseEntity(new Mensaje("Personas eliminadas del trabajo con éxito"), HttpStatus.OK);
    }
}
