package io.github.diegof856.neo.desafioTecnico.controller.dto;

import io.github.diegof856.neo.desafioTecnico.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Pessoa")
public record PessoaFisicaResponseDTO(
        @Schema(name = "Id")
        UUID id,
        @Schema(name = "Nome")
        String nome,
        @Schema(name = "Cpf")
        String cpf,
        @Schema(name = "Data de nascimento")
        LocalDate dataNascimento,
        @Schema(name = "Idade")
        Integer idade,
        @Schema(name = "Genero")
        String sexo,
        @Schema(name = "Estado civil")
        String estadoCivil,
        @Schema(name = "Endereco")
        Endereco endereco) {
}
