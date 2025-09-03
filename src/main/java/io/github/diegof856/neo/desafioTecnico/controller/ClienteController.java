package io.github.diegof856.neo.desafioTecnico.controller;

import io.github.diegof856.neo.desafioTecnico.controller.dto.ClienteDTO;
import io.github.diegof856.neo.desafioTecnico.controller.mappers.ClienteMapper;
import io.github.diegof856.neo.desafioTecnico.model.Cliente;
import io.github.diegof856.neo.desafioTecnico.service.ClienteService;
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
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Cliente")
public class ClienteController implements ControllerGenerico{
    private final ClienteService service;
    private final ClienteMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Salvar", description = "Cadastrar um novo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Cadastrado com sucesso")
    })
    public ResponseEntity<Object> salvar(@RequestBody @Valid ClienteDTO clienteDTO) {
       Cliente cliente = this.mapper.paraEntidade(clienteDTO);
        return ResponseEntity.created(gerarHeaderLocation(this.service.salvar(cliente).getId())).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object>atualizar(@PathVariable("id")String id, @RequestBody @Valid ClienteDTO clienteDTO){
        return this.service.obterPorId(UUID.fromString(id)).map(cliente ->{
            Cliente clienteAux = this.mapper.paraEntidade(clienteDTO);
            cliente.setClienteSegredo(clienteAux.getClienteSegredo());
            cliente.setClienteId(clienteAux.getClienteId());
            cliente.setScopo(clienteAux.getScopo());
            cliente.setRedirectUri(clienteAux.getRedirectUri());
            this.service.atualizar(cliente);
            return ResponseEntity.noContent().build();

        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id")String id){
        return this.service.obterPorId(UUID.fromString(id)).map(cliente ->{
            this.service.deletar(cliente);
            return ResponseEntity.noContent().build();
        }).orElseGet(()->ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obterDetalhes(@PathVariable("id")String id){
        return this.service.obterPorId(UUID.fromString(id)).map(cliente -> {
            return ResponseEntity.ok(this.mapper.paraDTO(cliente));
        }).orElseGet(()->ResponseEntity.notFound().build());
    }

}
