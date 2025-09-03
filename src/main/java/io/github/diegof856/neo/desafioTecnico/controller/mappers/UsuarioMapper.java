package io.github.diegof856.neo.desafioTecnico.controller.mappers;

import io.github.diegof856.neo.desafioTecnico.controller.dto.UsuarioDTO;
import io.github.diegof856.neo.desafioTecnico.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario paraEntidade(UsuarioDTO dto);


    UsuarioDTO paraDTO(Usuario usuario);
}
