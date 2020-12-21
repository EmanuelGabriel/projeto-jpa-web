package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.model.Local;
import br.com.emanuelgabriel.repository.LocalRepository;
import br.com.emanuelgabriel.service.exception.RegraNegocioException;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class LocalServiceImpl implements LocalRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public LocalServiceImpl() {
		this.entityManager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public void criar(Local local) {
		entityManager.getTransaction().begin();
		if (local.getCodigo() == null) {
			entityManager.persist(local);
		} else {
			entityManager.merge(local);
		}
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Local> findAll() {
		entityManager.getTransaction().begin();
		TypedQuery<Local> localQuery = entityManager.createQuery("SELECT l FROM Local l", Local.class);
		List<Local> listaLocais = localQuery.getResultList();
		entityManager.getTransaction().commit();
		return listaLocais;
	}

	@Override
	public Local findByCodigo(Long codigo) {
		return entityManager.find(Local.class, codigo);
	}

	@Transactional
	@Override
	public void remover(Local local) {
		entityManager.getTransaction().begin();
		Local localRemover = findByCodigo(local.getCodigo());
		entityManager.remove(localRemover);
		entityManager.flush();
		entityManager.getTransaction().commit();
	}

	@Override
	public Local findByPredio(String predio) {

		try {
			TypedQuery<Local> localQuery = entityManager
					.createQuery("SELECT l FROM Local l WHERE lower(l.predio) LIKE lower(concat('%', :predio , '%')) ",
							Local.class)
					.setParameter("predio", predio);
			Local local = localQuery.getSingleResult();
			return local;

		} catch (NoResultException | NonUniqueResultException e) {
			throw new RegraNegocioException("Não foi encontrado resultado. " + e.getMessage());
		}
	}

}
