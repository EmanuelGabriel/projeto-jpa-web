package br.com.emanuelgabriel.testes.livro;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.emanuelgabriel.dao.LivroDAO;
import br.com.emanuelgabriel.model.Livro;

public class TestPesquisaLivro {

	private static Logger log = Logger.getLogger(TestPesquisaLivro.class.getName());

	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado";
	private LivroDAO livroDAO;

	@Before
	public void init() {
		this.livroDAO = new LivroDAO();
	}

	@Test
	public void findAll() {

		log.info("---Exibe a lista dos Livros cadastrados---");

		List<Livro> livros = this.livroDAO.listarTodos();
		if (livros.isEmpty()) {
			log.info(NENHUM_REGISTRO_ENCONTRADO);
		}

		livros.forEach(livro -> log.info(livro));

	}

	@Test
	public void buscarLivroPorTitulo() {

		log.info("---Busca os Livros pelo seu Título---");

		String tituloLivro = "dois";
		List<Livro> livros = this.livroDAO.findByTitulo(tituloLivro);
		if (livros.isEmpty()) {
			log.warn(NENHUM_REGISTRO_ENCONTRADO);
		}

		livros.forEach(livro -> log.info(livro));

	}

}
