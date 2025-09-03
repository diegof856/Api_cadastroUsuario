package io.github.diegof856.neo.desafioTecnico.service;

import io.github.diegof856.neo.desafioTecnico.exceptions.OperacaoNaoPermitidaException;
import io.github.diegof856.neo.desafioTecnico.model.Cliente;
import io.github.diegof856.neo.desafioTecnico.respository.ClienteRepositorio;
import io.github.diegof856.neo.desafioTecnico.validadores.ClienteValidador;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepositorio repository;
    private final PasswordEncoder encoder;
    private final ClienteValidador validador;

    public Cliente salvar(Cliente cliente){
        this.validador.validar(cliente);
        var senhaCriptografada = this.encoder.encode(cliente.getClienteSegredo());
        cliente.setClienteSegredo(senhaCriptografada);
        return this.repository.save(cliente);
    }
    public Optional<Cliente> obterPorId(UUID id){
        return this.repository.findById(id);
    }
    public Cliente obterPorClientID(String clientId){
        return this.repository.findByClienteId(clientId);
    }

    public void atualizar(Cliente cliente) {
        if(cliente == null){
            throw new OperacaoNaoPermitidaException("Não é possivel atualizar um cliente sem estar cadastrado na base dados");
        }
        cliente.setClienteSegredo(this.encoder.encode(cliente.getClienteSegredo()));
        this.repository.save(cliente);
    }

    public void deletar(Cliente cliente) {
        this.repository.delete(cliente);
    }
}
