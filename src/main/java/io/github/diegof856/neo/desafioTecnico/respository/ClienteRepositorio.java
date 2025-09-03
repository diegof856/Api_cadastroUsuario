package io.github.diegof856.neo.desafioTecnico.respository;

import io.github.diegof856.neo.desafioTecnico.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepositorio extends JpaRepository<Cliente, UUID> {
    Cliente findByClienteId(String clienteId);
}
