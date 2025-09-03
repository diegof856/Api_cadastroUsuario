package io.github.diegof856.neo.desafioTecnico.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Entidade de consulta cep")
public record ConsultaCepDTO(
        String cep,
        String logradouro,
        String complemento,
        String unidade,
        String bairro,
        String localidade,
        String uf,
        String estado,
        String regiao,
        String gia,
        String ddd,
        String siafi
) {
}
