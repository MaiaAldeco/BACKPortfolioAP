package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.ContactoDto;
import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.entity.Contacto;
import com.maiaaldeco.portfolio.service.IContactoService;
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
@RequestMapping("/contacto")
@CrossOrigin(origins = "*")
public class ContactoController {

    @Autowired
    IContactoService contactoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Contacto>> list() {
        List<Contacto> list = contactoService.list();
        return new ResponseEntity<List<Contacto>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Contacto> getById(@PathVariable("id") long id) {
        if (!contactoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        } else {
            Contacto producto = contactoService.getOne(id).get();
            return new ResponseEntity<Contacto>(producto, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ContactoDto contactoDto) {
        if (StringUtils.isBlank(contactoDto.getLocalidad())) {
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(contactoDto.getTelefono())) {
            return new ResponseEntity(new Mensaje("el telefono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(contactoDto.getEmail())) {
            return new ResponseEntity(new Mensaje("el telefono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Contacto contacto = new Contacto(contactoDto.getLocalidad(), contactoDto.getTelefono(), contactoDto.getEmail());
        contactoService.save(contacto);
        return new ResponseEntity(new Mensaje("Contacto creado con éxito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody ContactoDto contactoDto) {
        if (!contactoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El contacto no existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(contactoDto.getLocalidad())) {
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(contactoDto.getTelefono())) {
            return new ResponseEntity(new Mensaje("el telefono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(contactoDto.getEmail())) {
            return new ResponseEntity(new Mensaje("el telefono es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Contacto contacto = contactoService.getOne(id).get();
        contacto.setLocalidad(contactoDto.getLocalidad());
        contacto.setTelefono(contactoDto.getTelefono());
        contacto.setEmail(contactoDto.getEmail());
        contactoService.save(contacto);
        return new ResponseEntity(new Mensaje("Contacto actualizado con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!contactoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        contactoService.delete(id);
        return new ResponseEntity(new Mensaje("eliminado con éxito"), HttpStatus.OK);
    }
}
