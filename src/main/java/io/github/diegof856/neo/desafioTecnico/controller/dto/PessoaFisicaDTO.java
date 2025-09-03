package io.github.diegof856.neo.desafioTecnico.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
@Schema(name = "Pessoa")
public record PessoaFisicaDTO(
        @Schema(name = "Nome")
        @NotBlank(message = "Campo obrigatorio")
        String nome,
        @Schema(name = "Cpf")
        @CPF(message = "Inválido")
        @NotBlank(message = "Campo CPF é obrigatório")
        String cpf,
        @Schema(name = "Data de nascimento")
        @NotNull(message = "Campo data de nascimento é obrigatório")
        LocalDate dataNascimento,
        @Schema(name = "Genero")
        @NotBlank(message = "Campo obrigatorio")
        String sexo,
        @Schema(name = "Estado civil")
        @NotBlank(message = "Campo obrigatorio")
        String estadoCivil,
        @Schema(name = "Endereco")
        @NotNull(message = "Endereço é obrigatório")
        @Valid
        EnderecoDTO endereco) {
}
