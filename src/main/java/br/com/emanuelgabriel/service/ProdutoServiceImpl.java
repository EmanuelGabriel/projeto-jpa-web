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

	private EntityManager manager;

	public ProdutoServiceImpl() {
		this.manager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public Produto criar(Produto produto) {

		manager.getTransaction().begin();
		if (produto.getCodigo() == null) {
			manager.persist(produto);
		} else {
			produto = manager.merge(produto);
		}

		manager.getTransaction().commit();
		manager.close();
		return produto;

	}

	@Override
	public List<Produto> findAll() {
		manager.getTransaction().begin();
		TypedQuery<Produto> produtos = manager.createQuery("SELECT p FROM Produto p", Produto.class);
		List<Produto> listaProduto = produtos.getResultList();
		manager.getTransaction().commit();
		return listaProduto;
	}

	@Transactional
	@Override
	public void remover(Produto produto) {
		try {

			produto = findByCodigo(produto.getCodigo());
			manager.remove(produto);
			manager.flush();

		} catch (PersistenceException e) {
			throw new RegraNegocioException("Produto não pode ser excluído");
		}

	}

	@Override
	public Produto findByCodigo(Long codigo) {
		return manager.find(Produto.class, codigo);
	}

	@Override
	public Produto findByNome(String nome) {

		try {
			TypedQuery<Produto> typedQuery = manager
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
		return this.manager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

}
