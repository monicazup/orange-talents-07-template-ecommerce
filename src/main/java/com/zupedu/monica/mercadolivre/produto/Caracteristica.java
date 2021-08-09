package com.zupedu.monica.mercadolivre.produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String nome;
    @NotBlank
    String descricao;
    @ManyToOne
    Produto produto;

    public Caracteristica(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    @Deprecated
    public Caracteristica(){}
}
