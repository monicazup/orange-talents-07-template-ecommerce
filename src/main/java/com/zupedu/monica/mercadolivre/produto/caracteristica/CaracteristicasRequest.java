package com.zupedu.monica.mercadolivre.produto.caracteristica;


import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

public class CaracteristicasRequest {
    @NotBlank(message = "Não pode estar em branco")
    String nome;
    @NotBlank(message = "Não pode estar em branco")
    String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //Não funcionou
    public static Set<Caracteristica> toModel(Set<CaracteristicasRequest> requestSet) {
        return requestSet.stream()
                .map(caracteristicasRequest -> new Caracteristica(
                        caracteristicasRequest.getNome(),
                        caracteristicasRequest.getDescricao()
                ))
                .collect(Collectors.toSet());
    }
}
