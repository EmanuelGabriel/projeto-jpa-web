package br.com.emanuelgabriel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnderecoEntrega implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "entrega_logradouro", nullable = false, length = 100)
	private String logradouro;

	@Column(name = "entrega_numero", nullable = false, length = 10)
	private String numero;

	@Column(name = "entrega_complemento", nullable = false, length = 90)
	private String complemento;

	@Column(name = "entrega_cep", nullable = false, length = 12)
	private String cep;

	@Column(name = "entrega_cidade", nullable = false, length = 30)
	private String cidade;

	@Column(name = "entrega_estado", nullable = false, length = 30)
	private String estado;

	public EnderecoEntrega() {
	}

	public EnderecoEntrega(String logradouro, String numero, String complemento, String cep, String cidade,
			String estado) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
