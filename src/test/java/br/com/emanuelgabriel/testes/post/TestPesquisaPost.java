package br.com.emanuelgabriel.testes.post;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.emanuelgabriel.dao.PostDAO;
import br.com.emanuelgabriel.model.Post;

public class TestPesquisaPost {

	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado";

	private static Logger log = Logger.getLogger(TestPesquisaPost.class);

	private PostDAO postDAO;

	@Before
	public void init() {
		this.postDAO = new PostDAO();
	}

	@Test
	public void getPosts() {

		log.info("---Lista de Post---");

		List<Post> posts = this.postDAO.listarTodos();
		if (posts.isEmpty()) {
			log.warn(NENHUM_REGISTRO_ENCONTRADO);
		}

		posts.forEach(p -> log.info(p));

	}

}
