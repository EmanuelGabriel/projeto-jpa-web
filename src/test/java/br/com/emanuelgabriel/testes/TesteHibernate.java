package br.com.emanuelgabriel.testes;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.com.emanuelgabriel.utils.HibernateUtil;

public class TesteHibernate {

	Logger log = Logger.getLogger(this.getClass().getName());

	@Test
	public void testeHibernate() {

		HibernateUtil.getEntityManager();

	}

}
