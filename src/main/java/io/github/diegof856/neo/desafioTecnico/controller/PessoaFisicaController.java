package io.github.diegof856.neo.desafioTecnico.controller;

import io.github.diegof856.neo.desafioTecnico.controller.dto.PessoaFisicaDTO;
import io.github.diegof856.neo.desafioTecnico.controller.dto.PessoaFisicaResponseDTO;
import io.github.diegof856.neo.desafioTecnico.controller.mappers.PessoaMapper;
import io.github.diegof856.neo.desafioTecnico.model.PessoaFisica;
import io.github.diegof856.neo.desafioTecnico.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
@Tag(name = "Pessoa")
public class PessoaFisicaController implements ControllerGenerico {

    private final PessoaMapper mapper;

    private final PessoaService service;


    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Salvar", description = "Cadastrar nova pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "422",description = "Erro de validação"),
            @ApiResponse(responseCode = "409",description = "Autor já cadastrado"),
    })
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid PessoaFisicaDTO pessoaDTO) {
        PessoaFisica pessoaFisica = this.mapper.paraEntidade(pessoaDTO);
        URI location = gerarHeaderLocation(this.service.salvar(pessoaFisica, pessoaDTO).getId());

        return ResponseEntity.created(location).build();
    }
    @Operation(summary = "Obter Detalhes", description = "Obter detalhes da pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Obter detalhes de uma pessoa por id"),
            @ApiResponse(responseCode = "404",description = "Pessoa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaFisicaResponseDTO> obterDetalhes(@PathVariable("id") String id) {
        return this.service.obterPorId(UUID.fromString(id)).map(pessoa -> {
            PessoaFisicaResponseDTO dto = this.mapper.paraDTO(pessoa);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Atualizar dados", description = "Atualiza dados de uma pessoa por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "409",description = "Pessoa não cadastrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id, @RequestBody @Valid PessoaFisicaDTO pessoaFisicaDTO) {
        Optional<PessoaFisica> pessoaOptional = this.service.obterPorId(UUID.fromString(id));
        if (pessoaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        this.service.atualizar(pessoaOptional.get(),pessoaFisicaDTO);

        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Deletar pessoa", description = "Deleta dados de uma pessoa por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Pessoa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        this.service.deletar(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Pesquisar", description = "Realiza pesquisa de pesssoas por parametros.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Sucesso.")
    })
    @GetMapping
    public ResponseEntity<Page<PessoaFisicaResponseDTO>> pesquisa(
            @RequestParam(value = "cpf", required = false) String cpf,
            @RequestParam(value = "sexo", required = false) String sexo,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "estadoCivil", required = false) String estadoCivil,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina) {
        Page<PessoaFisica> resultado = this.service.pesquisarPorExample(cpf, sexo, nome, estadoCivil,pagina,tamanhoPagina);
        Page<PessoaFisicaResponseDTO> lista = resultado.map(l -> this.mapper.paraDTO(l));
        return ResponseEntity.ok(lista);
    }


}
