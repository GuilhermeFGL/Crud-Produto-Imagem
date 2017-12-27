package com.guilhermefgl.spring.crudproduto.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imagem")
public class Imagem implements Serializable {
	
	private static final long serialVersionUID = -6436811442220514989L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idImagem;
	
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	/**
	 * @return the idImagem
	 */
	public Integer getIdImagem() {
		return idImagem;
	}

	/**
	 * @param idImagem the idImagem to set
	 */
	public void setIdImagem(Integer idImagem) {
		this.idImagem = idImagem;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
