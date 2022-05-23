package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.ContactoDto;
import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.entity.Contacto;
import com.maiaaldeco.portfolio.service.IContactoService;
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
            Contacto contacto = contactoService.getOne(id).get();
            return new ResponseEntity<Contacto>(contacto, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ContactoDto contactoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
           List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
                }
        }
//        if (contactoService.existsByEmail(contactoDto.getEmail())) {
//            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
//        }
//        if (StringUtils.isBlank(contactoDto.getLocalidad())) {
//            return new ResponseEntity(new Mensaje("La localidad es obligatoria"), HttpStatus.BAD_REQUEST);
//        }
//        if (contactoDto.getTelefono() == null || contactoDto.getTelefono().length() < 10) {
//            return new ResponseEntity(new Mensaje("Ingresa un número de teléfono válido"), HttpStatus.BAD_REQUEST);
//        }
//        if (StringUtils.isBlank(contactoDto.getEmail())) {
//            return new ResponseEntity(new Mensaje("El email no es válido"), HttpStatus.BAD_REQUEST);
//        }
        Contacto contacto = new Contacto(contactoDto.getLocalidad(), contactoDto.getTelefono(), contactoDto.getEmail());
        contactoService.save(contacto);
        return new ResponseEntity(new Mensaje("Contacto creado con éxito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody ContactoDto contactoDto, BindingResult bindingResult) {
        if (!contactoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El contacto no existe"), HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
                }
        }
//        if (StringUtils.isBlank(contactoDto.getLocalidad())) {
//            return new ResponseEntity(new Mensaje("La localidad es obligatorio"), HttpStatus.BAD_REQUEST);
//        }
//        if (contactoDto.getTelefono() == null || contactoDto.getTelefono().length() < 10) {
//            return new ResponseEntity(new Mensaje("Ingresa un número de teléfono válido"), HttpStatus.BAD_REQUEST);
//        }
//        if (StringUtils.isBlank(contactoDto.getEmail())) {
//            return new ResponseEntity(new Mensaje("El email no es válido"), HttpStatus.BAD_REQUEST);
//        }

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
