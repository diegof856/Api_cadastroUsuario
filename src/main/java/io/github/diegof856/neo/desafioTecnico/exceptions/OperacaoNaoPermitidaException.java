package io.github.diegof856.neo.desafioTecnico.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException{
    public OperacaoNaoPermitidaException( String mensagem) {
        super(mensagem);
    }
}
