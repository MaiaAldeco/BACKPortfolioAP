package com.maiaaldeco.portfolio.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maiaaldeco.portfolio.entity.Persona;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="user")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;
    @NotNull
    @Column(name = "name")
    private String nombre;
    @NotNull
    @Column(unique = true, name = "username")
    private String nombreUsuario;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "password")
    private String password;
    @Transient
    private Collection<? extends GrantedAuthority> authorities; // Extiende clase spring boot
    @OneToOne(mappedBy="usuario")
    @JsonIgnore
//    @NotFound(action=NotFoundAction.IGNORE)
    private Persona persona;
    @NotNull
    @ManyToMany(targetEntity = Rol.class, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    public Usuario(String nombre, String nombreUsuario, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public Usuario(String nombre, String nombreUsuario, String email, String password, Persona persona) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.persona = persona;
    }
    
    

    //ASIGNA PRIVILEGIOS A LOS USUARIOS
    public static Usuario build(Usuario usuario) {
        //OBTENER ROLES Y CONVERTIRLOS EN AUTHORITIES
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        //SE DEVUELVE UN NUEVO USUARIO
        return new Usuario(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
