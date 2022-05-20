package com.maiaaldeco.portfolio.entity;

import java.sql.Date;
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
@Table(name="STUDIES")
@NoArgsConstructor
@Getter @Setter
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STUDY")
    private long id;
    @NotNull
    @Column(name = "PLACE")
    private String lugar;
    @Column(name = "NAME")
    @NotNull
    private String curso;
    @Column(name = "START_DATE")
    @NotNull
    private java.sql.Date fechaInicio;
    @Column(name = "END_DATE")
    @NotNull
    private String fechaFin;
    @Column(name = "PERSON")
    private Persona persona;

    public Estudio(String lugar, String curso, Date fechaInicio, String fechaFin) {
        this.lugar = lugar;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Estudio(String lugar, String curso, Date fechaInicio, String fechaFin, Persona persona) {
        this.lugar = lugar;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.persona = persona;
    } 
}
