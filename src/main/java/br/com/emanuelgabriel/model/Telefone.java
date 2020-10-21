package br.com.emanuelgabriel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.emanuelgabriel.model.enums.TipoTelefone;

@Entity
@Table(name = "telefone")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Enumerated(EnumType.STRING)
	private TipoTelefone tipoTelefone;

	private String numero;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Cliente cliente;

	public Telefone() {

	}

	public Telefone(Long codigo, TipoTelefone tipoTelefone, String numero, Cliente cliente) {
		this.codigo = codigo;
		this.tipoTelefone = tipoTelefone;
		this.numero = numero;
		this.cliente = cliente;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nTelefone: ");
		sb.append("\nCód.: ");
		sb.append(this.codigo);
		sb.append("\nTipo/Telefone: ");
		sb.append(this.tipoTelefone);
		sb.append("\nNúmero: ");
		sb.append(this.numero);
		sb.append("\nCliente: ");
		sb.append(this.getCliente().getNome());

		return sb.toString();

	}

}
