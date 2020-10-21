package br.com.emanuelgabriel.repository;

import java.util.List;

import br.com.emanuelgabriel.model.Produto;
import br.com.emanuelgabriel.service.GenericService;

public interface ProdutoRepository extends GenericService<Produto> {

	List<Produto> findPorNome(String nome);

	Produto findByNome(String nome);

}
