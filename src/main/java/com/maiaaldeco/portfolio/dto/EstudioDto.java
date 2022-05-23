package com.maiaaldeco.portfolio.dto;

import com.maiaaldeco.portfolio.entity.Persona;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EstudioDto {
    @NotBlank(message = "El lugar del curso/carrera es obligatorio")
    private String lugar;
    @NotBlank(message = "El nombre del curso/carrera es obligatorio")
    private String curso;
    @Past(message="Fecha de inicio incorrecta")
    @NotNull(message = "La fecha de inicio es obligatoria")
    private java.sql.Date fechaInicio;
    @Past(message="Fecha de fin incorrecta")
    private java.sql.Date fechaFin;
    @NotNull(message = "Debes asignar una persona a este estudio")
    private Persona persona;
}
