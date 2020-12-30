package br.com.emanuelgabriel.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.emanuelgabriel.model.Livro;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class LivroRepositoryImp implements LivroRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public LivroRepositoryImp() {
		this.manager = HibernateUtil.getEntityManager();
	}

	@Override
	public Livro criar(Livro livro) {
		manager.getTransaction().begin();
		if (livro.getId() == null) {
			manager.persist(livro);
		} else {
			livro = manager.merge(livro);
		}
		manager.getTransaction().commit();
		manager.close();

		return livro;
	}

	@Override
	public List<Livro> findAll() {
		TypedQuery<Livro> livroQuery = manager.createQuery("FROM Livro l JOIN FETCH l.autores", Livro.class);
		List<Livro> livros = livroQuery.getResultList();
		return livros;
	}

	@Override
	public Livro findByCodigo(Long codigo) {
		return manager.find(Livro.class, codigo);
	}

	@Override
	public void remover(Livro livro) {

		manager.getTransaction().begin();

		if (manager.contains(livro)) {
			manager.remove(livro);
			manager.flush();
		} else {
			manager.merge(livro);
		}

		manager.getTransaction().commit();

	}

	@Override
	public List<Livro> findByTitulo(String titulo) {
		TypedQuery<Livro> livroQuery = manager
				.createQuery("SELECT l FROM Livro l WHERE lower(l.titulo) like lower(concat('%', :titulo, '%'))",
						Livro.class)
				.setParameter("titulo", titulo);

		return livroQuery.getResultList();

	}

}
