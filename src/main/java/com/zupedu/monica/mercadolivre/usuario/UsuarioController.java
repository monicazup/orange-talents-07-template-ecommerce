package com.zupedu.monica.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private EntityManager manager;

    @PostMapping @Transactional
    public void cadastrar(@RequestBody @Valid UsuarioRequest request){
        Usuario usuario = request.toEntity();
        manager.persist(usuario);

    }
}
