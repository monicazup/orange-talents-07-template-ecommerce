package com.zupedu.monica.mercadolivre.categoria;

import com.zupedu.monica.mercadolivre.anotacao.CampoUnico;
import com.zupedu.monica.mercadolivre.config.ApiException;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequest {

    @NotBlank
    @CampoUnico(fieldName = "nome", entityClass = Categoria.class)
    private String nome;
    @Positive
    private Long maeId;


    @Transactional
    public Categoria toEntity(EntityManager manager) {
        //Se não vier categoria-mãe, salva categoria com nome
        if (maeId == null) {
            return new Categoria(nome);
        }

        //Se vier um id de categoria mãe,
        // precisamos verificar e localizar no banco para montar a instância da entidade
        Categoria possivelCategoria = manager.find(Categoria.class, maeId);
        if (possivelCategoria == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Categoria não encontrada");
        }

        return new Categoria(nome, possivelCategoria);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getMaeId() {
        return maeId;
    }

    public void setMaeId(Long maeId) {
        this.maeId = maeId;
    }
}
