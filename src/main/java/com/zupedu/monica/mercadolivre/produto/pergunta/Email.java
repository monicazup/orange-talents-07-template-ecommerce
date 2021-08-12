package com.zupedu.monica.mercadolivre.produto.pergunta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zupedu.monica.mercadolivre.produto.Produto;

import java.time.Instant;
import java.time.LocalDateTime;

public class Email {

    String remetente;
    String destinatario;
    String corpo;
    @JsonFormat(pattern = "dd/MM/aaaa")
    LocalDateTime instanteDeEnvio;
    Long idProduto;

    public Email(Long idProduto,
                 String remetente,
                 String destinatario,
                 String corpo
                 ) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.corpo = corpo;
        this.instanteDeEnvio = LocalDateTime.now();
        this.idProduto = idProduto;
    }

    @Override
    public String toString() {
        return "__________________________"+
                "\nEMAIL " +
                "\nRemetente: " + remetente +
                "\nID do Produto: " + idProduto.toString() +
                "\nDestinatário: " + destinatario +
                "\nMensagem: " + corpo  +
                "\nHorário: " + instanteDeEnvio.toString() +
                "\n__________________________";
    }
}
