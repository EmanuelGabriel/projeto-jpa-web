package br.com.emanuelgabriel.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.emanuelgabriel.model.Autor;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class AutorRepositoryImpl implements AutorRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public AutorRepositoryImpl() {
		this.em = HibernateUtil.getEntityManager();
	}

	@Override
	public Autor criar(Autor autor) {
		em.getTransaction().begin();

		if (autor.getId() == null) {
			em.persist(autor);
		} else {
			autor = em.merge(autor);
		}

		em.getTransaction().commit();
		em.close();

		return autor;
	}

	@Override
	public List<Autor> findAll() {
		TypedQuery<Autor> autorQuery = em.createQuery("FROM Autor a JOIN FETCH a.livros", Autor.class);
		return autorQuery.getResultList();
	}

	@Override
	public Autor findByCodigo(Long codigo) {
		return em.find(Autor.class, codigo);
	}

	@Override
	public void remover(Autor autor) {
		em.getTransaction().begin();

		if (em.contains(autor)) {
			em.remove(autor);
			em.flush();
		} else {
			em.merge(autor);
		}

		em.getTransaction().commit();
		em.close();

	}

}
