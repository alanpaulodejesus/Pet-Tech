package br.com.projeto.pettech.pettech.service;

import br.com.projeto.pettech.pettech.controller.exception.ControlerNotFoundException;
import br.com.projeto.pettech.pettech.dto.ProdutoDTO;
import br.com.projeto.pettech.pettech.entities.Produto;
import br.com.projeto.pettech.pettech.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public Collection<ProdutoDTO> findAll(){
        var produtos = repo.findAll();
        return produtos.stream().map(this::toProdutoDto).collect(Collectors.toList());
    }

    public ProdutoDTO findById(UUID id){
        var produto = repo.findById(id).orElseThrow(()-> new ControlerNotFoundException("Produto não encontrado!"));
        return toProdutoDto(produto);
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO){
        Produto produto = toProduto(produtoDTO);
        produto = repo.save(produto);
        return toProdutoDto(produto);
    }

    public ProdutoDTO update(UUID id, ProdutoDTO produtoDto){
        try {
            Produto buscaProduto = repo.getReferenceById(id);
            buscaProduto.setNome(produtoDto.nome());
            buscaProduto.setDescricao(produtoDto.descricao());
            buscaProduto.setUrlDaImagem(produtoDto.urlDaImagem());
            buscaProduto.setPreco(produtoDto.preco());
            buscaProduto = repo.save(buscaProduto);

            return toProdutoDto(buscaProduto);
        }catch (EntityNotFoundException e){
            throw  new ControlerNotFoundException("Produto não encontrado!");
        }
    }

    public void delete(UUID id){
        repo.deleteById(id);
    }

    private ProdutoDTO toProdutoDto(Produto produto){
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getUrlDaImagem());
    }

    private Produto toProduto (ProdutoDTO produtoDTO){
        return  new Produto(
                produtoDTO.id(),
                produtoDTO.nome(),
                produtoDTO.descricao(),
                produtoDTO.preco(),
                produtoDTO.urlDaImagem()
        );
    }

}
