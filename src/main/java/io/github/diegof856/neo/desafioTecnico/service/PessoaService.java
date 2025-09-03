package io.github.diegof856.neo.desafioTecnico.service;

import io.github.diegof856.neo.desafioTecnico.controller.dto.ConsultaCepDTO;
import io.github.diegof856.neo.desafioTecnico.controller.dto.PessoaFisicaDTO;
import io.github.diegof856.neo.desafioTecnico.exceptions.OperacaoNaoPermitidaException;
import io.github.diegof856.neo.desafioTecnico.model.PessoaFisica;
import io.github.diegof856.neo.desafioTecnico.model.enums.EstadoCivil;
import io.github.diegof856.neo.desafioTecnico.model.enums.GeneroPessoa;
import io.github.diegof856.neo.desafioTecnico.respository.PessoaRepositorio;
import io.github.diegof856.neo.desafioTecnico.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepositorio repositorio;
    private final Util util;

    public PessoaFisica salvar(PessoaFisica pessoa, PessoaFisicaDTO pessoaDTO){

        ConsultaCepDTO consulta = this.util.consultaCep(pessoaDTO.endereco().cep());
        pessoa.setEndereco(pessoaDTO.endereco().criarEndereco(consulta));
        pessoa.setIdade(this.calcularIdade(pessoa.getDataNascimento(),LocalDate.now()));
        return this.repositorio.save(pessoa);
    }

    public Optional<PessoaFisica> obterPorId(UUID id) {
        return this.repositorio.findById(id);
    }
    public void atualizar(PessoaFisica pessoaFisica, PessoaFisicaDTO pessoaFisicaDTO){
        if (pessoaFisica.getId() == null) {
            throw new OperacaoNaoPermitidaException("Não é possivel atualizar uma pessoa sem estar cadastrado na base dados");
        }
        if (!pessoaFisica.getEndereco().getCep().equals(pessoaFisicaDTO.endereco().cep())) {
            ConsultaCepDTO consulta = this.util.consultaCep(pessoaFisicaDTO.endereco().cep());
            pessoaFisica.setEndereco(pessoaFisicaDTO.endereco().criarEndereco(consulta));
        }
        this.repositorio.save(criarObjPessoaFisica(pessoaFisica,pessoaFisicaDTO));
    }


    public void deletar(UUID id) {
        this.repositorio.deleteById(id);
    }

    public Page<PessoaFisica> pesquisarPorExample(String cpf, String sexo, String nome, String estadoCivil, Integer pagina, Integer tamanho_pagina) {
        var pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome(nome);
        pessoaFisica.setCpf(cpf);
        if (sexo != null) {
            pessoaFisica.setSexo(GeneroPessoa.valueOf(sexo.toUpperCase()));
        }
        if (estadoCivil != null) {
            pessoaFisica.setEstadoCivil(EstadoCivil.valueOf(estadoCivil.toUpperCase()));
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<PessoaFisica> pessoaFisicaExample = Example.of(pessoaFisica,matcher);
        Pageable pageRequest = PageRequest.of(pagina,tamanho_pagina);
        return this.repositorio.findAll(pessoaFisicaExample,pageRequest);
    }

    private PessoaFisica criarObjPessoaFisica(PessoaFisica pessoaFisica,PessoaFisicaDTO pessoaFisicaDTO){
        pessoaFisica.setNome(pessoaFisicaDTO.nome());
        pessoaFisica.setCpf(pessoaFisicaDTO.cpf());
        pessoaFisica.setDataNascimento(pessoaFisicaDTO.dataNascimento());
        pessoaFisica.setEstadoCivil(EstadoCivil.valueOf(pessoaFisicaDTO.estadoCivil().toUpperCase()));
        pessoaFisica.setSexo(GeneroPessoa.valueOf(pessoaFisicaDTO.sexo().toUpperCase()));
        pessoaFisica.setIdade(this.calcularIdade(pessoaFisicaDTO.dataNascimento(), LocalDate.now()));
        return pessoaFisica;
    }
    private Integer calcularIdade(LocalDate anoDaPessoa, LocalDate anoAtual){
        return Period.between(anoDaPessoa, anoAtual).getYears();
    }
}
