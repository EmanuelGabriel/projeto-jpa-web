package br.com.emanuelgabriel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.utils.HibernateUtil;

public class DAO<T> {

	private final Class<T> classe;
	private EntityManager entityManager;

	public DAO(Class<T> classe) {
		this.entityManager = HibernateUtil.getEntityManager();
		this.classe = classe;
	}

	@Transactional
	public void criar(T tipo) {

		// abre a transação
		entityManager.getTransaction().begin();

		// persiste o objeto
		entityManager.persist(tipo);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<T> listarTodos() {

		CriteriaQuery<T> queryType = entityManager.getCriteriaBuilder().createQuery(classe);
		queryType.select(queryType.from(classe));

		List<T> lista = entityManager.createQuery(queryType).getResultList();

		return lista;

	}

	public T buscaPorId(Long id) {

		T instancia = entityManager.find(classe, id);
		entityManager.close();

		return instancia;
	}

	@Transactional
	public void atualizar(T tipo) {

		entityManager.getTransaction().begin();

		entityManager.merge(tipo);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Transactional
	public void remove(T tipo) {

		entityManager.getTransaction().begin();

		entityManager.remove(entityManager.merge(tipo));

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public int contaTodos() {

		long resultado = (Long) entityManager.createQuery("select count(n) from " + classe.getCanonicalName() + " n")
				.getSingleResult();
		entityManager.close();

		return (int) resultado;
	}

	public List<T> listaTodosPaginada(int primeiroResultado, int maximoResultado) {

		CriteriaQuery<T> queryCriteria = entityManager.getCriteriaBuilder().createQuery(classe);
		queryCriteria.select(queryCriteria.from(classe));

		List<T> lista = entityManager.createQuery(queryCriteria).setFirstResult(primeiroResultado)
				.setMaxResults(maximoResultado).getResultList();

		entityManager.close();

		return lista;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
