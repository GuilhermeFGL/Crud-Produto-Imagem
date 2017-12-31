package com.guilhermefgl.spring.crudproduto.models.dto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.services.ImagemService;
import com.guilhermefgl.spring.crudproduto.models.services.ProdutoService;

public class ProdutoDTO {
	
	private Integer idProduto;
	private String nome;
	private String descricao;
	private ProdutoDTO produtoPai;
	private List<ImagemDTO> imagens;
	
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
	 * @return the imagensDTO
	 */
	public List<ImagemDTO> getImagens() {
		return imagens;
	}

	/**
	 * @param imagens the imagensDTO to set
	 */
	public void setImagens(List<ImagemDTO> imagens) {
		this.imagens = imagens;
	}
	
	/*
	 * Convert DTO to model class
	 * 
	 * @param produtoService
	 * @param BindingResult
	 * @return Produto
	 * @throws ParseException
	 */
	public Produto toModel(ProdutoService produtoService, BindingResult result) throws ParseException {
		Produto produto = new Produto();

		if (getIdProduto() != null) {
			Optional<Produto> produtoOpt = produtoService.getProduct(getIdProduto());
			if (produtoOpt.isPresent()) {
				produto = produtoOpt.get();
			} else {
				result.addError(new ObjectError("produto", "Produto não encontrado."));
			}
		}

		if (getProdutoPai() != null) {
			Optional<Produto> produtoOpt = produtoService.getProduct(getProdutoPai().getIdProduto());
			if (produtoOpt.isPresent()) {
				produto.setProdutoPai(produtoOpt.get());
			} else {
				result.addError(new ObjectError("produto", "Produto pai não encontrado."));
			}
		}
		
		produto.setNome(getNome());
		produto.setDescricao(getDescricao());

		return produto;
	}

	/*
	 * Create Produto DTO object from model
	 * 
	 * @param Produto
	 * @return ProdutoDTO
	 */
	public ProdutoDTO createProdutoDTO(Produto produto) {
		setNome(produto.getNome());
		setDescricao(produto.getDescricao());
		if (produto.getIdProdutoOpt().isPresent()) {
			setIdProduto(produto.getIdProdutoOpt().get());
		}
		return this;
	}

	/*
	 * Create ProdutoPai DTO object from model
	 * 
	 * @param Produto
	 * @return ProdutoDTO
	 */
	public ProdutoDTO createProdutoPaiDTO(Produto produto) {
		if (produto.getProdutoPaiOpt().isPresent()) {
			Produto produtoPai = produto.getProdutoPaiOpt().get();
			ProdutoDTO produtoPaiDTO = new ProdutoDTO();
			produtoPaiDTO.setNome(produtoPai.getNome());
			produtoPaiDTO.setDescricao(produtoPai.getDescricao());
			if (produtoPai.getIdProdutoOpt().isPresent()) {
				produtoPaiDTO.setIdProduto(produtoPai.getIdProdutoOpt().get());
			}
			setProdutoPai(produtoPaiDTO);
		}
		return this;
	}
	
	/*
	 *  Create List ImagemDTO from model
	 *  
	 *  @param ImagemService
	 *  @param Produto
	 *  @return ProdutoDTO
	 */
	public ProdutoDTO createImagensDTO(ImagemService imagemService, Produto produto) {
		List<ImagemDTO> imagensDTO = new ArrayList<ImagemDTO>();
		imagemService.listProdutoImages(produto).forEach(imagem -> imagensDTO.add(new ImagemDTO().createImagemDTO(imagem)));
		setImagens(imagensDTO);
		return this;
	}

}
