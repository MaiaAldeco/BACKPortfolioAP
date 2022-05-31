package com.maiaaldeco.portfolio.dto;

import com.maiaaldeco.portfolio.entity.Persona;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HabilidadDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String habilidad;
    @NotNull(message = "El porcentaje es obligatorio")
    @Min(value = 0, message ="El porcentaje debe ser mayor a cero")
    private Integer porcentaje;
    private Persona persona;
}
