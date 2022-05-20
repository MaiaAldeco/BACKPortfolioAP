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
@Table(name="CONTACTS")
public class Contacto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CONT")
    private long id;
    @NotNull
    @Column(name="LOCATION")
    private String localidad;
    @NotNull
    @Column(name="PHONE")
    private String phone;
    @NotNull
    @Column(name="EMAIL")
    private String email;

    public Contacto(String localidad, String phone, String email) {
        this.localidad = localidad;
        this.phone = phone;
        this.email = email;
    }
    
    
}
