package com.guilhermefgl.spring.crudproduto.models.repositories;

import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermefgl.spring.crudproduto.models.Imagem;
import com.guilhermefgl.spring.crudproduto.models.Produto;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "ImagemRepository.findByProduto", 
			query = "SELECT img FROM Imagem img WHERE img.produto = :produto") })
public interface ImagemRepository extends JpaRepository<Imagem, Integer> { 
	
	Imagem findByIdImagem(Integer idImagem);
	
	List<Imagem> findAll();
	
	List<Imagem> findByProduto(@Param("produto") Produto produto);
	
}
