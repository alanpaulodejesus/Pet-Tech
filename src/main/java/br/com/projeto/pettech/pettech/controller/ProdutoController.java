package br.com.projeto.pettech.pettech.controller;

import br.com.projeto.pettech.pettech.dto.ProdutoDTO;
import br.com.projeto.pettech.pettech.entities.Produto;
import br.com.projeto.pettech.pettech.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<Collection<ProdutoDTO>> findAll(){
        var produtos = service.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity <ProdutoDTO> findById(@PathVariable UUID id){
        var produto = service.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO produtoDto){
        produtoDto = service.save(produtoDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(produtoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable UUID id, @RequestBody ProdutoDTO produtoDto){
        produtoDto = service.update(id, produtoDto);
        return ResponseEntity.ok(produtoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
