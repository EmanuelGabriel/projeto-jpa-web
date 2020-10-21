package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.model.Categoria;
import br.com.emanuelgabriel.repository.CategoriaRepository;
import br.com.emanuelgabriel.service.exception.RegraNegocioException;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class CategoriaServiceImpl implements CategoriaRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public CategoriaServiceImpl() {
		this.entityManager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public void criar(Categoria categoria) {

		entityManager.getTransaction().begin();
		entityManager.merge(categoria);
		entityManager.getTransaction().commit();

	}

	@Override
	public List<Categoria> findAll() {
		entityManager.getTransaction().begin();
		TypedQuery<Categoria> categoriaQuery = entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> lista = categoriaQuery.getResultList();
		entityManager.getTransaction().commit();
		return lista;
	}

	@Override
	public Categoria update(Categoria categoria) {
		return null;
	}

	@Override
	public Categoria findByCodigo(Long codigo) {
		return this.entityManager.find(Categoria.class, codigo);
	}

	@Override
	public void remover(Categoria categoria) {

		try {

			categoria = findByCodigo(categoria.getCodigo());
			entityManager.remove(categoria);
			entityManager.flush();

		} catch (PersistenceException e) {
			throw new RegraNegocioException("Categoria não pode ser excluído");
		}
	}

}
