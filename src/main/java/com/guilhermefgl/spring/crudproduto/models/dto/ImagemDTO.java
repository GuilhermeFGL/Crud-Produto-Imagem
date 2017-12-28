package com.guilhermefgl.spring.crudproduto.models.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.NotEmpty;

public class ImagemDTO {
	
	private Optional<Integer> idImagem;
	private String tipo;
	private Integer idProduto;
	
	/**
	 * @return the idImagem
	 */
	public Optional<Integer> getIdImagem() {
		return idImagem;
	}
	
	/**
	 * @param idImagem the idImagem to set
	 */
	public void setIdImagem(Optional<Integer> idImagem) {
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
	@NotEmpty(message = "Tipo não pode ser vazio")
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto the idProduto to set
	 */
	@NotEmpty(message = "Produto não pode ser vazio")
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

}
