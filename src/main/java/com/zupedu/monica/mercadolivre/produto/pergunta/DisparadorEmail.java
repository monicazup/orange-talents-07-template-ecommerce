package com.zupedu.monica.mercadolivre.produto.pergunta;

import org.springframework.stereotype.Component;

@Component
public class DisparadorEmail {

    public String enviar(Email email) {
        System.out.println(email.toString());
        return email.toString();

    }
}
