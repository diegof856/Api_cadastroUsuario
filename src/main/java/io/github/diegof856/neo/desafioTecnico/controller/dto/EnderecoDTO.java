package io.github.diegof856.neo.desafioTecnico.controller.dto;

import io.github.diegof856.neo.desafioTecnico.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
@Schema(name = "Endereco")
public record EnderecoDTO(
        @Schema(name = "Cep")
        @NotBlank
        String cep,
        @Schema(name = "numero")
        @NotBlank
        String numero,
        @Schema(name = "complemento")
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
