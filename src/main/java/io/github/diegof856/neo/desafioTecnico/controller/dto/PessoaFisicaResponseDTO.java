package io.github.diegof856.neo.desafioTecnico.controller.dto;

import io.github.diegof856.neo.desafioTecnico.model.Endereco;

import java.time.LocalDate;
import java.util.UUID;

public record PessoaFisicaResponseDTO(UUID id, String nome, String cpf, LocalDate dataNascimento, Integer idade,String sexo, String estadoCivil, Endereco endereco) {
}
