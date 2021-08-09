package com.zupedu.monica.mercadolivre.produto;

import com.zupedu.monica.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    @Autowired
    EntityManager manager;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuario){


        Produto produto = request.toEntity(manager, usuario);
        manager.persist(produto);


    }
}
