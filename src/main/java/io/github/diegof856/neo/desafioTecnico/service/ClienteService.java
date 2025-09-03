package io.github.diegof856.neo.desafioTecnico.service;

import io.github.diegof856.neo.desafioTecnico.model.Cliente;
import io.github.diegof856.neo.desafioTecnico.respository.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepositorio repository;
    private final PasswordEncoder encoder;

    public Cliente salvar(Cliente client){
        var senhaCriptografada = this.encoder.encode(client.getClienteSegredo());
        client.setClienteSegredo(senhaCriptografada);
        return this.repository.save(client);
    }
    public Cliente obterPorClientID(String clientId){
        return this.repository.findByClienteId(clientId);
    }
}
