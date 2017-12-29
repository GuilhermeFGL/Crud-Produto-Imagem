package com.guilhermefgl.spring.crudproduto.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.dao.ProdutoDAO;
import com.guilhermefgl.spring.crudproduto.models.repositories.ProdutoRepository;

@Service
@Configurable
public class ProdutoService implements ProdutoDAO {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Optional<Produto> getProduct(Integer idProduto) {
		return Optional.ofNullable(produtoRepository.findByIdProduto(idProduto));
	}

	@Override
	public List<Produto> listProducts() {
		return produtoRepository.findAll();
	}

	@Override
	public List<Produto> listSonsProducts(Produto produtoPai) {
		return produtoRepository.findByProdutoPai(produtoPai);
	}

	@Override
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public void delete(Integer produtoId) {
		produtoRepository.delete(produtoId);
	}

}
