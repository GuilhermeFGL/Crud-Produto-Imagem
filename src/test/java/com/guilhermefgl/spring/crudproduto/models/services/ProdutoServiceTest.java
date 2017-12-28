package com.guilhermefgl.spring.crudproduto.models.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.repositories.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProdutoServiceTest {

	@MockBean
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(produtoRepository.findAll()).willReturn(new ArrayList<Produto>());
		BDDMockito.given(produtoRepository.findSonsByIdProduto(Mockito.anyInt())).willReturn(new ArrayList<Produto>());
		BDDMockito.given(produtoRepository.findByIdProduto(Mockito.anyInt())).willReturn(new Produto());
		BDDMockito.given(produtoRepository.save(Mockito.any(Produto.class))).willReturn(new Produto());
	}
	
	@Test
	public void getProductTest() {
		Optional<Produto> produto = produtoService.getProduct(Mockito.anyInt());
		assertTrue(produto.isPresent());
	}
	
	@Test
	public void listProductsTest() {
		List<Produto> produtos = produtoService.listProducts();
		assertNotNull(produtos);
	}
	
	@Test
	public void listSonsProductsTest() {
		List<Produto> produtos = produtoService.listSonsProducts(Mockito.anyInt());
		assertNotNull(produtos);
	}
	
	@Test
	public void saveTest() {
		Produto produto = produtoService.save(Mockito.any(Produto.class));
		assertNotNull(produto);
	}
	
}
