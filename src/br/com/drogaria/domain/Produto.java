package br.com.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_produtos")
@NamedQueries({ @NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto "),
		@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE codigo = :codigo ") })
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "pro_codigo")
	private Long codigo;

	@NotEmpty(message = "Nome do produto deve ser informado")
	@Size(min = 1, max = 50, message = "Nome do produto precisa ter entre 1 e 50 Caracteres")
	@Column(name = "pro_descricao", length = 50, nullable = false)
	private String descricao;

	@NotNull(message = "Campo valor não pode ser vazio")
	@DecimalMin(value = "0.00", message = "Informe valor maior que Zero")
	@DecimalMax(value = "99999.99", message = "Informe valor menor que Cem mil")
	@Digits(integer = 5, fraction = 2, message = "Informe um valor válido para o campo Preço")
	@Column(name = "pro_preco", precision = 7, scale = 2, nullable = false)
	private BigDecimal preco;
	
	@NotNull(message = "Campo quantidade não pode ser vazio")
	@Min(value=0, message="Informe um valor maior que zero")
	@Max(value=9999, message="Informe um valor menor que Dez mil")
	@Column(name = "pro_quantidade", nullable = false)
	private Integer quantidade;

	@NotNull(message = "Campo Fabricante e Obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_fabricantes_fab_codigo", referencedColumnName = "fab_codigo")
	private Fabricante fabricante;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ",preco=" + preco + ", quantidade="
				+ quantidade + ", fabricante=" + fabricante + "]";
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
