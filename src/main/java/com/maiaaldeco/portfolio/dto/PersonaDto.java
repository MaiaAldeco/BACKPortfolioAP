package com.maiaaldeco.portfolio.dto;

import com.maiaaldeco.portfolio.entity.Contacto;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

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
}
