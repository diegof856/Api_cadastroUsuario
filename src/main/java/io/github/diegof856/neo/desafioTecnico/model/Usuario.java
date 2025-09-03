package io.github.diegof856.neo.desafioTecnico.model;

import io.github.diegof856.neo.desafioTecnico.model.conversor.Conversor;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String login;

    @Column
    private String senha;

    @Convert(converter = Conversor.class)
    @Column(name = "roles")
    private List<String> roles;

}