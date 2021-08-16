package com.zupedu.monica.mercadolivre.produto.imagem;

import com.zupedu.monica.mercadolivre.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImagemProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @NotNull(message = "não pode ser nulo")
    private  Produto produto;
    @URL
    @NotBlank
    private String link;
    public ImagemProduto(@Valid Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }


    @Deprecated
    public ImagemProduto(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return produto.equals(that.produto) && link.equals(that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, link);
    }

    public String getLink() {
        return link;
    }
}
