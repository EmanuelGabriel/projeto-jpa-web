package br.com.emanuelgabriel.repository;

import java.util.List;

import br.com.emanuelgabriel.model.Cliente;
import br.com.emanuelgabriel.model.enums.TipoPessoa;
import br.com.emanuelgabriel.service.GenericService;

public interface ClienteRepository extends GenericService<Cliente> {

	List<Cliente> buscarPorNomes(String nome);

	Cliente findByNome(String nome);

	Cliente findByEmail(String email);

	List<Cliente> listaMaximaResultado();

	List<Cliente> buscarPorNomeAndEmail(String nome, String email);

	List<Cliente> buscarPorTipo(TipoPessoa tipoPessoa);

}
