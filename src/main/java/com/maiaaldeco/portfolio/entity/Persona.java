package com.maiaaldeco.portfolio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name="PERSONS")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PERSON")
    private long id;
    @NotNull
    @Column(name="FIRSTNAME")
    private String nombre;
    @NotNull
    @Column(name="LASTNAME")
    private String apellido;
    @NotNull
    @Column(name="STACK")
    private String stack;
    @NotNull
    @Column(name="TECHNO")
    private String tecnologia;
    @NotNull
    @Column(name="DESC")
    private String descripcion;
    @Column(name="CONTACT")
    private Contacto contacto;

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
