package br.com.projeto.pettech.pettech.repository;

import br.com.projeto.pettech.pettech.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository  extends JpaRepository <Produto, UUID> {


}
