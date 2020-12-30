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

	private EntityManager manager;

	public CategoriaServiceImpl() {
		manager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public Categoria criar(Categoria categoria) {
		manager.getTransaction().begin();
		if (categoria.getCodigo() == null) {
			manager.persist(categoria);
		} else {
			categoria = manager.merge(categoria);
		}
		manager.getTransaction().commit();
		manager.close();
		return categoria;

	}

	@Override
	public List<Categoria> findAll() {
		manager.getTransaction().begin();
		TypedQuery<Categoria> categoriaQuery = manager.createQuery("SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> categorias = categoriaQuery.getResultList();
		manager.getTransaction().commit();
		return categorias;
	}

	@Override
	public Categoria findByCodigo(Long codigo) {
		return this.manager.find(Categoria.class, codigo);
	}

	@Transactional
	@Override
	public void remover(Categoria categoria) {

		try {

			categoria = findByCodigo(categoria.getCodigo());
			manager.remove(categoria);
			manager.flush();

		} catch (PersistenceException e) {
			throw new RegraNegocioException("Categoria não pode ser excluída");
		}
	}

}
