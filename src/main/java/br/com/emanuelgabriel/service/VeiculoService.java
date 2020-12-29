package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.model.Veiculo;
import br.com.emanuelgabriel.repository.VeiculoRepository;
import br.com.emanuelgabriel.service.exception.RegraNegocioException;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class VeiculoService implements VeiculoRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public VeiculoService() {
		this.em = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public void criar(Veiculo veiculo) {
		em.getTransaction().begin();

		if (veiculo.getId() == null) {
			this.em.persist(veiculo);
		} else {
			em.merge(veiculo);
		}

		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Veiculo> findAll() {
		// Usando JPQL - join fetch
		//return em.createQuery("FROM Veiculo v JOIN FETCH v.proprietarios",
		//Veiculo.class).getResultList();

		// Usando CRITERIA
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

		Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
		veiculo.fetch("proprietarios");
		criteriaQuery.select(veiculo);

		// criar a query
		TypedQuery<Veiculo> query = this.em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public Veiculo findByCodigo(Long codigo) {
		return this.em.find(Veiculo.class, codigo);
	}

	@Transactional
	@Override
	public void remover(Veiculo veiculo) {

		try {

			veiculo = findByCodigo(veiculo.getId());
			this.em.remove(veiculo);
			this.em.flush();

		} catch (PersistenceException e) {
			throw new RegraNegocioException("Veiculo não pode ser removido");
		}

	}

	@Override
	public List<Veiculo> findByNomeFabricante(String nomeFabricante) {
		try {

			// JOIN FETCH v.proprietarios
			TypedQuery<Veiculo> veiculoQuery = this.em.createQuery(
					"SELECT v FROM Veiculo v JOIN FETCH v.proprietarios WHERE lower(v.fabricante) like lower(concat('%', :fabricante, '%'))",
					Veiculo.class).setParameter("fabricante", nomeFabricante);
			List<Veiculo> veiculos = veiculoQuery.getResultList();
			return veiculos;

		} catch (PersistenceException e) {
			return null;
		}

	}

	@Override
	public Long quantidadeVeiculo() {

		try {

			Long qtdVeiculo = (Long) this.em.createQuery("SELECT count(v.id) FROM Veiculo v").getSingleResult();
			return qtdVeiculo;

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}

}
