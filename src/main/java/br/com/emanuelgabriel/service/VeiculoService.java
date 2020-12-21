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

	@Override
	public void criar(Veiculo veiculo) {
		this.em.getTransaction().begin();
		if (veiculo.getId() == null) {
			this.em.persist(veiculo);
		} else {
			this.em.merge(veiculo);
		}
		this.em.getTransaction().commit();

	}

	@Override
	public List<Veiculo> findAll() {
		// Usando JPQL - join fetch
		// return em.createQuery("FROM Veiculo v JOIN FETCH v.proprietario",
		// Veiculo.class).getResultList();

		// Usando CRITERIA
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

		Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
		veiculo.fetch("proprietario");
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
	public Veiculo findByNomeFabricante(String fabricante) {

		try {

			TypedQuery<Veiculo> veiculoQuery = this.em.createQuery(
					"SELECT v FROM Veiculo v WHERE lower(v.fabricante) LIKE lower(concat('%', :fabricante, '%'))",
					Veiculo.class).setParameter("fabricante", fabricante);
			Veiculo veiculo = veiculoQuery.getSingleResult();
			return veiculo;

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}

	}

	@Override
	public Long quantidadeVeiculo() {

		try {

			this.em.getTransaction().begin();
			Long qtdVeiculo = (Long) this.em.createQuery("SELECT count(v.id) FROM Veiculo v").getSingleResult();
			this.em.getTransaction().commit();
			return qtdVeiculo;

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}

}
