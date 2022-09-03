package com.br.senac.planey.application.exception;

public class RegistroNaoEncontrado extends RuntimeException {
    public RegistroNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
