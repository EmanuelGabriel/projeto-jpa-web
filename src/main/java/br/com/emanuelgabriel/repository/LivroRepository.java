package br.com.emanuelgabriel.repository;

import java.util.List;

import br.com.emanuelgabriel.model.Livro;
import br.com.emanuelgabriel.service.GenericService;

public interface LivroRepository extends GenericService<Livro> {

	List<Livro> findByTitulo(String nome);

}
