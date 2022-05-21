package com.maiaaldeco.portfolio.entity;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name="trabajos")
public class Trabajo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_trabajo")
    private long id;
    @NotNull
    @Column(name = "title")
    private String titulo;
    @NotNull
    @Column(name = "description")
    private String descripcion;
    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Persona persona;
    
    public Trabajo(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Trabajo(String titulo, String descripcion, Persona persona) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.persona = persona;
    }
    
    
}
