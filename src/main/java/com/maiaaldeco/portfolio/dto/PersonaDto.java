package com.maiaaldeco.portfolio.dto;

import com.maiaaldeco.portfolio.entity.Contacto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonaDto {
    @NotBlank(message="El nombre es obligatorio")
    private String nombre;
    @NotBlank(message="El apellido es obligatorio")
    private String apellido;
    @NotBlank(message="El stack es obligatorio")
    private String stack;
    @NotBlank(message="La tecnología es obligatoria")
    private String tecnologia;
    @NotBlank(message="La descripción es obligatoria")
    private String descripcion;
    private Contacto contacto;
}
