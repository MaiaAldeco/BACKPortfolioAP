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
public class EstudioDto {
    @NotBlank
    private String lugar;
    @NotBlank
    private String curso;
    @NotBlank
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaFin;
    private Persona persona;

    public EstudioDto(String lugar, String curso, Date fechaInicio, Date fechaFin) {
        this.lugar = lugar;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public EstudioDto(String lugar, String curso, Date fechaInicio, Persona persona) {
        this.lugar = lugar;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.persona = persona;
    }
    
    
}
