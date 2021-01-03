package br.com.emanuelgabriel.testes;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.emanuelgabriel.dao.PostDAO;
import br.com.emanuelgabriel.model.Post;
import br.com.emanuelgabriel.model.PostComentario;

public class TestPost {

	private static Logger log = Logger.getLogger(TestPost.class);

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
		post.addComentario(new PostComentario("Meu post favorito é sempre aqui", LocalDate.now()));
		post.addComentario(new PostComentario("Comentário favorito aqui neste post", LocalDate.now()));
		post.addComentario(new PostComentario("Suave na nave pequeno javeiro", LocalDate.of(2020, 12, 30)));

		this.postDAO.criar(post);
		log.info(post);

	}

	@Test
	public void getPosts() {

		log.info("---Lista de Post---");

		List<Post> posts = this.postDAO.listarTodos();
		if (posts.isEmpty()) {
			log.warn("Nenhum registro encontrado");
		}

		posts.forEach(p -> {
			log.info(p);
		});

	}

}
