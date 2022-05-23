package com.maiaaldeco.portfolio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name="person")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_person")
    private long id;
    
    @NotNull
    @Column(name="firstname")
    private String nombre;
    @NotNull
    @Column(name="lastname")
    private String apellido;
    @NotNull
    @Column(name="stack")
    private String stack;
    @NotNull
    @Column(name="tech")
    private String tecnologia;
    @NotNull
    @Column(name="description")
    private String descripcion;
    @JoinColumn(name = "id_contacto")
    @OneToOne(fetch = FetchType.EAGER)
    private Contacto contacto;
//    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Experiencia> exp;
//    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Estudio> estudio;
//    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Trabajo> trabajo;

    public Persona(String nombre, String apellido, String stack, String tecnologia, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.stack = stack;
        this.tecnologia = tecnologia;
        this.descripcion = descripcion;
    }

    public Persona(String nombre, String apellido, String stack, String tecnologia, String descripcion, Contacto contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.stack = stack;
        this.tecnologia = tecnologia;
        this.descripcion = descripcion;
        this.contacto = contacto;
    }    
}
