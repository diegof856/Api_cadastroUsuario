package io.github.diegof856.neo.desafioTecnico.security;

import io.github.diegof856.neo.desafioTecnico.model.Usuario;
import io.github.diegof856.neo.desafioTecnico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutenticacaoCustomizadaProvedor implements AuthenticationProvider {
        private final UsuarioService usuarioService;
        private final PasswordEncoder encoder;
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String login = authentication.getName();
            String senhaDigitada = authentication.getCredentials().toString();
            Usuario usuarioEncontrado = this.usuarioService.obterPorLogin(login);
            if(usuarioEncontrado == null){
                throw getUsuarioNaoEncontrado();
            }
            String senhaCriptografada = usuarioEncontrado.getSenha();
            boolean senhasBatem = encoder.matches(senhaDigitada,senhaCriptografada);

            if(senhasBatem){
                return new AutenticacaoCustomizada(usuarioEncontrado);
            }
            throw getUsuarioNaoEncontrado();
        }

        private static UsernameNotFoundException getUsuarioNaoEncontrado() {
            return new UsernameNotFoundException("Usuário e/ou senha incorretos!");
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
        }
}
