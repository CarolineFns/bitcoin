package br.com.alura.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Ordem extends PanacheEntityBase {

	@Id
	@SequenceGenerator(name = "seq_ordem", sequenceName = "seq_ordem", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ordem")
	private Long id;

	private Double preco;

	private String tipo;

	private Date data;

	private String status;

	@Column(name = "idusuario")
	private Long idUsuario;

	public void setId(Long id) {
		this.id = id;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Double getPreco() {
		return preco;
	}

	public String getTipo() {
		return tipo;
	}

	public Date getData() {
		return data;
	}

	public String getStatus() {
		return status;
	}

}
