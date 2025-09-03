package io.github.diegof856.neo.desafioTecnico.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PessoaFisicaDTO(
        @NotBlank(message = "Campo obrigatorio")
        String nome,
        @CPF(message = "Inválido")
        @NotBlank(message = "Campo CPF é obrigatório")
        String cpf,
        @NotNull(message = "Campo data de nascimento é obrigatório")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo obrigatorio")
        String sexo,
        @NotBlank(message = "Campo obrigatorio")
        String estadoCivil,
        @NotNull(message = "Endereço é obrigatório")
        @Valid
        EnderecoDTO endereco) {
}
