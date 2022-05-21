package com.maiaaldeco.portfolio.entity;

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
@Table(name="education")
@NoArgsConstructor
@Getter @Setter
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_study")
    private long id;
    @NotNull
    @Column(name = "place")
    private String lugar;
    @Column(name = "name")
    @NotNull
    private String curso;
    @Column(name = "start_date")
    @NotNull
    private java.sql.Date fechaInicio;
    @Column(name = "end_date")
    private java.sql.Date fechaFin;
    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Persona persona;

    public Estudio(String lugar, String curso, Date fechaInicio, Date fechaFin) {
        this.lugar = lugar;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Estudio(String lugar, String curso, Date fechaInicio, Date fechaFin, Persona persona) {
        this.lugar = lugar;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.persona = persona;
    } 

    public Estudio(String lugar, String curso, Date fechaInicio, Persona persona) {
        this.lugar = lugar;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.persona = persona;
    }
    
    
}
