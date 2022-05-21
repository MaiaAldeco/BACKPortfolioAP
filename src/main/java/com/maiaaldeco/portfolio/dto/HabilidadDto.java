package com.maiaaldeco.portfolio.dto;

import com.maiaaldeco.portfolio.entity.Persona;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class HabilidadDto {
    @NotBlank
    private String habilidadNombre;
    @NotBlank
    private int porcentaje;
    private Persona persona;

    public HabilidadDto(String habilidadNombre, int porcentaje) {
        this.habilidadNombre = habilidadNombre;
        this.porcentaje = porcentaje;
    } 
}
