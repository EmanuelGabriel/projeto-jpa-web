package br.com.emanuelgabriel.service;

import java.util.List;

import br.com.emanuelgabriel.model.Livro;
import br.com.emanuelgabriel.repository.LivroRepository;

public class LivroService {

	
	private LivroRepository livroRepository;
	
	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	
	public Livro salvar(Livro livro) {
		return livroRepository.criar(livro);
	}
	
	public List<Livro> getLivros(){
		return livroRepository.findAll();
	}
	
}
