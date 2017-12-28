package com.guilhermefgl.spring.crudproduto.models.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.guilhermefgl.spring.crudproduto.models.Imagem;

public class ProdutoDTO {
	
	private Integer idProduto;
	private String nome;
	private String descricao;
	private ProdutoDTO produtoPai;
	private List<Imagem> imagens;
	
	/**
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}
	
	/**
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * @return the nome
	 */
	@NotEmpty(message = "Nome não pode ser vazio")
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the descricao
	 */
	@NotEmpty(message = "Descricao não pode ser vazio")
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the produtoPai
	 */
	public ProdutoDTO getProdutoPai() {
		return produtoPai;
	}

	/**
	 * @param produtoPai the produtoPai to set
	 */
	public void setProdutoPai(ProdutoDTO produtoPai) {
		this.produtoPai = produtoPai;
	}

	/**
	 * @return the imagens
	 */
	public List<Imagem> getImagens() {
		return imagens;
	}

	/**
	 * @param imagens the imagens to set
	 */
	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

}
