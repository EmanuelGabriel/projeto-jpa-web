package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.model.Produto;
import br.com.emanuelgabriel.repository.ProdutoRepository;
import br.com.emanuelgabriel.service.exception.RegraNegocioException;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class ProdutoServiceImpl implements ProdutoRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public ProdutoServiceImpl() {
		this.entityManager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public void criar(Produto produto) {

		entityManager.getTransaction().begin();
		if (produto.getCodigo() == null) {
			entityManager.persist(produto);
		} else {
			entityManager.merge(produto);
		}

		entityManager.getTransaction().commit();

	}

	@Override
	public List<Produto> findAll() {
		entityManager.getTransaction().begin();
		TypedQuery<Produto> produtos = entityManager.createQuery("SELECT p FROM Produto p", Produto.class);
		List<Produto> listaProduto = produtos.getResultList();
		entityManager.getTransaction().commit();
		return listaProduto;
	}

	@Transactional
	@Override
	public void remover(Produto produto) {
		try {

			produto = findByCodigo(produto.getCodigo());
			entityManager.remove(produto);
			entityManager.flush();

		} catch (PersistenceException e) {
			throw new RegraNegocioException("Produto não pode ser excluído");
		}

	}

	@Override
	public Produto findByCodigo(Long codigo) {
		return entityManager.find(Produto.class, codigo);
	}

	@Override
	public Produto findByNome(String nome) {

		try {
			TypedQuery<Produto> typedQuery = entityManager
					.createQuery("SELECT p FROM Produto p WHERE lower(p.nome) LIKE lower(concat('%', :nome, '%'))",
							Produto.class)
					.setParameter("nome", nome);
			Produto cliente = typedQuery.getSingleResult();
			return cliente;

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}

	@Override
	public List<Produto> findPorNome(String nome) {
		return this.entityManager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

}
