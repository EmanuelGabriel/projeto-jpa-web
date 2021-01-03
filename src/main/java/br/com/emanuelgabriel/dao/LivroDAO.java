package br.com.emanuelgabriel.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import br.com.emanuelgabriel.model.Livro;

public class LivroDAO extends DAO<Livro> {

	DAO<Livro> dao = new DAO<>(Livro.class);

	public LivroDAO() {
		super(Livro.class);
	}

	public List<Livro> findByTitulo(String titulo) {
		TypedQuery<Livro> livroQuery = this.dao.getEntityManager()
				.createQuery("SELECT l FROM Livro l WHERE lower(l.titulo) like lower(concat('%', :titulo, '%'))",
						Livro.class)
				.setParameter("titulo", titulo);

		return livroQuery.getResultList();

	}

	public Livro buscarPorIsbn(String isbn) {
		try {

			TypedQuery<Livro> livroQuery = this.dao.getEntityManager()
					.createQuery("SELECT l FROM Livro l WHERE l.isbn = :isbn", Livro.class)
					.setParameter("isbn", isbn);
			Livro livro = livroQuery.getSingleResult();
			return livro;

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}

	}

	public List<Livro> findByIsbn(String isbn) {
		TypedQuery<Livro> livroQuery = this.dao.getEntityManager()
				.createQuery("SELECT l FROM Livro l WHERE lower(l.isbn) like lower(concat('%', :isbn, '%'))",
						Livro.class)
				.setParameter("isbn", isbn);

		return livroQuery.getResultList();
	}

	@Override
	public List<Livro> listarTodos() {
		TypedQuery<Livro> livroQuery = this.dao.getEntityManager()
				.createQuery("SELECT DISTINCT l FROM Livro l JOIN FETCH l.autores", Livro.class);
		return livroQuery.getResultList();

	}

}
