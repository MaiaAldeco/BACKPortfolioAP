package com.maiaaldeco.portfolio.dto;

import com.maiaaldeco.portfolio.entity.Persona;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class TrabajoDto {
    
    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
    private Persona persona;

    public TrabajoDto(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
    
    
}
