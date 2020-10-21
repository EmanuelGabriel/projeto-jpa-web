package br.com.emanuelgabriel.repository;

import br.com.emanuelgabriel.model.Local;
import br.com.emanuelgabriel.service.GenericService;

public interface LocalRepository extends GenericService<Local> {

	Local findByPredio(String predio);

}
