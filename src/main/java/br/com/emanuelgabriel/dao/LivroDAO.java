package br.com.emanuelgabriel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.emanuelgabriel.model.Livro;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class LivroDAO extends DAO<Livro> {

	private EntityManager em;

	public LivroDAO() {
		super(Livro.class);
		this.em = HibernateUtil.getEntityManager();
	}

	public List<Livro> findByTitulo(String titulo) {
		TypedQuery<Livro> livroQuery = em
				.createQuery("SELECT l FROM Livro l WHERE lower(l.titulo) like lower(concat('%', :titulo, '%'))",
						Livro.class)
				.setParameter("titulo", titulo);

		return livroQuery.getResultList();

	}

	@Override
	public List<Livro> listarTodos() {
		TypedQuery<Livro> livroQuery = em.createQuery("FROM Livro l JOIN FETCH l.autores", Livro.class);
		List<Livro> livros = livroQuery.getResultList();
		return livros;
	}

}
