package com.zupedu.monica.mercadolivre.produto.opiniao;

import com.zupedu.monica.mercadolivre.produto.Produto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OpiniaoController {

    @PostMapping
    public void avaliar(@RequestBody Produto produto) {

    }
}
