package io.github.diegof856.neo.desafioTecnico.service;

import io.github.diegof856.neo.desafioTecnico.model.Usuario;
import io.github.diegof856.neo.desafioTecnico.respository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepositorio repository;
    private final PasswordEncoder encoder;
    public void salvar(Usuario usuario){
        var senha = usuario.getSenha();
        usuario.setSenha(this.encoder.encode(senha));
        this.repository.save(usuario);
    }
    public Usuario obterPorLogin(String login){
        return this.repository.findByLogin(login);
    }
}
