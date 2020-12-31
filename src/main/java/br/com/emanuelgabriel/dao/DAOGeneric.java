package br.com.emanuelgabriel.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.utils.HibernateUtil;

public abstract class DAOGeneric<E extends Serializable, ID> {

	private EntityManager manager;

	private Class<E> classeEntidade;

	public DAOGeneric() {

	}

	public DAOGeneric(Class<E> classeEntidade) {
		this();
		this.classeEntidade = classeEntidade;
		manager = HibernateUtil.getEntityManager();
	}

	@Transactional
	public void salvar(E entidade) {
		manager.getTransaction().begin();
		manager.persist(entidade);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<E> findAll() {
		manager.getTransaction().begin();

		// entidade.getClass().getCanonicalName()
		List<E> retorno = manager.createQuery("from " + getClasseEntidade().getCanonicalName(), classeEntidade)
				.getResultList();

		manager.getTransaction().commit();
		manager.close();
		return retorno;
	}

	public List<E> findAll(Class<E> entidade) {
		manager.getTransaction().begin();

		List<E> retorno = manager.createQuery("from " + getClasseEntidade().getCanonicalName(), entidade)
				.getResultList();

		manager.getTransaction().commit();
		manager.close();

		return retorno;
	}

	@Transactional
	public E atualizar(E entidade) {
		manager.getTransaction().begin();

		E retorno = manager.merge(entidade);

		manager.getTransaction().commit();
		return retorno;
	}

	public E buscarPorId(ID id) {
		manager.getTransaction().begin();

		E entidade = manager.find(getClasseEntidade(), id);

		manager.getTransaction().commit();
		manager.close();
		return entidade;
	}

	public E buscarPor(ID id) {
		return manager.find(getClasseEntidade(), id);
	}

	@Transactional
	public void remover(E entidade) {
		manager.getTransaction().begin();

		Object id = HibernateUtil.getPrimaryKey(entidade);
		manager.createQuery("DELETE FROM " + entidade.getClass().getCanonicalName() + " WHERE id = " + id)
				.executeUpdate();

		manager.getTransaction().commit();
		manager.close();

	}

	public void flush() {
		manager.flush();
	}

	public Class<E> getClasseEntidade() {
		return classeEntidade;
	}

}
