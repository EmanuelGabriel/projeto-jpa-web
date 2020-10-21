package br.com.emanuelgabriel.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class HibernateUtil {

	private static final String UNIDADE_PERSISTENCIA = "projeto-jpa-web";
	private static EntityManagerFactory factory = null;

	static {
		inicializar();
	}

	private static void inicializar() {
		try {

			if (factory == null) {
				factory = Persistence.createEntityManagerFactory(UNIDADE_PERSISTENCIA);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prover a parte de persistência
	 * 
	 * @return
	 */
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	/**
	 * Retorna a primary key
	 * 
	 * @param object
	 * @return
	 */
	public static Object getPrimaryKey(Object object) {
		return factory.getPersistenceUnitUtil().getIdentifier(object);
	}

}
