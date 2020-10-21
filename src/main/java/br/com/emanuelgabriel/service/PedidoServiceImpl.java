package br.com.emanuelgabriel.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.model.Pedido;
import br.com.emanuelgabriel.model.enums.StatusPedido;
import br.com.emanuelgabriel.repository.PedidoRepository;
import br.com.emanuelgabriel.service.exception.RegraNegocioException;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class PedidoServiceImpl implements PedidoRepository {

	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;

	public PedidoServiceImpl() {
		this.entityManager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public void criar(Pedido pedido) {
		entityManager.getTransaction().begin();
		pedido.setDataCriacao(new Date());
		pedido.setStatus(StatusPedido.ORCAMENTO);
		entityManager.merge(pedido);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Pedido> findAll() {
		entityManager.getTransaction().begin();
		TypedQuery<Pedido> pedidos = entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class);
		List<Pedido> lista = pedidos.getResultList();
		entityManager.getTransaction().commit();
		return lista;
	}

	@Transactional
	@Override
	public Pedido update(Pedido obj) {
		return null;
	}

	@Override
	public Pedido findByCodigo(Long codigo) {
		return this.entityManager.find(Pedido.class, codigo);
	}

	@Transactional
	@Override
	public void remover(Pedido pedido) {

		try {

			pedido = findByCodigo(pedido.getCodigo());
			entityManager.remove(pedido);
			entityManager.flush();

		} catch (PersistenceException e) {
			throw new RegraNegocioException("Pedido não pode ser excluído");
		}
	}

}
