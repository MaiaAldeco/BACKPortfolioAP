package com.maiaaldeco.portfolio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name="SKILLS")
public class Habilidad {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID_SKILL")
    private long id;
    @NotNull
    @Column(name = "SKILL_NAME")
    private String habilidad;
    @NotNull
    @Column(name = "PERCENTAGE")
    private int porcentaje;
    @Column(name = "PERSON")
    private Persona persona;

    public Habilidad(String habilidad, int porcentaje) {
        this.habilidad = habilidad;
        this.porcentaje = porcentaje;
    }

    public Habilidad(String habilidad, int porcentaje, Persona persona) {
        this.habilidad = habilidad;
        this.porcentaje = porcentaje;
        this.persona = persona;
    }
}
