package br.com.emanuelgabriel.testes.livro;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.emanuelgabriel.model.Autor;
import br.com.emanuelgabriel.model.Livro;
import br.com.emanuelgabriel.repository.AutorRepositoryImpl;
import br.com.emanuelgabriel.repository.LivroRepositoryImp;

public class TestCadastroLivro {

	Logger log = Logger.getLogger(this.getClass().getName());

	private LivroRepositoryImp livroRepository;
	private AutorRepositoryImpl autorRepository;

	@Before
	public void init() {
		this.livroRepository = new LivroRepositoryImp();
		this.autorRepository = new AutorRepositoryImpl();
	}

	@Test
	public void salvar() {

		Autor autor = this.autorRepository.findByCodigo(3L);

		Livro livro = new Livro();
		livro.setTitulo("Por que os loucos piram?");
		livro.setIsbn("475679");
		livro.setDataPublicacao(LocalDate.of(2009, 05, 22));
		livro.getAutores().add(autor);

		log.info(this.livroRepository.criar(livro));

	}

}
