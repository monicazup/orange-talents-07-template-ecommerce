package com.zupedu.monica.mercadolivre.produto;

import com.zupedu.monica.mercadolivre.categoria.Categoria;
import com.zupedu.monica.mercadolivre.config.ApiException;
import com.zupedu.monica.mercadolivre.produto.avaliacao.AvaliacaoProduto;
import com.zupedu.monica.mercadolivre.produto.caracteristica.Caracteristica;
import com.zupedu.monica.mercadolivre.produto.caracteristica.DetalheCaracteristicaDTO;
import com.zupedu.monica.mercadolivre.produto.imagem.ImagemProduto;
import com.zupedu.monica.mercadolivre.produto.pergunta.PerguntaSobreProduto;
import com.zupedu.monica.mercadolivre.usuario.Usuario;
import org.hibernate.query.Query;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Column(columnDefinition = "TEXT", length = 1000, nullable = false)
    private String descricao;
    @Positive
    private BigDecimal valor;
    @PositiveOrZero
    private Integer quantidadeDisponivel;
    @Size(min = 3, message = "O produto deve ter pelo menos 3 características")
    @OneToMany(cascade = CascadeType.ALL) //
    @JoinColumn(name = "produto_id")
    private Set<Caracteristica> caracteristicas;
    @ManyToOne
    @NotNull
    Categoria categoria;
    @NotNull
    private Instant instanteDeCadastro = Instant.now();
    @ManyToOne
    @NotNull
    private Usuario usuario;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE) //Atualiza as imagens ao atualizar produtos
    private Set<ImagemProduto> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    private Set<PerguntaSobreProduto> perguntas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<AvaliacaoProduto> avaliacoes = new HashSet<>();


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

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens =
                links
                        .stream()
                        .map(link -> new ImagemProduto(this, link))
                        .collect(Collectors.toSet()
                        );

        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelUsuario) {
        return this.usuario.getIdString().equals(possivelUsuario.getIdString());
    }

    public static Produto buscaPorId(EntityManager manager, Long id) {
        Produto produto = manager.find(Produto.class, id);
        if (produto == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        return produto;
    }

    public Set<DetalheCaracteristicaDTO> mapCaracteristicas(
                                        Function<Caracteristica, DetalheCaracteristicaDTO> funcaoMapeadora) {

        return this.caracteristicas
                .stream()
                .map(funcaoMapeadora)
                .collect(Collectors.toSet());

    }

    public Set<String> mapImagens(Function<ImagemProduto, String> fMapeadora) {
        return this.imagens
                .stream()
                .map(fMapeadora)
                .collect(Collectors.toSet());

    }

    public Set<String> mapPerguntas(Function<PerguntaSobreProduto, String> fMapeadora) {
        return this.perguntas
                .stream()
                .map(fMapeadora)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapAvaliacoes(Function<AvaliacaoProduto, T> fMapeadora) {
        return this.avaliacoes
                .stream()
                .map(fMapeadora)
                .collect(Collectors.toSet());
    }

    /*
    Getters
    */

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<AvaliacaoProduto> getAvaliacoes() {
        return avaliacoes;
    }
}
