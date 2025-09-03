package io.github.diegof856.neo.desafioTecnico.controller;

import io.github.diegof856.neo.desafioTecnico.controller.dto.UsuarioDTO;
import io.github.diegof856.neo.desafioTecnico.controller.mappers.UsuarioMapper;
import io.github.diegof856.neo.desafioTecnico.model.Usuario;
import io.github.diegof856.neo.desafioTecnico.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuario")
public class UsuarioController implements ControllerGenerico {
    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @Operation(summary = "Salvar", description = "Cadastrar um novo usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "422",description = "Erro de validação"),
            @ApiResponse(responseCode = "409",description = "Usuario já cadastrado"),

    })
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid UsuarioDTO dto){
        var usuario = this.mapper.paraEntidade(dto);
        return ResponseEntity.created(gerarHeaderLocation( this.service.salvar(usuario).getId())).build();
    }
    @Operation(summary = "Obter detalhes", description = "Obtem detalhes de usuario por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Encontrado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Usuario não encontrado"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterDetalhes(@PathVariable("id") String id){
        return this.service.obterPorId(UUID.fromString(id)).map(usuario -> {
            var dto = this.mapper.paraDTO(usuario);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar", description = "Atualiza detalhes de usuario por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Usuario não encontrado"),
            @ApiResponse(responseCode = "409",description = "Usuario já cadastrado")

    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id,@RequestBody @Valid UsuarioDTO usuarioDTO){
        return this.service.obterPorId(UUID.fromString(id))
                .map(usuario -> {
                    Usuario usuarioAux = this.mapper.paraEntidade(usuarioDTO);
                    usuario.setSenha(usuarioAux.getSenha());
                    usuario.setLogin(usuarioAux.getLogin());
                    usuario.setRoles(usuarioAux.getRoles());
                    this.service.atualizar(usuario);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Deletar", description = "Deleta usuario por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Usuario não encontrado")

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id){
        return this.service.obterPorId(UUID.fromString(id)).map(usuario ->{
            this.service.deletar(usuario);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
