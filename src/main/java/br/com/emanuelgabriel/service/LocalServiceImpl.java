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

	private EntityManager manager;

	public LocalServiceImpl() {
		this.manager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public Local criar(Local local) {
		manager.getTransaction().begin();
		if (local.getCodigo() == null) {
			manager.persist(local);
		} else {
			local = manager.merge(local);
		}
		manager.getTransaction().commit();
		manager.close();
		return local;
	}

	@Override
	public List<Local> findAll() {
		manager.getTransaction().begin();
		TypedQuery<Local> localQuery = manager.createQuery("SELECT l FROM Local l", Local.class);
		List<Local> listaLocais = localQuery.getResultList();
		manager.getTransaction().commit();
		return listaLocais;
	}

	@Override
	public Local findByCodigo(Long codigo) {
		return manager.find(Local.class, codigo);
	}

	@Transactional
	@Override
	public void remover(Local local) {
		manager.getTransaction().begin();
		Local localRemover = findByCodigo(local.getCodigo());
		manager.remove(localRemover);
		manager.flush();
		manager.getTransaction().commit();
	}

	@Override
	public Local findByPredio(String predio) {

		try {
			TypedQuery<Local> localQuery = manager
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
