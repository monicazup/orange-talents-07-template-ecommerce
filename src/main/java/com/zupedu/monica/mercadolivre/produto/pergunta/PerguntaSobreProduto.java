package com.zupedu.monica.mercadolivre.produto.pergunta;

import com.zupedu.monica.mercadolivre.produto.Produto;
import com.zupedu.monica.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

@Entity
public class PerguntaSobreProduto implements Comparable<PerguntaSobreProduto>{

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

    public String getTitulo() {
        return titulo;
    }

    public int compareTo(PerguntaSobreProduto o) {
        return this.titulo.compareTo(o.titulo);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerguntaSobreProduto that = (PerguntaSobreProduto) o;
        return Objects.equals(id, that.id) && Objects.equals(titulo, that.titulo) && Objects.equals(produto, that.produto) && Objects.equals(autor, that.autor) && Objects.equals(vendedor, that.vendedor) && Objects.equals(instanteDaCriacao, that.instanteDaCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, produto, autor, vendedor, instanteDaCriacao);
    }
}