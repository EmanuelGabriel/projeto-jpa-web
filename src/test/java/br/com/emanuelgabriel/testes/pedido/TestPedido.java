package br.com.emanuelgabriel.testes.pedido;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.emanuelgabriel.model.Cliente;
import br.com.emanuelgabriel.model.EnderecoEntrega;
import br.com.emanuelgabriel.model.ItemPedido;
import br.com.emanuelgabriel.model.Pedido;
import br.com.emanuelgabriel.model.Produto;
import br.com.emanuelgabriel.model.enums.FormaPagamento;
import br.com.emanuelgabriel.repository.ClienteRepository;
import br.com.emanuelgabriel.repository.PedidoRepository;
import br.com.emanuelgabriel.repository.ProdutoRepository;
import br.com.emanuelgabriel.service.ClienteServiceImpl;
import br.com.emanuelgabriel.service.PedidoServiceImpl;
import br.com.emanuelgabriel.service.ProdutoServiceImpl;

public class TestPedido {

	private static final String PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado";
	private static final String NENHUM_REGISTRO_ENCONTRADAO = "Nenhum registro encontrado";
	private PedidoRepository pedidoRepository;
	private ClienteRepository clienteRepository;
	private ProdutoRepository produtoRepository;

	public TestPedido() {
		this.pedidoRepository = new PedidoServiceImpl();
		this.clienteRepository = new ClienteServiceImpl();
		this.produtoRepository = new ProdutoServiceImpl();
	}

	@Test
	public void salvar() {

		Cliente cliente = clienteRepository.findByCodigo(3L);
		Produto produto = produtoRepository.findByCodigo(1L);
		// dar baixar no estoque
		produto.baixarEstoque(3);

		Pedido pedido = new Pedido();
		pedido.setFormaPagamento(FormaPagamento.CARTAO_DEBITO);
		pedido.setObservacao("sem açucar");
		pedido.setEnderecoEntrega(new EnderecoEntrega("Ao lado do hospital São Marcos", "7726",
				"Av. Júlio Cesar Coutinho", "6528992", "Teresina", "Piauí"));
		pedido.setDataEntrega(Date.from(Instant.now()));
		pedido.setValorDesconto(BigDecimal.ZERO);
		pedido.setValorFrete(BigDecimal.ZERO);
		pedido.setValorTotal(pedido.getValorTotal());

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(3);
		itemPedido.setValorUnitario(produto.getValorUnitario());

		pedido.setItens(Arrays.asList(itemPedido));
		pedido.setCliente(cliente);
		// calcular o valor total do pedido ou venda
		pedido.recalcularValorTotal();

		pedidoRepository.criar(pedido);

	}

	@Test
	public void getPedidos() {

		List<Pedido> pedidos = pedidoRepository.findAll();
		if (pedidos.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADAO);
		}

		for (Pedido ped : pedidos) {
			System.out.println("=======Nº DO PEDIDO=======");
			System.out.println("Cód: " + ped.getCodigo());
			System.out.println("Data/Criação: " + ped.getDataCriacao());
			System.out.println("Data/Entrega: " + ped.getDataEntrega());
			System.out.println("Valor/Frete: " + ped.getValorFrete());
			System.out.println("Valor/Desconto: " + ped.getValorDesconto());
			System.out.println("SubTotal: " + ped.getValorSubTotal());
			System.out.println("Valor Total: " + ped.getValorTotal());

			// pegar os itens do Pedido
			for (ItemPedido itensPedido : ped.getItens()) {
				System.out.println("=======ITENS DO PEDIDO=======");
				System.out.println("Cód: " + itensPedido.getCodigo());
				System.out.println("Valor Unit: " + itensPedido.getValorUnitario());
				System.out.println("Nome/Produto: " + itensPedido.getProduto().getNome());
				System.out.println("Qtd: " + itensPedido.getQuantidade());
				System.out.println("SubTotal: " + itensPedido.getValorTotal());

			}

		}

	}

	@Test
	public void buscarPedidoPorCodigo() {

		Long codigo = 10L;
		Pedido pedido = pedidoRepository.findByCodigo(codigo);
		if (pedido != null) {
			System.out.println("Cód. " + pedido.getCodigo());
			System.out.println("Cliente: " + pedido.getCliente().getNome());
			System.out.println("Data/Entrega: " + pedido.getDataEntrega());
			System.out.println("Forma/Pagamento: " + pedido.getFormaPagamento());
			System.out.println("Status: " + pedido.getStatus());
			System.out.println("Subtotal: " + pedido.getValorSubTotal());
			System.out.println("Valor Total: " + pedido.getValorTotal());

		} else {
			System.out.println(PEDIDO_NAO_ENCONTRADO);
		}

	}

}
