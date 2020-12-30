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

	private EntityManager manager;

	public PalestraServiceImpl() {
		this.manager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public Palestra criar(Palestra palestra) {
		manager.getTransaction().begin();
		manager.merge(palestra);
		manager.getTransaction().commit();
		manager.close();
		return palestra;
	}

	@Override
	public List<Palestra> findAll() {
		manager.getTransaction().begin();
		TypedQuery<Palestra> typedQuery = manager.createQuery("SELECT p FROM Palestra p", Palestra.class);
		List<Palestra> lista = typedQuery.getResultList();
		manager.getTransaction().commit();
		return lista;
	}

	@Override
	public Palestra findByCodigo(Long codigo) {
		return manager.find(Palestra.class, codigo);
	}

	@Transactional
	@Override
	public void remover(Palestra palestra) {
		manager.getTransaction().begin();
		Palestra removerPalestra = findByCodigo(palestra.getCodigo());
		manager.remove(removerPalestra);
		manager.getTransaction().commit();
	}

}
