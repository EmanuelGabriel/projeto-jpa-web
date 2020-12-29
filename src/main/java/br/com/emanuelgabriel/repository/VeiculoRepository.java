package br.com.emanuelgabriel.repository;

import java.util.List;

import br.com.emanuelgabriel.model.Veiculo;
import br.com.emanuelgabriel.service.GenericService;

public interface VeiculoRepository extends GenericService<Veiculo> {

	List<Veiculo> findByNomeFabricante(String nomeFabricante);

	Long quantidadeVeiculo();

}
