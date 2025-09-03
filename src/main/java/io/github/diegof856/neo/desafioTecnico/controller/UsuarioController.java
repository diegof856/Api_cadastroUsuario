package io.github.diegof856.neo.desafioTecnico.controller;

import io.github.diegof856.neo.desafioTecnico.controller.dto.UsuarioDTO;
import io.github.diegof856.neo.desafioTecnico.controller.mappers.UsuarioMapper;
import io.github.diegof856.neo.desafioTecnico.model.Usuario;
import io.github.diegof856.neo.desafioTecnico.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements ControllerGenerico {
    private final UsuarioService service;
    private final UsuarioMapper mapper;
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid UsuarioDTO dto){
        var usuario = this.mapper.paraEntidade(dto);
        return ResponseEntity.created(gerarHeaderLocation( this.service.salvar(usuario).getId())).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterDetalhes(@PathVariable("id") String id){
        return this.service.obterPorId(UUID.fromString(id)).map(usuario -> {
            var dto = this.mapper.paraDTO(usuario);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id){
        return this.service.obterPorId(UUID.fromString(id)).map(usuario ->{
            this.service.deletar(usuario);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
