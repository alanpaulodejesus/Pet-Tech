package br.com.projeto.pettech.pettech.controller.exception;

public class ControlerNotFoundException extends RuntimeException{

    public ControlerNotFoundException(String mensagem){
        super(mensagem);
    }

}
