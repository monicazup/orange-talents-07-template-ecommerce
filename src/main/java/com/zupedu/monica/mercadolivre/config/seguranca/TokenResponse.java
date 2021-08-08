package com.zupedu.monica.mercadolivre.config.seguranca;

public class TokenResponse {

    String token, tipo;

    public TokenResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
