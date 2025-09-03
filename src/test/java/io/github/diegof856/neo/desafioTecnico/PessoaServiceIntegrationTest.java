package io.github.diegof856.neo.desafioTecnico;

import io.github.diegof856.neo.desafioTecnico.controller.dto.PessoaFisicaDTO;
import io.github.diegof856.neo.desafioTecnico.model.PessoaFisica;
import io.github.diegof856.neo.desafioTecnico.service.PessoaService;
import io.github.diegof856.neo.desafioTecnico.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PessoaServiceIntegrationTest {

    @Autowired
    private PessoaService service;

    @Autowired
    private Util util;

    private PessoaFisicaDTO pessoaDTO;

    @BeforeEach
    public void setup() {
        pessoaDTO = new PessoaFisicaDTO(
                "Diego",
                "12169357009",
                LocalDate.of(1995, 2, 22),
                "MASCULINO",
                "SOLTEIRO",
                null
        );
    }

    @Test
    public void testSalvarPessoa() {
        PessoaFisica pessoa = new PessoaFisica();
        PessoaFisica salva = service.salvar(pessoa, pessoaDTO);

        assertThat(salva.getId()).isNotNull();
        assertThat(salva.getNome()).isEqualTo("Diego");
        assertThat(salva.getIdade()).isEqualTo(LocalDate.now().getYear() - 1995);
    }

    @Test
    public void testObterPorId() {
        PessoaFisica pessoa = new PessoaFisica();
        PessoaFisica salva = service.salvar(pessoa, pessoaDTO);

        Optional<PessoaFisica> encontrada = service.obterPorId(salva.getId());
        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getNome()).isEqualTo("Diego");
    }

    @Test
    public void testAtualizarPessoa() {
        PessoaFisica pessoa = new PessoaFisica();
        PessoaFisica salva = service.salvar(pessoa, pessoaDTO);

        PessoaFisicaDTO updateDTO = new PessoaFisicaDTO(
                "Diego Atualizado",
                "12169357009",
                LocalDate.of(1995, 2, 22),
                "MASCULINO",
                "SOLTEIRO",
                null
        );

        service.atualizar(salva, updateDTO);

        Optional<PessoaFisica> atualizada = service.obterPorId(salva.getId());
        assertThat(atualizada).isPresent();
        assertThat(atualizada.get().getNome()).isEqualTo("Diego Atualizado");
    }

    @Test
    public void testDeletarPessoa() {
        PessoaFisica pessoa = new PessoaFisica();
        PessoaFisica salva = service.salvar(pessoa, pessoaDTO);

        service.deletar(salva.getId());

        Optional<PessoaFisica> encontrada = service.obterPorId(salva.getId());
        assertThat(encontrada).isEmpty();
    }

    @Test
    public void testPesquisarPorExample() {
        PessoaFisica pessoa1 = new PessoaFisica();
        PessoaFisica pessoa2 = new PessoaFisica();

        PessoaFisicaDTO dto1 = new PessoaFisicaDTO(
                "Alice",
                "11111111111",
                LocalDate.of(1990, 1, 1),
                "FEMININO",
                "SOLTEIRO",
                null
        );

        PessoaFisicaDTO dto2 = new PessoaFisicaDTO(
                "Bob",
                "22222222222",
                LocalDate.of(1985, 5, 5),
                "MASCULINO",
                "CASADO",
                null
        );

        service.salvar(pessoa1, dto1);
        service.salvar(pessoa2, dto2);

        Page<PessoaFisica> resultado = service.pesquisarPorExample(
                null, "FEMININO", null, null, 0, 10
        );

        assertThat(resultado.getContent()).hasSize(1);
        assertThat(resultado.getContent().get(0).getNome()).isEqualTo("Alice");
    }

    @Test
    public void testAtualizarPessoaSemIdDeveDarErro() {
        PessoaFisica pessoa = new PessoaFisica();
        PessoaFisicaDTO updateDTO = new PessoaFisicaDTO(
                "Diego Atualizado",
                "12169357009",
                LocalDate.of(1995, 2, 22),
                "MASCULINO",
                "SOLTEIRO",
                null
        );

        assertThrows(RuntimeException.class, () -> service.atualizar(pessoa, updateDTO));
    }
}
