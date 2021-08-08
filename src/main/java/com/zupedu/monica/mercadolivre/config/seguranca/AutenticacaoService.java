package com.zupedu.monica.mercadolivre.config.seguranca;

import com.zupedu.monica.mercadolivre.usuario.Usuario;
import com.zupedu.monica.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //ao logar, o Spring invoca esse método passando como parâmetro o username
        //O Spring busca no banco de dados e verificar em memória se a senha corresponde
        Optional<Usuario> possivelUsuario = userRepository.findByEmail(username);

        if (!possivelUsuario.isPresent()) {
            throw new UsernameNotFoundException("Credenciais inválidas");
        }
        return possivelUsuario.get();
    }
}
