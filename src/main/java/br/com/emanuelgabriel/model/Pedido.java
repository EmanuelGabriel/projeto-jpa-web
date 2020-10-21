package br.com.emanuelgabriel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.emanuelgabriel.model.enums.FormaPagamento;
import br.com.emanuelgabriel.model.enums.StatusPedido;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;

	@Column(columnDefinition = "text")
	private String observacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega", nullable = false)
	private Date dataEntrega;

	@Column(name = "valor_frete", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorFrete;

	@Column(name = "valor_desconto", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorDesconto;

	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorTotal;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 40)
	private StatusPedido status;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false, length = 40)
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(name = "cliente_codigo", nullable = false)
	private Cliente cliente;

	@Embedded
	private EnderecoEntrega enderecoEntrega;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();

	public Pedido() {

	}

	public Pedido(Long codigo, Date dataCriacao, String observacao, Date dataEntrega, FormaPagamento formaPagamento,
			Cliente cliente, EnderecoEntrega enderecoEntrega) {
		this.codigo = codigo;
		this.dataCriacao = dataCriacao;
		this.observacao = observacao;
		this.dataEntrega = dataEntrega;
		this.valorFrete = BigDecimal.ZERO;
		this.valorDesconto = BigDecimal.ZERO;
		this.valorTotal = BigDecimal.ZERO;
		this.status = StatusPedido.ORCAMENTO;
		this.formaPagamento = formaPagamento;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EnderecoEntrega getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	/**
	 * Método para pegar o subtotal de todos os itens
	 * 
	 * @return o valor do subtotal do Pedido
	 */
	@Transient
	public BigDecimal getValorSubTotal() {
		// subtrai o valor do frete e o valor do desconto
		return this.getValorTotal().subtract(this.getValorFrete()).add(this.getValorDesconto());
	}

	/**
	 * Método responsável em calcular o preço total do Pedido
	 */
	public void recalcularValorTotal() {
		BigDecimal total = BigDecimal.ZERO;

		total = total.add(this.getValorFrete()).subtract(this.getValorDesconto());
		for (ItemPedido item : this.getItens()) {
			if (item.getProduto() != null && item.getProduto().getClass() != null) {
				total = total.add(item.getValorTotal());
			}
		}

		this.setValorTotal(total);
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
		Pedido other = (Pedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", dataCriacao=" + dataCriacao + ", observacao=" + observacao
				+ ", dataEntrega=" + dataEntrega + ", valorFrete=" + valorFrete + ", valorDesconto=" + valorDesconto
				+ ", valorTotal=" + valorTotal + ", status=" + status + ", formaPagamento=" + formaPagamento
				+ ", cliente=" + cliente + ", enderecoEntrega=" + enderecoEntrega + "]";
	}

}
