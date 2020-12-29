package br.com.emanuelgabriel.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.emanuelgabriel.model.Proprietario;
import br.com.emanuelgabriel.repository.ProprietarioRepository;
import br.com.emanuelgabriel.utils.HibernateUtil;

public class ProprietarioServiceImpl implements ProprietarioRepository {

	private static final long serialVersionUID = 1L;
	private EntityManager em;

	public ProprietarioServiceImpl() {
		em = HibernateUtil.getEntityManager();
	}

	@Transactional
	@Override
	public void criar(Proprietario proprietario) {
		em.getTransaction().begin();
		if (proprietario.getId() == null) {
			em.persist(proprietario);
		} else {
			em.merge(proprietario);
		}
		em.getTransaction().commit();

	}

	@Override
	public List<Proprietario> findAll() {
		return null;
	}

	@Override
	public Proprietario findByCodigo(Long codigo) {
		return em.find(Proprietario.class, codigo);
	}

	@Override
	public void remover(Proprietario obj) {

	}

}
