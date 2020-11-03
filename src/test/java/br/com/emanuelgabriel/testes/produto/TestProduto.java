package br.com.emanuelgabriel.testes.produto;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import br.com.emanuelgabriel.model.Categoria;
import br.com.emanuelgabriel.model.Produto;
import br.com.emanuelgabriel.repository.CategoriaRepository;
import br.com.emanuelgabriel.repository.ProdutoRepository;
import br.com.emanuelgabriel.service.CategoriaServiceImpl;
import br.com.emanuelgabriel.service.ProdutoServiceImpl;

public class TestProduto {

	private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
	private static final String NENHUM_REGISTRO_ENCONTRADAO = "Nenhum registro encontrado";
	private ProdutoRepository produtoRepository;
	private CategoriaRepository categoriaRepository;

	public TestProduto() {
		this.produtoRepository = new ProdutoServiceImpl();
		this.categoriaRepository = new CategoriaServiceImpl();
	}

	@Test
	public void salvar() {

		Categoria categoria = categoriaRepository.findByCodigo(4L);

		Produto produto = new Produto();
		produto.setNome("Notebook Dell Ispiron 678 Vostro");
		produto.setQuantidadeEstoque(20);
		produto.setValorUnitario(BigDecimal.valueOf(2789.15));
		produto.setCategoria(categoria);

		produtoRepository.criar(produto);

	}

	@Test
	public void listarProdutos() {

		List<Produto> produtos = produtoRepository.findAll();
		if (produtos.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADAO);
		}

		for (Produto prod : produtos) {
			System.out.println(prod);
		}

	}

	@Test
	public void buscarProdutoPorCodigo() {

		Long codigo = 3L;
		Produto produto = produtoRepository.findByCodigo(codigo);
		if (produto != null) {
			System.out.println(produto);

		} else {
			System.out.println(PRODUTO_NAO_ENCONTRADO);
		}

	}

	@Test
	public void buscarPorNomes() {

		String nomeProduto = "not";
		List<Produto> listaProdutosNome = produtoRepository.findPorNome(nomeProduto);
		if (listaProdutosNome.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADAO);
		}

		for (Produto prod : listaProdutosNome) {
			System.out.println(prod);
		}

	}

}
