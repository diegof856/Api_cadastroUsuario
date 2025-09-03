package io.github.diegof856.neo.desafioTecnico.controller.dto;

import io.github.diegof856.neo.desafioTecnico.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank
        String cep,
        @NotBlank
        String numero,
        String complemento) {

        public Endereco criarEndereco(ConsultaCepDTO consultaCepDTO){
                Endereco endereco = new Endereco();
                endereco.setRua(consultaCepDTO.logradouro());
                endereco.setBairro(consultaCepDTO.bairro());
                endereco.setCep(consultaCepDTO.cep());
                endereco.setCidade(consultaCepDTO.localidade());
                endereco.setEstado(consultaCepDTO.estado());
                endereco.setComplemento(complemento);
                endereco.setNumero(numero);
                return endereco;

        }
}
