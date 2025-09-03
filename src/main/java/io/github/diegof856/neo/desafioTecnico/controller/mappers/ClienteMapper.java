package io.github.diegof856.neo.desafioTecnico.controller.mappers;

import io.github.diegof856.neo.desafioTecnico.controller.dto.ClienteDTO;
import io.github.diegof856.neo.desafioTecnico.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "redirectURI", target = "redirectUri")
    Cliente paraEntidade(ClienteDTO clienteDTO);
    @Mapping(source = "redirectUri", target = "redirectURI")
    ClienteDTO paraDTO(Cliente cliente);
}
