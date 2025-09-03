package io.github.diegof856.neo.desafioTecnico.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Schema(name = "Cliente")
public record ClienteDTO(
        @Schema(name = "Id")
        UUID id,
        @Schema(name = "Client Id")
        @NotBlank
        String clienteId,
        @Schema(name = "Senha do client")
        @NotBlank
        String clienteSegredo,
        @Schema(name = "URI de redirecionamento")
        @NotBlank
        String redirectURI,
        @Schema(name = "Scopo")
        @NotBlank
        String scopo

) {
}
