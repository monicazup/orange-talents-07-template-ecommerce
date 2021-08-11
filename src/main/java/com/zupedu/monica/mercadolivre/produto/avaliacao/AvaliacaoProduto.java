package com.zupedu.monica.mercadolivre.produto.avaliacao;

import com.zupedu.monica.mercadolivre.produto.Produto;
import com.zupedu.monica.mercadolivre.usuario.Usuario;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class AvaliacaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Max(value = 5) @Positive
    private Integer avaliacao;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @ManyToOne
    private Produto produto;
    private LocalDate date = LocalDate.now();


    public AvaliacaoProduto(Integer avaliacao, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.avaliacao = avaliacao;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Deprecated
    public AvaliacaoProduto(){}
}
