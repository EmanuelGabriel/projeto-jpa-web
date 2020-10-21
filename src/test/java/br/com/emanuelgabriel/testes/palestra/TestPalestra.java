package br.com.emanuelgabriel.testes.palestra;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.emanuelgabriel.model.Local;
import br.com.emanuelgabriel.model.Palestra;
import br.com.emanuelgabriel.repository.LocalRepository;
import br.com.emanuelgabriel.repository.PalestraRepository;
import br.com.emanuelgabriel.service.LocalServiceImpl;
import br.com.emanuelgabriel.service.PalestraServiceImpl;

public class TestPalestra {

	private static final String PALESTRA_NAO_ENCONTRADA = "Palestra não encontrada";
	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado...";

	private LocalRepository localRepository;
	private PalestraRepository palestraRepository;

	public TestPalestra() {
		this.palestraRepository = new PalestraServiceImpl();
		this.localRepository = new LocalServiceImpl();
	}

	@Test
	public void salvar() {

		Local local = this.localRepository.findByCodigo(3L);

		Palestra palestra = new Palestra();
		palestra.setTitulo("Frases do Tempo");
		palestra.setLocal(local);
		palestra.setDataHora(LocalDateTime.of(2020, 11, 12, 15, 00));
		palestra.setDuracao(1);

		this.palestraRepository.criar(palestra);

	}

	@Test
	public void buscarTodos() {

		List<Palestra> palestras = this.palestraRepository.findAll();
		if (palestras.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADO);
		}

		palestras.forEach(p -> {
			System.out.println(p + "\n");
		});

	}

	@Test
	public void buscarPorCodigo() {

		Palestra palestra = this.palestraRepository.findByCodigo(3L);
		if (palestra != null) {
			System.out.println(palestra);
		} else {
			System.out.println(PALESTRA_NAO_ENCONTRADA);
		}

	}

	@Test
	public void remover() {

		Palestra palestra = this.palestraRepository.findByCodigo(1L);
		if (palestra != null) {
			this.palestraRepository.remover(palestra);
		} else {
			System.out.println(PALESTRA_NAO_ENCONTRADA);
		}

	}

}
