package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.dto.TrabajoDto;
import com.maiaaldeco.portfolio.entity.Trabajo;
import com.maiaaldeco.portfolio.service.ITrabajoService;
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
@RequestMapping("/trabajo")
public class TrabajoController {

    @Autowired
    ITrabajoService trabajoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Trabajo>> list() {
        List<Trabajo> list = trabajoService.list();
        return new ResponseEntity<List<Trabajo>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Trabajo> getById(@PathVariable("id") long id) {
        if (!trabajoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        } else {
            Trabajo trabajo = trabajoService.getOne(id).get();
            return new ResponseEntity<Trabajo>(trabajo, HttpStatus.OK);
        }
    }

    @GetMapping("/detailname/{titulo}")
    public ResponseEntity<Trabajo> getByNombre(@PathVariable("titulo") String titulo) {
        if (!trabajoService.existsByTitulo(titulo)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        } else {
            Trabajo trabajo = trabajoService.getByTitulo(titulo).get();
            return new ResponseEntity<Trabajo>(trabajo, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TrabajoDto trabajoDto) {
        if (StringUtils.isBlank(trabajoDto.getTitulo())) { //common lang
            return new ResponseEntity(new Mensaje("el titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(trabajoDto.getDescripcion())) { //common lang
            return new ResponseEntity(new Mensaje("la descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Trabajo trabajo = new Trabajo(trabajoDto.getTitulo(), trabajoDto.getDescripcion());
        trabajoService.save(trabajo);
        return new ResponseEntity(new Mensaje("trabajo creado con éxito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody TrabajoDto trabajoDto) {
        if (!trabajoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(trabajoDto.getTitulo())) { //common lang
            return new ResponseEntity(new Mensaje("el titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(trabajoDto.getDescripcion())) { //common lang
            return new ResponseEntity(new Mensaje("la descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Trabajo trabajo = trabajoService.getOne(id).get();
        trabajo.setTitulo(trabajoDto.getTitulo());
        trabajo.setDescripcion(trabajoDto.getDescripcion());
        trabajo.setPersona(trabajoDto.getPersona());

        trabajoService.save(trabajo);
        return new ResponseEntity(new Mensaje("trabajo actualizado con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!trabajoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        trabajoService.delete(id);
        return new ResponseEntity(new Mensaje("eliminado con éxito"), HttpStatus.OK);
    }
}
