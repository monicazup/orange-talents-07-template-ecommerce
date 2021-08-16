package com.zupedu.monica.mercadolivre.produto.avaliacao;

import com.zupedu.monica.mercadolivre.produto.Produto;
import com.zupedu.monica.mercadolivre.usuario.Usuario;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class AvaliacaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Max(value = 5) @Positive
    private Integer nota;
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

    public AvaliacaoProduto(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoProduto that = (AvaliacaoProduto) o;
        return id.equals(that.id) && Objects.equals(nota, that.nota) && Objects.equals(titulo, that.titulo) && Objects.equals(descricao, that.descricao) && Objects.equals(usuario, that.usuario) && Objects.equals(produto, that.produto) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota, titulo, descricao, usuario, produto, date);
    }

    /* Getters e Construtor default para o Spring*/
    @Deprecated
    public AvaliacaoProduto(){}

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }
}
