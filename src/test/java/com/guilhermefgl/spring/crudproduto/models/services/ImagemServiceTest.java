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

import com.guilhermefgl.spring.crudproduto.models.Imagem;
import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.repositories.ImagemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ImagemServiceTest {
	
	@MockBean
	private ImagemRepository imagemRepository;

	@Autowired
	private ImagemService imagemService;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(imagemRepository.findAll()).willReturn(new ArrayList<Imagem>());
		BDDMockito.given(imagemRepository.findByProduto(Mockito.any(Produto.class))).willReturn(new ArrayList<Imagem>());
		BDDMockito.given(imagemRepository.findByIdImagem(Mockito.anyInt())).willReturn(new Imagem());
		BDDMockito.given(imagemRepository.save(Mockito.any(Imagem.class))).willReturn(new Imagem());
	}
	
	@Test
	public void getImagemTest() {
		Optional<Imagem> imagem = imagemService.getImagem(Mockito.anyInt());
		assertTrue(imagem.isPresent());
	}
	
	@Test
	public void listProductsTest() {
		List<Imagem> imagens = imagemService.listImagens();
		assertNotNull(imagens);
	}
	
	@Test
	public void saveTest() {
		Imagem imagem = imagemService.save(Mockito.any(Imagem.class));
		assertNotNull(imagem);
	}

}
