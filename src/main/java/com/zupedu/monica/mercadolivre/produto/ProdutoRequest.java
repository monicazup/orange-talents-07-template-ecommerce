package com.zupedu.monica.mercadolivre.produto;

import com.zupedu.monica.mercadolivre.categoria.Categoria;
import com.zupedu.monica.mercadolivre.config.ApiException;
import com.zupedu.monica.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoRequest {

    @NotBlank
    private String nome;
    @Positive(message = " não pode ser negativo")
    private BigDecimal valor;
    @PositiveOrZero(message = " não pode ser negativa")
    private Integer quantidadeDisponivel;
    //maior que 3 anotacao
    @Valid @NotNull @Size(min = 3, message = "no mínimo três")
    private Set<CaracteristicasRequest> caracteristicasDoProduto;
    @NotBlank(message = " é obrigatória")
    private String descricao;
    @NotNull(message = " é obrigatória")
    private Long categoriaId;

    public Set<Caracteristica> converterCaracteristicasRequestToModel(Set<CaracteristicasRequest> requestSet) {
        return requestSet.stream()
                .map(caracteristicasRequest -> new Caracteristica(
                        caracteristicasRequest.getNome(),
                        caracteristicasRequest.getDescricao()
                ))
                .collect(Collectors.toSet());
    }


    public Produto toEntity(EntityManager manager, Usuario usuario) {
        //Não funcionou
        // Set<Caracteristica> caracteristicas = caracteristicasDoProduto.toModel(this.caracteristicasDoProduto);


        Set<Caracteristica> caracteristicas = converterCaracteristicasRequestToModel(this.caracteristicasDoProduto);

        Categoria possivelCategoria = manager.find(Categoria.class, categoriaId);
        if (possivelCategoria == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Categoria inválida");
        }
        return new Produto(nome,
                descricao,
                valor,
                quantidadeDisponivel,
                caracteristicas,
                possivelCategoria,
                usuario);
    }


    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public Set<CaracteristicasRequest> getCaracteristicasDoProduto() {
        return caracteristicasDoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public void setCaracteristicasDoProduto(Set<CaracteristicasRequest> caracteristicasDoProduto) {
        this.caracteristicasDoProduto = caracteristicasDoProduto;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
