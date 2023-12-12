package br.com.projeto.pettech.pettech.dto;

import java.util.UUID;

public record ProdutoDTO (
        UUID id,
        String nome,
        String descricao,
        Double preco,
        String urlDaImagem
){
}
