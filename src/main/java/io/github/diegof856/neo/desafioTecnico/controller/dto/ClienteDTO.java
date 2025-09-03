package io.github.diegof856.neo.desafioTecnico.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;


public record ClienteDTO(
        UUID id,
        @NotBlank
        String clienteId,
        @NotBlank
        String clienteSegredo,
        @NotBlank
        String redirectURI,
        @NotBlank
        String scopo

) {
}
