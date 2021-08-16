package com.zupedu.monica.mercadolivre.produto;

import com.zupedu.monica.mercadolivre.config.ApiException;
import com.zupedu.monica.mercadolivre.produto.avaliacao.AvaliacaoProduto;
import com.zupedu.monica.mercadolivre.produto.avaliacao.AvaliacaoProdutoRequest;
import com.zupedu.monica.mercadolivre.produto.imagem.ImagemRequest;
import com.zupedu.monica.mercadolivre.produto.imagem.Uploader;
import com.zupedu.monica.mercadolivre.produto.pergunta.DisparadorEmail;
import com.zupedu.monica.mercadolivre.produto.pergunta.Email;
import com.zupedu.monica.mercadolivre.produto.pergunta.PerguntaSobreProduto;
import com.zupedu.monica.mercadolivre.produto.pergunta.PerguntaSobreProdutoRequest;
import com.zupedu.monica.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

import static com.zupedu.monica.mercadolivre.produto.Produto.buscaPorId;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    EntityManager manager;

    @Autowired
    Uploader uploader;

    @Autowired
    DisparadorEmail disparadorEmail;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ProdutoRequest request,
                          @AuthenticationPrincipal Usuario usuario) {

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
        if (!produtoDoUpload.pertenceAoUsuario(usuarioDoUpload)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploader.enviar(imagemRequest.getImagens());
        produtoDoUpload.associaImagens(links);

        manager.merge(produtoDoUpload);
    }


    @Transactional
    @PostMapping("/{id}/avaliar")
    public void avaliarProduto(@PathVariable("id") Long id,
                               @RequestBody AvaliacaoProdutoRequest request,
                               @AuthenticationPrincipal Usuario usuario) {
        Produto produto = buscaPorId(manager, id);
        AvaliacaoProduto avaliacao = request.toEntity(usuario, produto);
        manager.persist(avaliacao);

    }

    @PostMapping("/{id}/perguntar")
    @Transactional
    public void enviarPergunta(@PathVariable("id") Long id,
                               @RequestBody @Valid PerguntaSobreProdutoRequest request,
                               @AuthenticationPrincipal Usuario usuarioAutor) {
        Produto produto = buscaPorId(manager, id);
        Usuario usuarioVendedor = produto.getUsuario();

        PerguntaSobreProduto pergunta = request.toEntity(usuarioAutor, usuarioVendedor, produto);

        manager.persist(pergunta);

        Email email = new Email(
                id,
                "nao-responda@mercadolivre.com.br",
                usuarioVendedor.getUsername(),
                pergunta.toEmail()
        );

        disparadorEmail.enviar(email);
    }

    @GetMapping("/{id}")
    public DetalheProdutoView detalhar(@PathVariable("id") Long id) {
        Produto produto = buscaPorId(manager, id);

        return new DetalheProdutoView(produto);
    }

}
