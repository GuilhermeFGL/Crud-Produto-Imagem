package com.guilhermefgl.spring.crudproduto.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermefgl.spring.crudproduto.models.Imagem;
import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.dao.ImagemDAO;
import com.guilhermefgl.spring.crudproduto.models.repositories.ImagemRepository;
import com.guilhermefgl.spring.crudproduto.models.repositories.ProdutoRepository;

@Service
public class ImagemService implements ImagemDAO {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ImagemRepository imagemRepository;
	
	@Override
	public Optional<Imagem> getImagem(Integer imagemId) {
		return Optional.ofNullable(imagemRepository.findByIdImagem(imagemId));
	}
	
	@Override
	public List<Imagem> listImagens() {
		return imagemRepository.findAll();
	}

	@Override
	public List<Imagem> listProdutoImages(Integer produtoId) {
		Optional<Produto> produto = Optional.ofNullable(produtoRepository.findByIdProduto(produtoId));
		if (produto.isPresent()) {
			return imagemRepository.findByProduto(produto.get());
		} else {
			return new ArrayList<Imagem>();
		}
	}

	@Override
	public Imagem save(Imagem imagem) {
		return imagemRepository.save(imagem);
	}

	@Override
	public void delete(Integer imagemId) {
		imagemRepository.delete(imagemId);
	}

}
