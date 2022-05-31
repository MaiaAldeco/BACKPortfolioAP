package com.maiaaldeco.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "experience")
@NoArgsConstructor
@Getter @Setter
public class Experiencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exp")
    private long id;
    @NotNull
    @Column(name = "exp_name")
    private String nombre;
    @NotNull
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "tasks", length = 4000)
    private String descripcion;
    @NotNull
    @Column(name = "start_date")
    private java.sql.Date fechaInicio;
    @Column(name = "end_date")
    private java.sql.Date fechaFin;
    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Persona persona;

    public Experiencia(String nombre, String puesto, String tareas, Date fechaInicio, Date fechaFin) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.descripcion = tareas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Experiencia(String nombre, String puesto, String tareas, Date fechaInicio, Date fechaFin, Persona persona) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.descripcion = tareas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.persona = persona;
    }

    public Experiencia(String nombre, String puesto, Date fechaInicio, Persona persona) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.fechaInicio = fechaInicio;
        this.persona = persona;
    }
    
    
}
