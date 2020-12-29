package br.com.emanuelgabriel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.emanuelgabriel.model.enums.TipoCombustivel;

@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 60)
	private String fabricante;

	@Column(nullable = false, length = 60)
	private String modelo;

	@Column(name = "ano_fabricacao", nullable = false)
	private Integer anoFabricacao;

	@Column(name = "ano_modelo", nullable = false)
	private Integer anoModelo;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_combustivel", length = 15, nullable = false)
	private TipoCombustivel tipoCombustivel;

	// @ManyToOne
	// @JoinColumn(name = "id_proprietario")
	// private Proprietario proprietario;

	@ManyToMany
	@JoinTable(name = "veiculo_proprietario", 
	joinColumns = @JoinColumn(name = "veiculo_id"), 
	inverseJoinColumns = @JoinColumn(name = "proprietario_id"))
	private List<Proprietario> proprietarios = new ArrayList<Proprietario>();

	public Veiculo() {
	}

	public Veiculo(Long id, String fabricante, String modelo, Integer anoFabricacao, Integer anoModelo,
			TipoCombustivel tipoCombustivel) {
		this.id = id;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.tipoCombustivel = tipoCombustivel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	public TipoCombustivel getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public List<Proprietario> getProprietarios() {
		return proprietarios;
	}

	public void setProprietarios(List<Proprietario> proprietarios) {
		this.proprietarios = proprietarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nVeículos");
		sb.append("\nCód.:");
		sb.append(id);
		sb.append("\nFabricante: ");
		sb.append(fabricante);
		sb.append("\nModelo: ");
		sb.append(modelo);
		sb.append("\nAno/Fabricação: ");
		sb.append(anoFabricacao);
		sb.append("\nAno/Modelo: ");
		sb.append(anoModelo);
		sb.append("\nTipo de Combustível: ");
		sb.append(tipoCombustivel);
		sb.append("\nProprietário: ");
		sb.append(proprietarios);
		return sb.toString();

	}

}
