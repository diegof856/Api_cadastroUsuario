package io.github.diegof856.neo.desafioTecnico.controller.dto;

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
