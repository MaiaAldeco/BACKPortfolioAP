package com.maiaaldeco.portfolio.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ContactoDto {
    @NotBlank
    private String localidad;
    @NotBlank
    private String telefono;
    @NotBlank
    private String email;
    
}
