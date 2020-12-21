package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.model.Palestra;
import br.com.emanuelgabriel.repository.PalestraRepository;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class PalestraServiceImpl implements PalestraRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public PalestraServiceImpl() {
		this.entityManager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public void criar(Palestra palestra) {
		entityManager.getTransaction().begin();
		entityManager.merge(palestra);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Palestra> findAll() {
		entityManager.getTransaction().begin();
		TypedQuery<Palestra> typedQuery = entityManager.createQuery("SELECT p FROM Palestra p", Palestra.class);
		List<Palestra> lista = typedQuery.getResultList();
		entityManager.getTransaction().commit();
		return lista;
	}

	@Override
	public Palestra findByCodigo(Long codigo) {
		return entityManager.find(Palestra.class, codigo);
	}

	@Transactional
	@Override
	public void remover(Palestra palestra) {
		entityManager.getTransaction().begin();
		Palestra removerPalestra = findByCodigo(palestra.getCodigo());
		entityManager.remove(removerPalestra);
		entityManager.getTransaction().commit();
	}

}
