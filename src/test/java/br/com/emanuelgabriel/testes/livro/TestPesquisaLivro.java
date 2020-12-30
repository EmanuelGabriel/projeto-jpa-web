package br.com.emanuelgabriel.testes.livro;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.emanuelgabriel.model.Livro;
import br.com.emanuelgabriel.repository.LivroRepository;
import br.com.emanuelgabriel.repository.LivroRepositoryImp;

public class TestPesquisaLivro {

	Logger log = Logger.getLogger(this.getClass().getName());

	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado...";
	private LivroRepository livroRepository;

	@Before
	public void init() {
		this.livroRepository = new LivroRepositoryImp();
	}

	@Test
	public void findAll() {

		log.info("---Exibe a lista dos Livros cadastrados---");

		List<Livro> livros = this.livroRepository.findAll();
		if (livros.isEmpty()) {
			log.info(NENHUM_REGISTRO_ENCONTRADO);
		}

		livros.forEach(livro -> log.info(livro));

	}

	@Test
	public void buscarLivroPorTitulo() {

		log.info("---Busca os Livros pelo seu Título---");

		String tituloLivro = "VI";
		List<Livro> livros = this.livroRepository.findByTitulo(tituloLivro);
		if (livros.isEmpty()) {
			log.warn(NENHUM_REGISTRO_ENCONTRADO);
		}

		livros.forEach(livro -> log.info(livro));

	}

}
