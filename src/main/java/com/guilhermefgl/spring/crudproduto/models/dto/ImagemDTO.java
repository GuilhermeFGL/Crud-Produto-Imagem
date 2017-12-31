package com.guilhermefgl.spring.crudproduto.models.dto;

import java.text.ParseException;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.guilhermefgl.spring.crudproduto.models.Imagem;
import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.services.ImagemService;
import com.guilhermefgl.spring.crudproduto.models.services.ProdutoService;

public class ImagemDTO {
	
	private Integer idImagem;
	private String tipo;
	private ProdutoDTO produto;
	
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
	@NotEmpty(message = "Tipo não pode ser vazio")
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
	 * @return ProdutoDTO
	 */
	@NotNull(message = "Produto não pode ser vazio")
	public ProdutoDTO getProduto() {
		return produto;
	}

	/**
	 * @param ProdutoDTO
	 */
	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}
	
	/*
	 * Convert DTO to model class
	 * 
	 * @param ImagemService
	 * @param BindingResult
	 * @return Imagem
	 * @throws ParseException
	 */
	public Imagem toModel(ProdutoService produtoService, ImagemService imagemService, BindingResult result) throws ParseException {
		Imagem imagem = new Imagem();

		if (getIdImagem() != null) {
			Optional<Imagem> imagemOpt = imagemService.getImagem(getIdImagem());
			if (imagemOpt.isPresent()) {
				imagem = imagemOpt.get();
			} else {
				result.addError(new ObjectError("imagem", "Imagem não encontrado."));
			}
		}

		if (getProduto() != null) {
			Optional<Produto> produtoOpt = produtoService.getProduct(getProduto().getIdProduto());
			if (produtoOpt.isPresent()) {
				imagem.setProduto(produtoOpt.get());
			} else {
				result.addError(new ObjectError("produto", "Produto pai não encontrado."));
			}	
		}
		
		
		imagem.setTipo(getTipo());
		return imagem;
	}
	
	/*
	 * Create Imagem DTO object from model
	 * 
	 * @param Imagem
	 * @return ImagemDTO
	 */
	public ImagemDTO createImagemDTO(Imagem imagem) {
		setTipo(imagem.getTipo());
		if (imagem.getIdImagemOpt().isPresent()) {
			setIdImagem(imagem.getIdImagemOpt().get());
		}
		return this;
	}

}
