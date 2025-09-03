package io.github.diegof856.neo.desafioTecnico.validadores;

import io.github.diegof856.neo.desafioTecnico.exceptions.RegistroDuplicadoException;
import io.github.diegof856.neo.desafioTecnico.model.Usuario;
import io.github.diegof856.neo.desafioTecnico.respository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioValidador {
    private final UsuarioRepositorio usuarioRepositorio;

    public void validar(Usuario usuario){
        if(existeUsuarioCadastrado(usuario) != null){
            throw new RegistroDuplicadoException("Usuario já cadastrado!");
        }
    }

    private Usuario existeUsuarioCadastrado(Usuario usuario) {
       return this.usuarioRepositorio.findByLogin(usuario.getLogin());
    }
}
