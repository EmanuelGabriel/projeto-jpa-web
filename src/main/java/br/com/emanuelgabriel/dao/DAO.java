package br.com.emanuelgabriel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.utils.HibernateUtil;

public class DAO<T> {

	private final Class<T> classe;
	private EntityManager em;

	public DAO(Class<T> classe) {
		this.em = HibernateUtil.getEntityManager();
		this.classe = classe;
	}

	@Transactional
	public void criar(T tipo) {

		// abre a transação
		em.getTransaction().begin();

		// persiste o objeto
		em.persist(tipo);

		em.getTransaction().commit();
		em.close();
	}

	public List<T> listarTodos() {

		CriteriaQuery<T> queryType = em.getCriteriaBuilder().createQuery(classe);
		queryType.select(queryType.from(classe));

		List<T> lista = em.createQuery(queryType).getResultList();

		em.close();
		return lista;

	}

	@Transactional
	public void atualizar(T tipo) {

		em.getTransaction().begin();

		em.merge(tipo);

		em.getTransaction().commit();
		em.close();
	}

	public T buscaPorId(Long id) {

		T instancia = em.find(classe, id);
		em.close();

		return instancia;
	}

	@Transactional
	public void remove(T tipo) {

		em.getTransaction().begin();

		em.remove(em.merge(tipo));

		em.getTransaction().commit();
		em.close();
	}

	public int contaTodos() {

		long resultado = (Long) em.createQuery("select count(n) from " + classe.getCanonicalName() + " n")
				.getSingleResult();
		em.close();

		return (int) resultado;
	}

	public List<T> listaTodosPaginada(int primeiroResultado, int maximoResultado) {

		CriteriaQuery<T> queryCriteria = em.getCriteriaBuilder().createQuery(classe);
		queryCriteria.select(queryCriteria.from(classe));

		List<T> lista = em.createQuery(queryCriteria).setFirstResult(primeiroResultado).setMaxResults(maximoResultado)
				.getResultList();

		em.close();

		return lista;
	}

}
