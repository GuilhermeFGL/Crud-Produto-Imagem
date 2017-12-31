package com.guilhermefgl.spring.crudproduto.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilhermefgl.spring.crudproduto.models.Imagem;
import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.repositories.ProdutoRepository;
import com.guilhermefgl.spring.crudproduto.models.services.ImagemService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ImagemControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ImagemService imagemService;
	
	@MockBean
	private ProdutoRepository produtoRepository;
	
	private final String IMAGEM_BASE = "/api/imagens/";
	private final Integer IMAGEM_ID = 123;	

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(produtoRepository.findByIdProduto(Mockito.anyInt())).willReturn(new Produto());
	}
	
	@Test
	@SuppressWarnings("serial")
	public void postTest_valid() throws Exception {
		Imagem imagem = new Imagem() {{
			setTipo("img teste");
			setProduto(new Produto() {{
				setIdProduto(1);
			}});
		}};
		BDDMockito.given(imagemService.save(Mockito.any(Imagem.class)))
			.willReturn(imagem);

		mvc.perform(MockMvcRequestBuilders.post(IMAGEM_BASE)
				.content(toJson(imagem))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	public void postTest_invalid() throws Exception {
		BDDMockito.given(imagemService.getImagem(Mockito.anyInt())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.post(IMAGEM_BASE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getTest_valid()  throws Exception {
		BDDMockito.given(imagemService.listImagens()).willReturn(new ArrayList<Imagem>());
		
		mvc.perform(MockMvcRequestBuilders.get(IMAGEM_BASE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	@Test
	public void getWithProdutoTest()  throws Exception {
		BDDMockito.given(imagemService.listImagens()).willReturn(new ArrayList<Imagem>());
		
		mvc.perform(MockMvcRequestBuilders.get(IMAGEM_BASE + "?produto=1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	@SuppressWarnings("serial")
	public void putTest() throws Exception {
		Imagem imagem = new Imagem() {{
			setTipo("img teste - update");
			setProduto(new Produto() {{
				setIdProduto(1);
			}});
		}};
		BDDMockito.given(imagemService.getImagem(Mockito.anyInt())).willReturn(Optional.of(new Imagem()));
		BDDMockito.given(imagemService.save(Mockito.any(Imagem.class))).willReturn(imagem);

		mvc.perform(MockMvcRequestBuilders.put(IMAGEM_BASE + IMAGEM_ID)
				.content(toJson(imagem))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	public void deleteTest() throws Exception {
		BDDMockito.given(imagemService.getImagem(Mockito.anyInt())).willReturn(Optional.of(new Imagem()));

		mvc.perform(MockMvcRequestBuilders.delete(IMAGEM_BASE + IMAGEM_ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	private String toJson(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

}
