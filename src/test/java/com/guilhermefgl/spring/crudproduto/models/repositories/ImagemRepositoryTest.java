package com.guilhermefgl.spring.crudproduto.models.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.guilhermefgl.spring.crudproduto.models.Imagem;
import com.guilhermefgl.spring.crudproduto.models.Produto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ImagemRepositoryTest {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ImagemRepository imagemRepository;
	
	private static final String IMAGEM_TIPO_1 = "tipo1";
	private static final String IMAGEM_TIPO_2 = "tipo2";
	
	@Before
	public void setUp() throws Exception {
		Produto produto = new Produto();
		produto.setNome("produto teste");
		produto.setDescricao("produto de teste");
		produtoRepository.save(produto);
		
		Imagem imagem1 = new Imagem();
		imagem1.setTipo(IMAGEM_TIPO_1);
		imagem1.setProduto(produtoRepository.findByIdProduto(1));
		imagemRepository.save(imagem1);
		
		Imagem imagem2 = new Imagem();
		imagem2.setTipo(IMAGEM_TIPO_2);
		imagem2.setProduto(produtoRepository.findByIdProduto(1));
		imagemRepository.save(imagem2);
	}
	
	@After
    public final void tearDown() { 
		produtoRepository.deleteAll();
		imagemRepository.deleteAll();
	}
	
	@Test
	public void findByProduto() {
		Produto produto = produtoRepository.findByIdProduto(1);
		List<Imagem> imagens = imagemRepository.findByProduto(produto);
		assertEquals(2, imagens.size());
	}
}
