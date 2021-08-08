package com.zupedu.monica.mercadolivre.config.seguranca;

import com.zupedu.monica.mercadolivre.usuario.Usuario;
import com.zupedu.monica.mercadolivre.usuario.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);
        boolean valido = tokenService.isTokenValido(token);

        System.out.println(valido);

        if(valido) {
            autenticarCliente(token);
        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarCliente(String token){
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken authenticationToken  =
                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        String possivelToken = httpServletRequest.getHeader("Authorization");
        if (possivelToken == null || possivelToken.isEmpty() || !possivelToken.startsWith("Bearer ")) {
            return null;
        }
        return possivelToken.substring(7, possivelToken.length());

    }
}
