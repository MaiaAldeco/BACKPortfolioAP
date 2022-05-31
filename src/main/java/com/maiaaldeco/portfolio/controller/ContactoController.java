package com.maiaaldeco.portfolio.controller;

import com.maiaaldeco.portfolio.dto.ContactoDto;
import com.maiaaldeco.portfolio.dto.Mensaje;
import com.maiaaldeco.portfolio.entity.Contacto;
import com.maiaaldeco.portfolio.entity.Persona;
import com.maiaaldeco.portfolio.service.IContactoService;
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
@RequestMapping("/contacto")
@CrossOrigin(origins = "*")
public class ContactoController {

    @Autowired
    IContactoService contactoService;
    
    @ApiOperation("Muestra una lista de contactos")
    @GetMapping("/lista")
    public ResponseEntity<List<Contacto>> list() {
        List<Contacto> list = contactoService.list();
        return new ResponseEntity<List<Contacto>>(list, HttpStatus.OK);
    }

    @ApiOperation("Muestra el detalle de un contacto por id")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Contacto> getById(@PathVariable("id") long id) {
        if (!contactoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        } else {
            Contacto contacto = contactoService.getOne(id).get();
            return new ResponseEntity<Contacto>(contacto, HttpStatus.OK);
        }
    }

    @ApiOperation("Muestra una lista de contactos por email")
    @GetMapping("/detailemail/{email}")
    public ResponseEntity<List<Contacto>> getByNombre(@PathVariable(value = "email") String email) {
        if (!contactoService.existsByEmail(email)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        List<Contacto> contacto = contactoService.findByEmail(email);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }
    
    @ApiOperation("Crea un nuevo contacto")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ContactoDto contactoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        if(contactoService.list() != null){
            return new ResponseEntity(new Mensaje("Ya existe un contacto para este portfolio"), HttpStatus.BAD_REQUEST);
        }
        Contacto contacto = new Contacto(contactoDto.getLocalidad(), contactoDto.getTelefono(), contactoDto.getEmail());
        contactoService.save(contacto);
        return new ResponseEntity(new Mensaje("Contacto creado con éxito"), HttpStatus.OK);
    }
    
    @ApiOperation("Actualiza un producto")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody ContactoDto contactoDto, BindingResult bindingResult) {
        if (!contactoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                return new ResponseEntity(new Mensaje(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }

        Contacto contacto = contactoService.getOne(id).get();
        contacto.setLocalidad(contactoDto.getLocalidad());
        contacto.setTelefono(contactoDto.getTelefono());
        contacto.setEmail(contactoDto.getEmail());
        contactoService.save(contacto);
        return new ResponseEntity(new Mensaje("Contacto actualizado con éxito"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un contacto por id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (!contactoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el dato al que intenta acceder"), HttpStatus.NOT_FOUND);
        }
        Persona persona = contactoService.getOne(id).get().getPersona();
        if((Long)persona.getId()!=null){
            return new ResponseEntity(new Mensaje("No es posible eliminar el contacto de " + persona.getApellido()), HttpStatus.BAD_REQUEST);
        }
        
        contactoService.delete(id);
        return new ResponseEntity(new Mensaje("eliminado con éxito"), HttpStatus.OK);
    }
}
