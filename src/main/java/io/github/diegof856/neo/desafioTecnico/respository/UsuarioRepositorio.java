package io.github.diegof856.neo.desafioTecnico.respository;

import io.github.diegof856.neo.desafioTecnico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, UUID> {
    Usuario findByLogin(String login);

}
