package com.zupedu.monica.mercadolivre.produto.avaliacao;

import com.zupedu.monica.mercadolivre.produto.Produto;
import com.zupedu.monica.mercadolivre.usuario.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AvaliacaoProdutoRequest {

    @NotNull(message = "Não pode ser nulo") @Max(value = 5, message = "Deve ser um número entre 1 e 5") @Positive(message = "Deve ser um número entre 1 e 5")
    private Integer avaliacao;
    @NotBlank(message = "Não pode estar em branco")
    private String titulo;
    @NotBlank(message = "Não pode estar em branco")
    private String descricao;

    public AvaliacaoProdutoRequest(Integer avaliacao, String titulo, String descricao) {
        this.avaliacao = avaliacao;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public AvaliacaoProduto toEntity(Usuario usuario, Produto produto) {
        return new AvaliacaoProduto(this.avaliacao, this.titulo, this.descricao, usuario, produto);



    }
}
