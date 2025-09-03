package io.github.diegof856.neo.desafioTecnico.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "Endereco")
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;
    @Column(name = "rua", length = 100, nullable = false)
    private String rua;
    @Column(name = "numero", length = 25, nullable = false)
    private String numero;
    @Column(name = "complemento", length = 100)
    private String complemento;
    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;
    @Column(name = "cidade", length = 50, nullable = false)
    private String cidade;
    @Column(name = "estado", length = 30, nullable = false)
    private String estado;
}
