package com.zupedu.monica.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SenhaLimpa {
    @NotNull
    @Size(min = 6)
    private String senha;

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
