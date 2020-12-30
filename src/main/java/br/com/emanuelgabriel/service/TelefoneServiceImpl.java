package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.emanuelgabriel.model.Telefone;
import br.com.emanuelgabriel.repository.TelefoneRepository;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class TelefoneServiceImpl implements TelefoneRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public TelefoneServiceImpl() {
		this.manager = HibernateUtil.getEntityManager();
	}

	@Override
	public Telefone criar(Telefone telefone) {
		manager.getTransaction().begin();
		manager.persist(telefone);
		manager.getTransaction().commit();
		manager.close();
		return telefone;
	}

	@Override
	public List<Telefone> findAll() {
		return null;
	}

	@Override
	public Telefone findByCodigo(Long codigo) {
		return null;
	}

	@Override
	public void remover(Telefone telefone) {

	}

}
