package com.zupedu.monica.mercadolivre.produto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zupedu.monica.mercadolivre.produto.avaliacao.AvaliacaoProduto;
import com.zupedu.monica.mercadolivre.produto.caracteristica.DetalheCaracteristicaDTO;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.IntStream;


public class DetalheProdutoView {

    private String nome;
    private String descricao;
    private BigDecimal valor;
    @JsonView
    private Set<DetalheCaracteristicaDTO> caracteristicas;
    private Set<String> linksImagens;
    private Set<String> perguntas;
    private Set<Map<String, String>> avaliacoes;
    private double mediaNota;



    public DetalheProdutoView(Produto produto) {

        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.caracteristicas = produto.mapCaracteristicas(DetalheCaracteristicaDTO::new);
        this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
        /*        produto.getCaracteristicas()
                        .stream()
                        .map(caracteristica -> new DetalheCaracteristicaDTO(caracteristica))
                        .collect(Collectors.toSet());

        /*        produto.mapCaracteristicas(caracteristica -> new DetalheCaracteristicaDTO(caracteristica)); */

        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());

        this.avaliacoes = produto.mapAvaliacoes(avaliacao ->
                Map.of("titulo", avaliacao.getTitulo(),
                        "descricao", avaliacao.getDescricao()));

        /* CALCULO DA MÉDIA DE NOTAS DAS AVALIAÇÕES*/
        Set<Integer> notas = produto.mapAvaliacoes(avaliacao -> avaliacao.getNota());
        IntStream notasToInt = notas.stream().mapToInt(nota -> nota);
        OptionalDouble media = notasToInt.average();
        if(media.isPresent()) {
            this.mediaNota = media.getAsDouble();
        }

    }


    /*
    Getters e setters
    */

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setCaracteristicas(Set<DetalheCaracteristicaDTO> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Set<DetalheCaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getAvaliacoes() {
        return avaliacoes;
    }

    public double getMediaNota() {
        return mediaNota;
    }
}
