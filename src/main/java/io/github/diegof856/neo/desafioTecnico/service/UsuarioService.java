package io.github.diegof856.neo.desafioTecnico.service;

import io.github.diegof856.neo.desafioTecnico.controller.dto.UsuarioDTO;
import io.github.diegof856.neo.desafioTecnico.exceptions.OperacaoNaoPermitidaException;
import io.github.diegof856.neo.desafioTecnico.model.Usuario;
import io.github.diegof856.neo.desafioTecnico.respository.UsuarioRepositorio;
import io.github.diegof856.neo.desafioTecnico.validadores.UsuarioValidador;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepositorio repository;
    private final PasswordEncoder encoder;
    private final UsuarioValidador validador;
    public Usuario salvar(Usuario usuario){
        this.validador.validar(usuario);
        var senha = usuario.getSenha();
        usuario.setSenha(this.encoder.encode(senha));
        return this.repository.save(usuario);
    }
    public Usuario obterPorLogin(String login){
        return this.repository.findByLogin(login);
    }
    public Optional<Usuario> obterPorId(UUID id){
        return this.repository.findById(id);
    }
    public void atualizar( Usuario usuario){
        if(usuario.getId() == null){
            throw new OperacaoNaoPermitidaException("Não é possivel atualizar um usuario sem estar cadastrado na base dados");
        }
        usuario.setSenha(this.encoder.encode(usuario.getSenha()));
        this.repository.save(usuario);
    }
    public void deletar(Usuario usuario){
        this.repository.delete(usuario);
    }


}
