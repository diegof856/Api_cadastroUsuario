package io.github.diegof856.neo.desafioTecnico.controller.mappers;

import io.github.diegof856.neo.desafioTecnico.controller.dto.UsuarioDTO;
import io.github.diegof856.neo.desafioTecnico.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(source = "roles", target = "roles")
    Usuario paraEntidade(UsuarioDTO dto);
}
