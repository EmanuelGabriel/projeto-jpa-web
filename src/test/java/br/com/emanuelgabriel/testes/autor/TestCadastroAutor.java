package br.com.emanuelgabriel.testes.autor;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.emanuelgabriel.dao.DAO;
import br.com.emanuelgabriel.model.Autor;

public class TestCadastroAutor {

	private static Logger log = Logger.getLogger(TestCadastroAutor.class.getName());

	private DAO<Autor> autorDAO;

	@Before
	public void init() {
		this.autorDAO = new DAO<Autor>(Autor.class);
	}

	@Test
	public void listarTodos() {

		List<Autor> lista = this.autorDAO.listarTodos();
		if (lista.isEmpty()) {
			log.error("Nenhum registro encontrado");
		}

		lista.forEach(autor -> log.info(autor));

	}

	@Test
	public void getQuantidadeAutores() {
		int quantidade = this.autorDAO.contaTodos();
		log.info("Quantidade de Autores: " + quantidade);
	}

	@Test
	public void buscarPorId() {

		Autor autor = this.autorDAO.buscaPorId(5L);
		if (autor.getId() == null) {
			log.info("Autor de código não encontrado");
		}

		log.info(autor);

	}

}
