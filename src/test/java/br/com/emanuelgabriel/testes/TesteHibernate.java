package br.com.emanuelgabriel.testes;

import org.junit.Test;

import br.com.emanuelgabriel.utils.HibernateUtil;

public class TesteHibernate {

	
	@Test
	public void testeHibernate() {

		HibernateUtil.getEntityManager();
		

	}

}
