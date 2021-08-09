package com.zupedu.monica.mercadolivre.produto;

import com.zupedu.monica.mercadolivre.categoria.Categoria;
import com.zupedu.monica.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Column(columnDefinition = "TEXT", length = 1000, nullable = false) @NotBlank
    private String descricao;
    @Positive
    private BigDecimal valor;
    @PositiveOrZero
    private Integer quantidadeDisponivel;
    @Size(min = 3, message = "O produto deve ter pelo menos 3 características")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id")
    private Set<Caracteristica> caracteristicas;
    @ManyToOne
    @NotNull Categoria categoria;
    @NotNull
    private Instant instanteDeCadastro = Instant.now();
    @ManyToOne @NotNull
    Usuario usuario;


    @Deprecated
    public Produto() {
    }

    public Produto(String nome,
                   String descricao,
                   BigDecimal valor,
                   Integer quantidadeDisponivel,
                   Set<Caracteristica> caracteristicas,
                   Categoria categoria,
                   Usuario usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.usuario = usuario;
    }
}
