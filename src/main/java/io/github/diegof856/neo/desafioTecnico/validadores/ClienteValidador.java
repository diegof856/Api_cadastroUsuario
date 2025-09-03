package io.github.diegof856.neo.desafioTecnico.validadores;

import io.github.diegof856.neo.desafioTecnico.exceptions.RegistroDuplicadoException;
import io.github.diegof856.neo.desafioTecnico.model.Cliente;
import io.github.diegof856.neo.desafioTecnico.respository.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteValidador {
    private final ClienteRepositorio clienteRepositorio;


    public void validar(Cliente cliente){
        if(existeClienteCadastrado(cliente) != null){
            throw new RegistroDuplicadoException("Cliente já cadastrado!");
        }
    }

    private Cliente existeClienteCadastrado(Cliente cliente) {
        return this.clienteRepositorio.findByClienteId(cliente.getClienteId());
    }


}
