package br.com.emanuelgabriel.testes.veiculo;

import java.util.List;

import org.junit.Test;

import br.com.emanuelgabriel.model.Veiculo;
import br.com.emanuelgabriel.model.enums.TipoCombustivel;
import br.com.emanuelgabriel.repository.VeiculoRepository;
import br.com.emanuelgabriel.service.VeiculoService;

public class TesteVeiculo {

	private static final String VEICULO_NAO_ENCONTRADO = "Veiculo não encontrado";
	private static final String VEICULO_CODIGO_NAO_ENCONTRADO = "Veiculo de Id não encontrado";

	private VeiculoRepository veiculoRepository;

	public TesteVeiculo() {
		this.veiculoRepository = new VeiculoService();
	}

	@Test
	public void getVeiculos() {

		List<Veiculo> veiculos = this.veiculoRepository.findAll();
		if (veiculos.isEmpty()) {
			System.out.println("Nenhum veículo encontrado...");
		}

		veiculos.forEach(v -> System.out.println(v));
	}

	@Test
	public void quantidadeVeiculo() {
		Long qtd = this.veiculoRepository.quantidadeVeiculo();
		System.out.println("Quantidade: " + qtd);

	}

	@Test
	public void buscarPorNomeFabricante() {

		String nomeFabricante = "f";
		Veiculo veiculo = this.veiculoRepository.findByNomeFabricante(nomeFabricante);
		if (veiculo.getFabricante() == null) {
			System.out.println(VEICULO_NAO_ENCONTRADO);
		} else {
			System.out.println(veiculo);
		}
	}

	@Test
	public void buscarPorId() {

		Veiculo veiculo = this.veiculoRepository.findByCodigo(5L);
		if (veiculo.getId() == null) {
			System.out.println(VEICULO_CODIGO_NAO_ENCONTRADO);
		} else {
			System.out.println(veiculo);
		}

	}

	@Test
	public void atualizar() {

		Veiculo veiculo = this.veiculoRepository.findByCodigo(5L);

		if (veiculo != null) {
			veiculo.setFabricante("Chevrolet GM");
			veiculo.setTipoCombustivel(TipoCombustivel.GASOLINA);

			this.veiculoRepository.criar(veiculo);
		} else {
			System.out.println(VEICULO_NAO_ENCONTRADO);
		}

	}

	@Test
	public void remover() {
		Veiculo veiculo = this.veiculoRepository.findByCodigo(5L);
		this.veiculoRepository.remover(veiculo);

	}

}
