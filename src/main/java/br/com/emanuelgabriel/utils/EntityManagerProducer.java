package br.com.emanuelgabriel.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//@ApplicationScoped
public class EntityManagerProducer {

	private static final String UNIDADE_PERSISTENCIA = "projeto-jpa-web";
	private EntityManagerFactory emf;

	public EntityManagerProducer() {
		this.emf = Persistence.createEntityManagerFactory(UNIDADE_PERSISTENCIA);
	}

	// @Produces
	// @RequestScoped
	public EntityManager create() {
		return emf.createEntityManager();
	}

	// @Disposes
	public void close(EntityManager em) {
		em.close();
	}

}
