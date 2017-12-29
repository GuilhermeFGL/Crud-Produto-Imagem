package com.guilhermefgl.spring.crudproduto.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermefgl.spring.crudproduto.models.Produto;

@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	List<Produto> findAll();
	
	List<Produto> findByProdutoPai(Produto produtoPai);
	
	Produto findByIdProduto(Integer idProduto);

}
