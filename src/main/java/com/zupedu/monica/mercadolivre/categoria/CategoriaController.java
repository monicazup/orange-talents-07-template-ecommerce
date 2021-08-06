package com.zupedu.monica.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private EntityManager manager;

    @Transactional
    @PostMapping
    public void cadastrar(@RequestBody @Valid CategoriaRequest request){
        Categoria categoria = request.toEntity(manager);
        manager.persist(categoria);

    }
}
