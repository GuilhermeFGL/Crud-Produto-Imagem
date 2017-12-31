package com.guilhermefgl.spring.crudproduto.models.dao;

import java.util.List;
import java.util.Optional;

import com.guilhermefgl.spring.crudproduto.models.Imagem;
import com.guilhermefgl.spring.crudproduto.models.Produto;

public interface ImagemDAO {
	
	/*
	 * GET imagem
	 * 
	 * @param imagemId
	 * @return Imagem
	 */
	Optional<Imagem> getImagem(Integer imagemId);
	
	/*
	 * List Imagens
	 * 
	 * @return List<Imagem>
	 */
	List<Imagem> listImagens();

	/*
	 * List Imagens from Produto id
	 * 
	 * @param Produto
	 * @return List<Imagem>
	 */
	List<Imagem> listProdutoImages(Produto produto);
	
	/*
	 * Persist product object
	 * 
	 * @param Imagem
	 * @return Imagem
	 */
	Imagem save(Imagem Imagem);
	
	/*
	 * Delete product object
	 * 
	 * @param Imagem
	 */
	void delete(Integer Imagem);
	
}
