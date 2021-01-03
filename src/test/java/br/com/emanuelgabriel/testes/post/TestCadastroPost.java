package br.com.emanuelgabriel.testes.post;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.emanuelgabriel.dao.PostDAO;
import br.com.emanuelgabriel.model.Post;

public class TestCadastroPost {

	private static Logger log = Logger.getLogger(TestCadastroPost.class);

	private PostDAO postDAO;

	@Before
	public void init() {
		this.postDAO = new PostDAO();
	}

	@Test
	public void salvar() {

		Post post = new Post();
		post.setTitulo("Como ficar rico sem trabalhar?");
		post.setDescricao("Tudo sobre dinheiro é aqui");

		this.postDAO.criar(post);
		log.info(post);

	}

}
