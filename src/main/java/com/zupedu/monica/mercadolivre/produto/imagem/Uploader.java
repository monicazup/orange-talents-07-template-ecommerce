package com.zupedu.monica.mercadolivre.produto.imagem;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class Uploader {

    /**
     * @param imagens
     * @return links para imagens upadas
     */
    public Set<String> enviar(List<MultipartFile> imagens) {
        return imagens
                .stream()
                .map(imagem -> "http://mercadolivre.io/"
                        +imagem.getOriginalFilename()+"-"
                        + UUID.randomUUID())
                .collect(Collectors.toSet());
    }
}
