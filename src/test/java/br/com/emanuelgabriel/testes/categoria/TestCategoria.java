package br.com.emanuelgabriel.testes.categoria;

import java.util.List;

import org.junit.Test;

import br.com.emanuelgabriel.model.Categoria;
import br.com.emanuelgabriel.repository.CategoriaRepository;
import br.com.emanuelgabriel.service.CategoriaServiceImpl;

public class TestCategoria {

	private static final String CATEGORIA_NAO_ENCONTRADO = "Categoria não encontrado";
	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado";
	private CategoriaRepository categoriaRepository;

	public TestCategoria() {
		this.categoriaRepository = new CategoriaServiceImpl();
	}

	@Test
	public void salvar() {

		Categoria categoria = new Categoria();
		categoria.setNome("Informática");
		categoria.setDescricao("Descrição de Informática");
		categoria.setCategoriaPai(categoria);

		categoriaRepository.criar(categoria);
	}

	@Test
	public void buscarCategoriaPorCodigo() {

		Long codigo = 4L;
		Categoria categoria = categoriaRepository.findByCodigo(codigo);
		if (categoria != null) {

			System.out.println(categoria);
		} else {
			System.out.println(CATEGORIA_NAO_ENCONTRADO);
		}

	}

	@Test
	public void listarCategoria() {

		List<Categoria> categorias = categoriaRepository.findAll();
		if (categorias.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADO);
		}

		for (Categoria cat : categorias) {
			System.out.println(cat.getCodigo());
			System.out.println(cat.getNome());
		}

	}

	@Test
	public void remover() {

		Long codigo = 4L;
		Categoria categoria = categoriaRepository.findByCodigo(codigo);
		if (categoria != null) {
			categoriaRepository.remover(categoria);
		} else {
			System.out.println(CATEGORIA_NAO_ENCONTRADO);
		}

	}

}
