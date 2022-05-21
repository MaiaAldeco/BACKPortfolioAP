package com.maiaaldeco.portfolio.dto;

import com.maiaaldeco.portfolio.entity.Contacto;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class PersonaDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String stack;
    @NotBlank
    private String tecnologia;
    @NotBlank
    private String descripcion;
    private Contacto contacto;

    public PersonaDto(String nombre, String apellido, String stack, String tecnologia, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.stack = stack;
        this.tecnologia = tecnologia;
        this.descripcion = descripcion;
    }
    
    
}
