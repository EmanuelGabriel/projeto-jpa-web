package br.com.emanuelgabriel.testes.veiculo;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.com.emanuelgabriel.model.Proprietario;
import br.com.emanuelgabriel.model.Veiculo;
import br.com.emanuelgabriel.model.enums.TipoCombustivel;
import br.com.emanuelgabriel.repository.ProprietarioRepository;
import br.com.emanuelgabriel.repository.VeiculoRepository;
import br.com.emanuelgabriel.service.ProprietarioServiceImpl;
import br.com.emanuelgabriel.service.VeiculoService;

public class TesteVeiculo {

	private static final String VEICULO_NAO_ENCONTRADO = "Veiculo não encontrado";
	private static final String VEICULO_CODIGO_NAO_ENCONTRADO = "Veiculo de Id não encontrado";
	Logger log = Logger.getLogger(this.getClass().getName());

	private VeiculoRepository veiculoRepository;
	private ProprietarioRepository proprietarioRepository;

	public TesteVeiculo() {
		this.veiculoRepository = new VeiculoService();
		this.proprietarioRepository = new ProprietarioServiceImpl();
	}

	@Test
	public void salvar() {

		Proprietario proprietario = this.proprietarioRepository.findByCodigo(1L);

		Veiculo veiculo = new Veiculo();
		veiculo.setFabricante("Troller");
		veiculo.setAnoFabricacao(2018);
		veiculo.setAnoModelo(2019);
		veiculo.setModelo("T4");
		veiculo.setTipoCombustivel(TipoCombustivel.GASOLINA);
		veiculo.getProprietarios().add(proprietario);

		this.veiculoRepository.criar(veiculo);

	}

	@Test
	public void getVeiculos() {
		log.info("---Lista de Veículos---");

		List<Veiculo> veiculos = this.veiculoRepository.findAll();
		if (veiculos.isEmpty()) {
			System.out.println("Nenhum veículo encontrado...");
		}

		veiculos.forEach(v -> System.out.println(v));
	}

	@Test
	public void quantidadeVeiculo() {
		log.info("---Quantidade de Veículos---");

		Long qtd = this.veiculoRepository.quantidadeVeiculo();
		System.out.println("Quantidade: " + qtd);

	}

	@Test
	public void buscarPorNomeFabricantes() {

		log.info("---Busca de veículos por nome de Fabricante---");

		String nomeFabricante = "s";
		List<Veiculo> veiculos = this.veiculoRepository.findByNomeFabricante(nomeFabricante);
		if (veiculos.isEmpty()) {
			System.out.println(VEICULO_NAO_ENCONTRADO);
		}

		veiculos.forEach(v -> log.info(v));
	}

	@Test
	public void buscarPorId() {

		Veiculo veiculo = this.veiculoRepository.findByCodigo(3L);
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
