package com.guilhermefgl.spring.crudproduto.models.dao;

import java.util.List;
import java.util.Optional;

import com.guilhermefgl.spring.crudproduto.models.Produto;

public interface ProdutoDAO {

	/*
	 * Search a product by his id
	 * 
	 * @param idProduto
	 * @return Optional<Produto>
	 */
	Optional<Produto> getProduct(Integer idProduto);
	
	/*
	 * List all products
	 * 
	 * @return List<Produto>
	 */
	List<Produto> listProducts();
	
	/*
	 * List all sons products from a parent product id
	 * 
	 * @param Produto
	 * @return List<Produto>
	 */
	List<Produto> listSonsProducts(Produto produtoPai);
	
	/*
	 * Persist product object
	 * 
	 * @param produto
	 * @return Produto
	 */
	Produto save(Produto produto);
	
	/*
	 * Delete product object
	 * 
	 * @param produtoId
	 */
	void delete(Integer produtoId);
}
