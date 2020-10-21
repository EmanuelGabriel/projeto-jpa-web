package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.model.Cliente;
import br.com.emanuelgabriel.model.enums.TipoPessoa;
import br.com.emanuelgabriel.repository.ClienteRepository;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class ClienteServiceImpl implements ClienteRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public ClienteServiceImpl() {
		this.entityManager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public void criar(Cliente cliente) {
		entityManager.getTransaction().begin();
		if (cliente.getCodigo() == null) {
			entityManager.persist(cliente);
		} else {
			entityManager.merge(cliente);
		}
		entityManager.getTransaction().commit();
	}

	public List<Cliente> findAll() {
		entityManager.getTransaction().begin();
		TypedQuery<Cliente> clientes = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
		List<Cliente> resultado = clientes.getResultList();
		entityManager.getTransaction().commit();
		return resultado;
	}

	@Override
	public Cliente findByCodigo(Long codigo) {
		Cliente cliente = entityManager.find(Cliente.class, codigo);
		return cliente;
	}

	@Transactional
	public void remover(Cliente cliente) {
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.flush();
		entityManager.getTransaction().commit();
	}

	@Override
	public Cliente findByNome(String nome) {
		TypedQuery<Cliente> clienteQuery = entityManager
				.createQuery("SELECT c FROM Cliente c WHERE lower(c.nome) LIKE lower(concat('%', :nome, '%'))",
						Cliente.class)
				.setParameter("nome", nome);
		Cliente cliente = clienteQuery.getSingleResult();
		return cliente;
	}

	@Override
	public List<Cliente> buscarPorNomes(String nome) {

		TypedQuery<Cliente> clientesQuery = entityManager
				.createQuery("SELECT c FROM Cliente c WHERE lower(c.nome) LIKE lower(concat('%', :nome, '%'))",
						Cliente.class)
				.setParameter("nome", nome);

		List<Cliente> clientes = clientesQuery.getResultList();

		return clientes;
	}

	@Transactional
	@Override
	public Cliente update(Cliente cliente) {
		entityManager.getTransaction().begin();
		Cliente updateCliente = entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		return updateCliente;
	}

	@Override
	public List<Cliente> listaMaximaResultado() {

		entityManager.getTransaction().begin();

		TypedQuery<Cliente> clienteQuery = entityManager
				.createQuery("SELECT c FROM Cliente c ORDER BY c.nome", Cliente.class).setMaxResults(5);
		List<Cliente> listaCliente = clienteQuery.getResultList();
		entityManager.getTransaction().commit();
		return listaCliente;
	}

	@Override
	public List<Cliente> buscarPorNomeAndEmail(String nome, String email) {
		entityManager.getTransaction().begin();

		TypedQuery<Cliente> clienteQuery = entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.nome = :nome OR c.email = :email", Cliente.class)
				.setParameter("nome", nome).setParameter("email", email);
		List<Cliente> clientes = clienteQuery.getResultList();

		entityManager.getTransaction().commit();
		return clientes;
	}

	@Override
	public Cliente findByEmail(String email) {
		entityManager.getTransaction().begin();

		try {

			TypedQuery<Cliente> clienteQuery = entityManager
					.createQuery("SELECT c FROM Cliente c WHERE c.email = :email", Cliente.class)
					.setParameter("email", email);

			Cliente cliente = clienteQuery.getSingleResult();
			entityManager.getTransaction().commit();
			return cliente;

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}

	}

	@Override
	public List<Cliente> buscarPorTipo(TipoPessoa tipoPessoa) {

		entityManager.getTransaction().begin();

		TypedQuery<Cliente> buscaPorTipoQuery = entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.tipo = :tipo", Cliente.class)
				.setParameter("tipo", tipoPessoa);

		List<Cliente> cliente = buscaPorTipoQuery.getResultList();

		entityManager.getTransaction().commit();
		return cliente;
	}

}
