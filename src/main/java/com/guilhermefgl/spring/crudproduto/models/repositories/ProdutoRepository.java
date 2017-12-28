package com.guilhermefgl.spring.crudproduto.models.repositories;

import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermefgl.spring.crudproduto.models.Produto;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "ProdutoRepository.findSonsByIdProdutoPai",
			query = "SELECT * FROM Produto prd WHERE prd.idProdutoPai = :idProdutoPai") })
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	List<Produto> findAll();
	
	List<Produto> findSonsByIdProduto(@Param("idProdutoPai") Integer idProdutoPai);
	
	Produto findByIdProduto(Integer idProduto);

}
