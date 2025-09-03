package io.github.diegof856.neo.desafioTecnico.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;


@Schema(name = "Usuario")
public record UsuarioDTO(
        @Schema(name = "Id")
        UUID id,
        @NotBlank(message = "Campo obrigatorio")
        @Schema(name = "Login")
        String login,
        @NotBlank(message = "Campo obrigatorio")
        @Schema(name = "Senha")
        String senha,
        @Schema(name = "Lista de autorizações")
        List<String> roles) {
}
