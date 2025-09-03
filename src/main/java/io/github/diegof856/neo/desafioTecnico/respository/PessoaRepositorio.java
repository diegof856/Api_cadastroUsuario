package io.github.diegof856.neo.desafioTecnico.respository;

import io.github.diegof856.neo.desafioTecnico.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepositorio extends JpaRepository<PessoaFisica, UUID> {
}
