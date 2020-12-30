package br.com.emanuelgabriel.testes;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.com.emanuelgabriel.model.Livro;
import br.com.emanuelgabriel.repository.LivroRepositoryImp;

public class TestLivro {

	Logger log = Logger.getLogger(this.getClass().getName());
	private static final String LIVRO_NAO_ENCONTRADO = "Livro não encontrado";
	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado...";

	private LivroRepositoryImp livroRepository;

	public TestLivro() {
		this.livroRepository = new LivroRepositoryImp();
	}

	@Test
	public void salvar() {

		log.info("--- Adicionar Livro ---");

		Livro livro = new Livro();
		livro.setTitulo("Por que os loucos piram?");
		livro.setDataPublicacao(LocalDate.of(2016, 03, 20));
		// livro.getAutores().add(autor);
		livro.setIsbn("09038283");

		log.info(livro);

	}

	@Test
	public void findAll() {

		log.info("--- Lista todos os Livros ---");

		List<Livro> livros = this.livroRepository.findAll();
		if (livros.isEmpty()) {
			log.info(NENHUM_REGISTRO_ENCONTRADO);
		}

		livros.forEach(livro -> log.info(livro));

	}

	@Test
	public void buscarLivroPorTitulo() {

		log.info("---Busca de Livros com base em seu Título---");

		String tituloLivro = "ta";
		List<Livro> livros = this.livroRepository.findByTitulo(tituloLivro);
		if (livros.isEmpty()) {
			log.warn(NENHUM_REGISTRO_ENCONTRADO);
		}

		livros.forEach(System.out::println);

	}

}
