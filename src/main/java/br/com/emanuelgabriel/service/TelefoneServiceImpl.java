package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.emanuelgabriel.model.Telefone;
import br.com.emanuelgabriel.repository.TelefoneRepository;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class TelefoneServiceImpl implements TelefoneRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public TelefoneServiceImpl() {
		this.entityManager = HibernateUtil.getEntityManager();
	}

	@Override
	public void criar(Telefone telefone) {
		entityManager.getTransaction().begin();
		entityManager.persist(telefone);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Telefone> findAll() {
		return null;
	}

	@Override
	public Telefone update(Telefone telefone) {
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
