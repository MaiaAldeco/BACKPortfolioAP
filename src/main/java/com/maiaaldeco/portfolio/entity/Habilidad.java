package com.maiaaldeco.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@Getter @Setter
@Table(name="skills")
public class Habilidad {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_skill")
    private long id;
    @NotNull
    @Column(name = "skill_name")
    private String habilidad;
    @NotNull
    @Column(name = "percen")
    private int porcentaje;
    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
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
