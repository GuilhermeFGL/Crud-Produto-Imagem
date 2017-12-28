package com.guilhermefgl.spring.crudproduto.models.dao;

import java.util.List;

import com.guilhermefgl.spring.crudproduto.models.Imagem;
import com.guilhermefgl.spring.crudproduto.models.services.ImagemService;

public interface ImagemDAO {

	/*
	 * List Imagens from Produto id
	 * 
	 * @param produtoId
	 * @return List<Imagem>
	 */
	List<Imagem> listProdutoImages(Integer produtoId);
	
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
	
	/*
	 * DAO Factory
	 * 
	 * @return ImagemServiceContract
	 */
	public static ImagemDAO getService() {
		return new ImagemService();
	}
	
}
