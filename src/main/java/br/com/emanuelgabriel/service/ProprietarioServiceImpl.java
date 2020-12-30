package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.model.Proprietario;
import br.com.emanuelgabriel.repository.ProprietarioRepository;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class ProprietarioServiceImpl implements ProprietarioRepository {

	private static final long serialVersionUID = 1L;
	private EntityManager manager;

	public ProprietarioServiceImpl() {
		manager = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public Proprietario criar(Proprietario proprietario) {
		manager.getTransaction().begin();
		if (proprietario.getId() == null) {
			manager.persist(proprietario);
		} else {
			proprietario = manager.merge(proprietario);
		}
		manager.getTransaction().commit();
		manager.close();
		return proprietario;

	}

	@Override
	public List<Proprietario> findAll() {
		return null;
	}

	@Override
	public Proprietario findByCodigo(Long codigo) {
		return manager.find(Proprietario.class, codigo);
	}

	@Override
	public void remover(Proprietario obj) {

	}

}
