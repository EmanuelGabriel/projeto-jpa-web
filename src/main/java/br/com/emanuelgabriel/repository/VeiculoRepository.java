package br.com.emanuelgabriel.repository;

import br.com.emanuelgabriel.model.Veiculo;
import br.com.emanuelgabriel.service.GenericService;

public interface VeiculoRepository extends GenericService<Veiculo> {

	Veiculo findByNomeFabricante(String fabricante);

	Long quantidadeVeiculo();

}
