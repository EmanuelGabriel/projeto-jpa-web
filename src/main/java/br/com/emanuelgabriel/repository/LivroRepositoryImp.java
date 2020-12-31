package br.com.emanuelgabriel.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.emanuelgabriel.model.Livro;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class LivroRepositoryImp implements LivroRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public LivroRepositoryImp() {
		this.em = HibernateUtil.getEntityManager();
	}

	@Override
	public Livro criar(Livro livro) {
		em.getTransaction().begin();
		if (livro.getId() == null) {
			em.persist(livro);
		} else {
			livro = em.merge(livro);
		}
		em.getTransaction().commit();
		em.close();

		return livro;
	}

	@Override
	public List<Livro> findAll() {
		TypedQuery<Livro> livroQuery = em.createQuery("FROM Livro l JOIN FETCH l.autores", Livro.class);
		List<Livro> livros = livroQuery.getResultList();
		return livros;
	}

	@Override
	public Livro findByCodigo(Long codigo) {
		return em.find(Livro.class, codigo);
	}

	@Override
	public void remover(Livro livro) {

		em.getTransaction().begin();

		if (em.contains(livro)) {
			em.remove(livro);
			em.flush();
		} else {
			em.merge(livro);
		}

		em.getTransaction().commit();

	}

	@Override
	public List<Livro> findByTitulo(String titulo) {
		TypedQuery<Livro> livroQuery = em
				.createQuery("SELECT l FROM Livro l WHERE lower(l.titulo) like lower(concat('%', :titulo, '%'))",
						Livro.class)
				.setParameter("titulo", titulo);

		return livroQuery.getResultList();

	}

}
