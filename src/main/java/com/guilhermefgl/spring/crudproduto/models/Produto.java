package com.guilhermefgl.spring.crudproduto.models;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = -6604809960636279954L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idProduto;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@ManyToOne(fetch = FetchType.EAGER) // (?) EAGER = loop
	@JoinColumn(name="idProdutoPai")
	private Produto produtoPai;
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Imagem> imagens;	
	
	/**
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}
	
	/**
	 * @return the optional produtoPai
	 */
	@Transient
	public Optional<Integer> getIdProdutoOpt() {
		return Optional.ofNullable(idProduto);
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
	public Produto getProdutoPai() {
		return produtoPai;
	}
	
	
	/**
	 * @return the optional produtoPai
	 */
	@Transient
	public Optional<Produto> getProdutoPaiOpt() {
		return Optional.ofNullable(produtoPai);
	}


	/**
	 * @param produtoPai the produtoPai to set
	 */
	@OneToMany(mappedBy="produtoPai")
	public void setProdutoPai(Produto produtoPai) {
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
