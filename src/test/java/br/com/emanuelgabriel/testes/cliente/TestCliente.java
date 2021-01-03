package br.com.emanuelgabriel.testes.cliente;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.emanuelgabriel.model.Cliente;
import br.com.emanuelgabriel.model.Endereco;
import br.com.emanuelgabriel.model.Telefone;
import br.com.emanuelgabriel.model.enums.TipoPessoa;
import br.com.emanuelgabriel.model.enums.TipoTelefone;
import br.com.emanuelgabriel.repository.TelefoneRepository;
import br.com.emanuelgabriel.service.ClienteServiceImpl;
import br.com.emanuelgabriel.service.TelefoneServiceImpl;

public class TestCliente {

	private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
	private static final String NENHUM_REGISTRO_ENCONTRADAO = "Nenhum registro encontrado...";
	Logger log = Logger.getLogger(this.getClass().getName());

	private ClienteServiceImpl clienteRepository;
	private TelefoneRepository telefoneRepository;

	@Before
	public void init() {
		this.clienteRepository = new ClienteServiceImpl();
		this.telefoneRepository = new TelefoneServiceImpl();
	}

	@Test
	public void criar() {

		Cliente cliente = new Cliente();
		cliente.setNome("João da Silva Rocha");
		cliente.setEmail("joaorocha@hotmail.com");
		cliente.setTipo(TipoPessoa.FISICA);

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua Leandro Nogueira");
		endereco.setNumero(1008);
		endereco.setCep("640058912");
		endereco.setRua("Rua 24 de Janeiro de 2002");
		endereco.setCidade("São Luis");
		endereco.setEstado("Maranhão");
		endereco.setCliente(cliente);

		cliente.getEnderecos().add(endereco);

		clienteRepository.criar(cliente);
	}

	@Test
	public void listarTodos() {

		log.info("--- Lista os clientes---");

		List<Cliente> clientes = clienteRepository.findAll();
		if (clientes.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADAO);
		}

		clientes.forEach(cli -> {System.out.println(cli);});
		
	}

	@Test
	public void buscarPorId() {

		Long codigo = 1L;
		Cliente cliente = clienteRepository.findByCodigo(codigo);
		if (cliente != null) {
			System.out.println(cliente);
		} else {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}

	}

	@Test
	public void remover() {

		Long codigo = 5L;
		Cliente cliente = clienteRepository.findByCodigo(codigo);
		if (cliente != null) {
			clienteRepository.remover(cliente);
		} else {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}

	}

	@Test
	public void buscarPorNome() {

		String nome = "roc";
		Cliente cliente = clienteRepository.findByNome(nome);
		if (cliente != null) {
			System.out.println(cliente);
		} else {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}
	}

	@Test
	public void buscarPorNomes() {

		String nome = "lito";
		List<Cliente> clientes = clienteRepository.buscarPorNomes(nome);
		if (clientes.isEmpty()) {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

	}

	@Test
	public void atualizar() {

		Cliente cliente = clienteRepository.findByCodigo(5L);
		if (cliente != null) {
			cliente.setNome("Augusto Sandro Pinto");
			cliente.setEmail("augustosandro@yahoo.com.br");
			cliente.setTipo(TipoPessoa.JURIDICA);

			clienteRepository.criar(cliente);
		} else {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}

	}

	@Test
	public void ordenarComLimitePorNome() {

		List<Cliente> clientes = clienteRepository.listaMaximaResultado();
		if (clientes.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADAO);
		}

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

	}

	@Test
	public void ordenarClienteComLimitePorCodigo() {

		List<Cliente> clientes = clienteRepository.listaMaximaResultado();
		if (clientes.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADAO);
		}

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

	}

	@Test
	public void buscarClientePorNomeOuEmail() {

		String nome = "Carlos";
		String email = "carlitobezerra@hotmail.com";

		List<Cliente> clientes = clienteRepository.buscarPorNomeAndEmail(nome, email);
		if (clientes.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADAO);
		}

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

	}

	@Test
	public void buscarClientePorEmail() {

		String email = "carlitobezerra@hotmail.com";

		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente != null) {
			System.out.println(cliente);

		} else {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}

	}

	@Test
	public void buscarClientePorTipo() {

		List<Cliente> clientes = clienteRepository.buscarPorTipo(TipoPessoa.FISICA);
		if (clientes.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADAO);
		}

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

	}

	@Test
	public void cadastrarTelefoneCliente() {

		Cliente cliente = this.clienteRepository.findByCodigo(5L);

		Telefone telefone = new Telefone();
		telefone.setNumero("879889237623");
		telefone.setTipoTelefone(TipoTelefone.CELULAR);
		telefone.setCliente(cliente);

		this.telefoneRepository.criar(telefone);

	}

	@Test
	public void consultarTelefoneCliente() {

		Cliente cliente = this.clienteRepository.findByCodigo(5L);

		if (cliente != null) {
			for (Telefone telefone : cliente.getTelefones()) {
				System.out.println(telefone);

			}
		} else {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}

	}

	@Test
	public void somarQuantidadeClientes() {
		Long qtdCliente = this.clienteRepository.quantidadeClientes();
		System.out.println("Quantidade de clientes cadastrados: " + qtdCliente);
	}

}
