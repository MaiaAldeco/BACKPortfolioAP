package com.maiaaldeco.portfolio.dto;

import com.maiaaldeco.portfolio.entity.Persona;
import java.sql.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ExperienciaDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String puesto;
    private String tareas;
    @NotBlank
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaFin;
    private Persona persona;

    public ExperienciaDto(String nombre, String puesto, String tareas, Date fechaInicio, Persona persona) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.tareas = tareas;
        this.fechaInicio = fechaInicio;
        this.persona = persona;
        
        
    }

    public ExperienciaDto(String nombre, String puesto, String tareas, Date fechaInicio, Date fechaFin) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.tareas = tareas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ExperienciaDto(String nombre, String puesto, String tareas, Date fechaInicio) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.tareas = tareas;
        this.fechaInicio = fechaInicio;
    }
    
    
    
}
