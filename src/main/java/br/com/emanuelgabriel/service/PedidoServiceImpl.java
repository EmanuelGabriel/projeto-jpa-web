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
	private EntityManager manager;

	public PedidoServiceImpl() {
		this.manager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public Pedido criar(Pedido pedido) {
		manager.getTransaction().begin();
		pedido.setDataCriacao(new Date());
		pedido.setStatus(StatusPedido.ORCAMENTO);
		pedido = manager.merge(pedido);
		manager.getTransaction().commit();
		manager.close();
		return pedido;
	}

	@Override
	public List<Pedido> findAll() {
		manager.getTransaction().begin();
		TypedQuery<Pedido> pedidos = manager.createQuery("SELECT p FROM Pedido p", Pedido.class);
		List<Pedido> lista = pedidos.getResultList();
		manager.getTransaction().commit();
		return lista;
	}

	@Override
	public Pedido findByCodigo(Long codigo) {
		return this.manager.find(Pedido.class, codigo);
	}

	@Transactional
	@Override
	public void remover(Pedido pedido) {

		try {

			pedido = findByCodigo(pedido.getCodigo());
			manager.remove(pedido);
			manager.flush();
			manager.getTransaction().commit();

		} catch (PersistenceException e) {
			throw new RegraNegocioException("Pedido não pode ser excluído");
		}
	}

}
