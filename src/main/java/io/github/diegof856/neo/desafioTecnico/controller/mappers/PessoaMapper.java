package io.github.diegof856.neo.desafioTecnico.controller.mappers;

import io.github.diegof856.neo.desafioTecnico.controller.dto.PessoaFisicaDTO;
import io.github.diegof856.neo.desafioTecnico.controller.dto.PessoaFisicaResponseDTO;
import io.github.diegof856.neo.desafioTecnico.model.PessoaFisica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
    @Mapping(target = "endereco", ignore = true)
    PessoaFisica paraEntidade(PessoaFisicaDTO pessoaFisicaDTO);

    PessoaFisicaResponseDTO paraDTO(PessoaFisica pessoaFisica);
}
