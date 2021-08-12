package com.zupedu.monica.mercadolivre.produto.pergunta;

import com.zupedu.monica.mercadolivre.produto.Produto;
import com.zupedu.monica.mercadolivre.usuario.Usuario;

public class PerguntaSobreProdutoRequest {

    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public PerguntaSobreProduto toEntity(Usuario usuarioAutor, Usuario usuarioVendedor, Produto produto) {
        return new PerguntaSobreProduto(this.titulo, produto, usuarioAutor, usuarioVendedor);
    }
}
