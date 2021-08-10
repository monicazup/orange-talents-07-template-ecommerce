package com.zupedu.monica.mercadolivre.produto;

import com.zupedu.monica.mercadolivre.produto.imagem.ImagemRequest;
import com.zupedu.monica.mercadolivre.produto.imagem.Uploader;
import com.zupedu.monica.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.servlet.annotation.MultipartConfig;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    @Autowired
    EntityManager manager;

    @Autowired
    Uploader uploader;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuario){

        Produto produto = request.toEntity(manager, usuario);
        manager.persist(produto);

    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public void adicionarImagemAoProduto(@PathVariable("id") Long id,
                                         @Valid ImagemRequest imagemRequest,
                                         @AuthenticationPrincipal Usuario usuarioDoUpload) {


        /*
        Pesquisa produto passado no path
        */
        Produto produtoDoUpload = manager.find(Produto.class, id);

        /*
        Verifica se é o usuário dono do produto quem está adicionando imagem
         */
        if(!produtoDoUpload.pertenceAoUsuario(usuarioDoUpload)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploader.enviar(imagemRequest.getImagens());
        produtoDoUpload.associaImagens(links);

        manager.merge(produtoDoUpload);
    }
}
