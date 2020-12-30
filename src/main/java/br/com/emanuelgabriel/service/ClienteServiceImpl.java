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

	private EntityManager manager;

	public ClienteServiceImpl() {
		this.manager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public Cliente criar(Cliente cliente) {
		manager.getTransaction().begin();
		if (cliente.getCodigo() == null) {
			manager.persist(cliente);
		} else {
			cliente = manager.merge(cliente);
		}
		manager.getTransaction().commit();
		manager.close();
		return cliente;
	}

	public List<Cliente> findAll() {
		manager.getTransaction().begin();
		TypedQuery<Cliente> clientes = manager.createQuery("SELECT c FROM Cliente c", Cliente.class);
		List<Cliente> resultado = clientes.getResultList();
		manager.getTransaction().commit();
		return resultado;
	}

	@Override
	public Cliente findByCodigo(Long codigo) {
		return manager.find(Cliente.class, codigo);
	}

	@Transactional
	public void remover(Cliente cliente) {
		manager.getTransaction().begin();
		manager.remove(cliente);
		manager.flush();
		manager.getTransaction().commit();
	}

	@Override
	public Cliente findByNome(String nome) {
		TypedQuery<Cliente> clienteQuery = manager
				.createQuery("SELECT c FROM Cliente c WHERE lower(c.nome) LIKE lower(concat('%', :nome, '%'))",
						Cliente.class)
				.setParameter("nome", nome);
		Cliente cliente = clienteQuery.getSingleResult();
		return cliente;
	}

	@Override
	public List<Cliente> buscarPorNomes(String nome) {

		TypedQuery<Cliente> clientesQuery = manager
				.createQuery("SELECT c FROM Cliente c WHERE lower(c.nome) LIKE lower(concat('%', :nome, '%'))",
						Cliente.class)
				.setParameter("nome", nome);

		List<Cliente> clientes = clientesQuery.getResultList();

		return clientes;
	}

	@Override
	public List<Cliente> listaMaximaResultado() {

		manager.getTransaction().begin();

		TypedQuery<Cliente> clienteQuery = manager.createQuery("SELECT c FROM Cliente c ORDER BY c.nome", Cliente.class)
				.setMaxResults(5);
		List<Cliente> listaCliente = clienteQuery.getResultList();
		manager.getTransaction().commit();
		return listaCliente;
	}

	@Override
	public List<Cliente> buscarPorNomeAndEmail(String nome, String email) {
		manager.getTransaction().begin();

		TypedQuery<Cliente> clienteQuery = manager
				.createQuery("SELECT c FROM Cliente c WHERE c.nome = :nome OR c.email = :email", Cliente.class)
				.setParameter("nome", nome).setParameter("email", email);
		List<Cliente> clientes = clienteQuery.getResultList();

		manager.getTransaction().commit();
		return clientes;
	}

	@Override
	public Cliente findByEmail(String email) {
		manager.getTransaction().begin();

		try {

			TypedQuery<Cliente> clienteQuery = manager
					.createQuery("SELECT c FROM Cliente c WHERE c.email = :email", Cliente.class)
					.setParameter("email", email);

			Cliente cliente = clienteQuery.getSingleResult();
			manager.getTransaction().commit();
			return cliente;

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}

	}

	@Override
	public List<Cliente> buscarPorTipo(TipoPessoa tipoPessoa) {

		manager.getTransaction().begin();

		TypedQuery<Cliente> buscaPorTipoQuery = manager
				.createQuery("SELECT c FROM Cliente c WHERE c.tipo = :tipo", Cliente.class)
				.setParameter("tipo", tipoPessoa);

		List<Cliente> cliente = buscaPorTipoQuery.getResultList();

		manager.getTransaction().commit();
		return cliente;
	}

	@Override
	public Long quantidadeClientes() {
		try {

			manager.getTransaction().begin();
			Long qtdClientes = (Long) manager.createQuery("SELECT count(*) FROM Cliente c").getSingleResult();
			manager.getTransaction().commit();
			return qtdClientes;

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}

	}

}
