package com.guilhermefgl.spring.crudproduto.models.dao;

import java.util.List;
import java.util.Optional;

import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.services.ProdutoService;

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
	 * List all children products from a father product id
	 * 
	 * @param idProdutoPai
	 * @return List<Produto>
	 */
	List<Produto> listChildrenProducts(Integer idProdutoPai);
	
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
	
	/*
	 * DAO Factory
	 * 
	 * @return ProdutoServiceDAO
	 */
	public static ProdutoDAO getService() {
		return new ProdutoService();
	}
}
