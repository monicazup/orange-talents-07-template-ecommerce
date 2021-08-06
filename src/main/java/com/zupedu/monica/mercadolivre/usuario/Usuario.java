package com.zupedu.monica.mercadolivre.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Email
    private String email;
    @NotBlank @Size(min = 6)
    private String senha;
    @PastOrPresent
    @NotNull @JsonFormat(pattern = "dd/MM/aaaa")
    private LocalDateTime horaCadastro = LocalDateTime.now();

    public Usuario(String email, @NotNull @Size(min = 6) SenhaLimpa senha) { // RECEBER SENHA EM TEXTO LIMPO
        this.email = email;
        this.senha = senha.hash() ;
    }

    @Deprecated    // Para uso exclusivo do Spring
    public Usuario() {}
}
