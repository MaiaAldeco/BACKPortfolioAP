package com.maiaaldeco.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maiaaldeco.portfolio.security.entity.Usuario;
import javax.persistence.CascadeType;
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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "person")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private long id;

    @NotNull
    @Column(name = "firstname")
    private String nombre;
    @NotNull
    @Column(name = "lastname")
    private String apellido;
    @NotNull
    @Column(name = "stack")
    private String stack;
    @NotNull
    @Column(name = "tech")
    private String tecnologia;
    @NotNull
    @Column(name = "description", length = 4000)
    private String descripcion;
    @JsonIgnore
    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "contacto_id")
    private Contacto contacto;
    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

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

    public Persona(String nombre, String apellido, String stack, String tecnologia, String descripcion, Usuario usuario, Contacto contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.stack = stack;
        this.tecnologia = tecnologia;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.contacto = contacto;
    }
}
