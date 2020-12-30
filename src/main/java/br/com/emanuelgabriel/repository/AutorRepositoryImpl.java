package br.com.emanuelgabriel.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.emanuelgabriel.model.Autor;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class AutorRepositoryImpl implements AutorRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public AutorRepositoryImpl() {
		this.manager = HibernateUtil.getEntityManager();
	}

	@Override
	public Autor criar(Autor autor) {
		manager.getTransaction().begin();

		if (autor.getId() == null) {
			manager.persist(autor);
		} else {
			autor = manager.merge(autor);
		}

		manager.getTransaction().commit();
		manager.close();

		return autor;
	}

	@Override
	public List<Autor> findAll() {
		TypedQuery<Autor> autorQuery = manager.createQuery("FROM Autor a JOIN FETCH a.livros", Autor.class);
		return autorQuery.getResultList();
	}

	@Override
	public Autor findByCodigo(Long codigo) {
		return manager.find(Autor.class, codigo);
	}

	@Override
	public void remover(Autor autor) {
		manager.getTransaction().begin();

		if (manager.contains(autor)) {
			manager.remove(autor);
			manager.flush();
		} else {
			manager.merge(autor);
		}

		manager.getTransaction().commit();
		manager.close();

	}

}
