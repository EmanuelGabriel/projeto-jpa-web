package br.com.emanuelgabriel.testes.local;

import java.util.List;

import org.junit.Test;

import br.com.emanuelgabriel.model.Local;
import br.com.emanuelgabriel.repository.LocalRepository;
import br.com.emanuelgabriel.service.LocalServiceImpl;

public class TestLocal {

	private static final String LOCAL_NAO_ENCONTRADO = "Local não encontrado";
	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado...";

	private LocalRepository localRepository;

	public TestLocal() {
		this.localRepository = new LocalServiceImpl();
	}

	@Test
	public void salvar() {

		Local local = new Local();
		local.setPredio("Búfalo Rego Martins");
		local.setSala("301");
		local.setCapacidade(40);

		this.localRepository.criar(local);

	}

	@Test
	public void buscarTodos() {

		List<Local> locais = this.localRepository.findAll();
		if (locais.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADO);
		}

		locais.forEach(l -> {
			System.out.println(l);
		});

	}

	@Test
	public void buscarPorCodigo() {

		Local local = this.localRepository.findByCodigo(1L);
		if (local != null) {
			System.out.println(local);
		} else {
			System.out.println(LOCAL_NAO_ENCONTRADO);
		}

	}

	@Test
	public void buscarPorNomePredio() {

		String predio = "ei";
		Local local = this.localRepository.findByPredio(predio);
		if (local != null) {
			System.out.println(local);
		} else {
			System.out.println(LOCAL_NAO_ENCONTRADO);
		}
	}

	@Test
	public void atualizar() {

		Local local = this.localRepository.findByCodigo(1L);
		if (local != null) {
			local.setPredio("Edifício Jarbas Castro Ltda");
			local.setSala("7009");
			local.setCapacidade(89);
			this.localRepository.criar(local);
		} else {
			System.out.println(LOCAL_NAO_ENCONTRADO);
		}

	}

	@Test
	public void remover() {

		Long codigo = 2L;
		Local local = this.localRepository.findByCodigo(codigo);
		if (local != null) {
			this.localRepository.remover(local);
		} else {
			System.out.println(LOCAL_NAO_ENCONTRADO);
		}

	}

}
