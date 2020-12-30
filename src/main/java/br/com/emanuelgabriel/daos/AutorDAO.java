package br.com.emanuelgabriel.daos;

import br.com.emanuelgabriel.model.Autor;

public class AutorDAO extends DAOGeneric<Autor, Long> {

	public AutorDAO() {
		super(Autor.class);
	}

}
