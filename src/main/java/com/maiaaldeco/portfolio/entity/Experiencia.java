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
@Table(name = "EXPERIENCES")
@NoArgsConstructor
@Getter @Setter
public class Experiencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXP")
    private long id;
    @NotNull
    @Column(name = "EXP_NAME")
    private String nombre;
    @NotNull
    @Column(name = "JOB")
    private String puesto;
    @Column(name = "TASKS")
    private String tareas;
    @NotNull
    @Column(name = "START_DATE")
    private java.sql.Date fechaInicio;
    @NotNull
    @Column(name = "END_DATE")
    private String fechaFin;
    @Column(name = "PERSON")
    private Persona persona;

    public Experiencia(String nombre, String puesto, String tareas, Date fechaInicio, String fechaFin) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.tareas = tareas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Experiencia(String nombre, String puesto, String tareas, Date fechaInicio, String fechaFin, Persona persona) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.tareas = tareas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.persona = persona;
    }
}
