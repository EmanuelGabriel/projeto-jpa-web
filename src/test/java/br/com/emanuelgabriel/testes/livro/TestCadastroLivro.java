package br.com.emanuelgabriel.testes.livro;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.emanuelgabriel.dao.AutorDAO;
import br.com.emanuelgabriel.dao.LivroDAO;
import br.com.emanuelgabriel.model.Autor;
import br.com.emanuelgabriel.model.Livro;

public class TestCadastroLivro {

	private static Logger log = Logger.getLogger(TestCadastroLivro.class);

	private LivroDAO livroDAO;
	private AutorDAO autorDAO;

	@Before
	public void init() {
		this.livroDAO = new LivroDAO();
		this.autorDAO = new AutorDAO();
	}

	@Test
	public void salvar() {

		Autor autor = this.autorDAO.buscaPorId(3L);

		Livro livro = new Livro();
		livro.setTitulo("Por que os loucos piram?");
		livro.setIsbn("475679");
		livro.setDataPublicacao(LocalDate.of(2009, 05, 22));
		livro.getAutores().add(autor);

		this.livroDAO.criar(livro);
		log.info(livro);

	}

	@Test
	public void getQuantidadeAutores() {
		int quantidade = this.livroDAO.contaTodos();
		log.info("Quantidade de Livros: " + quantidade);
	}

}
