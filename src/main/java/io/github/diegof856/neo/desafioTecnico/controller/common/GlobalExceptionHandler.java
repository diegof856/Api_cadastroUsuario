package io.github.diegof856.neo.desafioTecnico.controller.common;

import io.github.diegof856.neo.desafioTecnico.controller.dto.ErroCampo;
import io.github.diegof856.neo.desafioTecnico.controller.dto.ErroResposta;
import io.github.diegof856.neo.desafioTecnico.exceptions.CampoInvalidoException;
import io.github.diegof856.neo.desafioTecnico.exceptions.OperacaoNaoPermitidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handlerMetodoDeArgumentosNaoValidosException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrorList = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrorList
                .stream()
                .map(fe -> new ErroCampo(fe.getField(),fe.getDefaultMessage())).collect(Collectors.toList());
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação",listaErros);
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException e) {
        return ErroResposta.respostaPadrao(e.getMessage());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErroResposta handleDataInvalida(HttpMessageNotReadableException e){
        return new ErroResposta(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "Só será aceita datas no formato yyyy-mm-dd",
                List.of()
        );
    }
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handlerCepNaoEncontrado(HttpClientErrorException e){
        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Cep inválido",
                List.of()
        );
    }
    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleCampoInvalidoException(CampoInvalidoException e){

        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value()
                , "Erro validação."
                , List.of(new ErroCampo(e.getCampo(),e.getMessage())));
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErroResposta handleAcessoNegadoException(AccessDeniedException e){
        return new ErroResposta(HttpStatus.FORBIDDEN.value(), "Acesso Negado", List.of());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleErrosNaoTratados(RuntimeException e) {
        return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperada. Entre em contato com a administração", List.of());
    }
}
