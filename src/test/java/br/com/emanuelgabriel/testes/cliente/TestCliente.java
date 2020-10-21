package br.com.emanuelgabriel.testes.cliente;

import java.util.List;

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

	private ClienteServiceImpl clienteRepository;
	private TelefoneRepository telefoneRepository;

	public TestCliente() {
		this.clienteRepository = new ClienteServiceImpl();
		this.telefoneRepository = new TelefoneServiceImpl();
	}

	@Test
	public void criar() {

		Cliente cliente = new Cliente();
		cliente.setNome("Carlos Eduardo Martins");
		cliente.setEmail("carlitoedu@gmail.com");
		cliente.setTipo(TipoPessoa.FISICA);

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua das Hortas Cesar Leitão");
		endereco.setNumero(9882);
		endereco.setCep("6400467");
		endereco.setRua("Rua Treze de Maio");
		endereco.setCidade("Portos");
		endereco.setEstado("Acre");
		endereco.setCliente(cliente);

		cliente.getEnderecos().add(endereco);

		clienteRepository.criar(cliente);
	}

	@Test
	public void listarTodos() {

		List<Cliente> clientes = clienteRepository.findAll();
		if (clientes.isEmpty()) {
			System.out.println(NENHUM_REGISTRO_ENCONTRADAO);
		}

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
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

		Long codigo = 27L;
		Cliente cliente = clienteRepository.findByCodigo(codigo);
		if (cliente != null) {
			clienteRepository.remover(cliente);
		} else {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}

	}

	@Test
	public void buscarPorNome() {

		String nome = "dro";
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

		Cliente cliente = clienteRepository.findByCodigo(1L);
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

		String nome = "Carlos do Nascimento Alves";
		String email = "c@hotmail.com.br";

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

		String email = "carlosnascimento@gmail.com";

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

		Cliente cliente = this.clienteRepository.findByCodigo(2L);

		Telefone telefone = new Telefone();
		telefone.setNumero("8932895612");
		telefone.setTipoTelefone(TipoTelefone.RESIDENCIAL);
		telefone.setCliente(cliente);

		this.telefoneRepository.criar(telefone);

	}

	@Test
	public void consultarTelefoneCliente() {

		Cliente cliente = this.clienteRepository.findByCodigo(2L);

		if (cliente != null) {
			for (Telefone telefone : cliente.getTelefones()) {
				System.out.println(telefone);

			}
		} else {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}

	}

}
