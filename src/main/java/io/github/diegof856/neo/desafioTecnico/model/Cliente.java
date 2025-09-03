package io.github.diegof856.neo.desafioTecnico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cliente_id")
    private String clienteId;
    @Column(name = "cliente_segredo")
    private String clienteSegredo;
    @Column(name = "redirect_uri")
    private String redirectURI;
    @Column(name = "scope")
    private String scopo;
}
