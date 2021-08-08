package com.zupedu.monica.mercadolivre.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Email @Column(unique = true)
    private String email;
    @NotBlank @Size(min = 6)
    private String senha;
    @PastOrPresent
    @NotNull @JsonFormat(pattern = "dd/MM/aaaa")
    private LocalDateTime horaCadastro = LocalDateTime.now();

    //relacionamento many to many ele não carrega a lista (LAZY).
    //Colocamos EAGER para carregar a lista de perfis quando carregar o usuário
//    @ManyToMany(fetch = FetchType.EAGER) // por default,quando carregamos um objeto com
//    private List<Perfil> perfis = new ArrayList<>();

    public Usuario(String email, @NotNull @Size(min = 6) SenhaLimpa senha) { // RECEBER SENHA EM TEXTO LIMPO
        this.email = email;
        this.senha = senha.hash();
    }

    @Deprecated    // Para uso exclusivo do Spring
    public Usuario() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
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

    public String getIdString() {
        return id.toString();
    }
}
