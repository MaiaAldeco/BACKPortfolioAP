package com.maiaaldeco.portfolio.security.entity;

import com.maiaaldeco.portfolio.security.enums.RolNombre;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol")
    private long id;
    @NotNull
    @Column(name="rol_name")
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
