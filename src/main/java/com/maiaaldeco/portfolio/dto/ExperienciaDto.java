package com.maiaaldeco.portfolio.dto;

import com.maiaaldeco.portfolio.entity.Persona;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExperienciaDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El puesto de trabajo es obligatorio")
    private String puesto;
    private String descripcion;
    @NotNull(message = "La fecha de inicio es obligatoria")
    @Past(message="Fecha de inicio incorrecta")
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaFin;
    private Persona persona;  
}
