package com.zupedu.monica.mercadolivre.usuario;

import com.zupedu.monica.mercadolivre.anotacao.CampoUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank @Email @CampoUnico(fieldName = "email", entityClass = Usuario.class)
    private String email;
    @NotBlank @Size(min = 6)
    private String senha;

    public UsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toEntity() {
     return new Usuario(this.email, new SenhaLimpa(senha));
    }

}
