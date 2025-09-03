package io.github.diegof856.neo.desafioTecnico.controller;

import io.github.diegof856.neo.desafioTecnico.controller.dto.PessoaFisicaDTO;
import io.github.diegof856.neo.desafioTecnico.controller.dto.PessoaFisicaResponseDTO;
import io.github.diegof856.neo.desafioTecnico.controller.mappers.PessoaMapper;
import io.github.diegof856.neo.desafioTecnico.model.PessoaFisica;
import io.github.diegof856.neo.desafioTecnico.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaFisicaController implements ControllerGenerico {

    private final PessoaMapper mapper;

    private final PessoaService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid PessoaFisicaDTO pessoaDTO) {
        PessoaFisica pessoaFisica = this.mapper.paraEntidade(pessoaDTO);
        URI location = gerarHeaderLocation(this.service.salvar(pessoaFisica, pessoaDTO).getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaFisicaResponseDTO> obterDetalhes(@PathVariable("id") String id) {
        return this.service.obterPorId(UUID.fromString(id)).map(pessoa -> {
            PessoaFisicaResponseDTO dto = this.mapper.paraDTO(pessoa);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id, @RequestBody @Valid PessoaFisicaDTO pessoaFisicaDTO) {
        Optional<PessoaFisica> pessoaOptional = this.service.obterPorId(UUID.fromString(id));
        if (pessoaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        this.service.atualizar(pessoaOptional.get(),pessoaFisicaDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        this.service.deletar(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

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
