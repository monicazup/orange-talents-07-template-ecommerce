package com.zupedu.monica.mercadolivre.produto.pergunta;

import com.zupedu.monica.mercadolivre.produto.Produto;
import com.zupedu.monica.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class PerguntaSobreProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull
    @ManyToOne
    private Produto produto;
    @NotNull
    @ManyToOne
    private Usuario autor;
    @NotNull
    @ManyToOne
    private Usuario vendedor;
    private Instant instanteDaCriacao = Instant.now();


    public PerguntaSobreProduto(String titulo, Produto produto, Usuario autor, Usuario vendedor) {
        this.titulo = titulo;
        this.produto = produto;
        this.autor = autor;
        this.vendedor = vendedor;
    }

    @Deprecated
    public PerguntaSobreProduto() {
    }


    public String toEmail() {
        return "PERGUNTA: " + this.titulo;
    }
}
