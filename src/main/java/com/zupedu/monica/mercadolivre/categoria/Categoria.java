package com.zupedu.monica.mercadolivre.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(unique = true)
    private String nome;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Categoria mae;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Categoria categoria) {
        this.nome = nome;
        this.mae = categoria;
    }

    public void setMae(Categoria mae) {
        this.mae = mae;
    }

    @Deprecated //Para uso exclusivo do Spring
    public Categoria() {}
}
