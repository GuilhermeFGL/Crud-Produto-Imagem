package com.guilhermefgl.spring.crudproduto.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

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
import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.services.ProdutoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProdutoControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProdutoService produtoService;
	
	private final String PRODUTO_BASE = "/api/produtos/";
	private final Integer PRODUTO_ID = 123;
	
	@Test
	@SuppressWarnings("serial")
	public void addTest_valid() throws Exception {
		Produto produto = new Produto() {{
			setNome("Teste");
			setDescricao("produto teste");
		}};
		BDDMockito.given(produtoService.save(Mockito.any(Produto.class)))
			.willReturn(produto);

		mvc.perform(MockMvcRequestBuilders.post(PRODUTO_BASE)
				.content(toJson(produto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
		
	}
	
	@Test
	public void addTest_invalid() throws Exception {
		BDDMockito.given(produtoService.getProduct(Mockito.anyInt())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.post(PRODUTO_BASE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	@SuppressWarnings("serial")
	public void getTest_valid() throws Exception {
		BDDMockito.given(produtoService.getProduct(Mockito.anyInt()))
				.willReturn(Optional.of(new Produto() {{
					setIdProduto(PRODUTO_ID);
					setNome("Teste");
					setDescricao("produto teste");
				}}));

		mvc.perform(MockMvcRequestBuilders.get(PRODUTO_BASE + PRODUTO_ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.idProduto").value(PRODUTO_ID));
	}
	
	@Test
	public void getTest_invalid() throws Exception {
		BDDMockito.given(produtoService.getProduct(Mockito.anyInt())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.get(PRODUTO_BASE + PRODUTO_ID).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Produto não encontrado"));
	}
	
	@Test
	public void testRemoverLancamento() throws Exception {
		BDDMockito.given(produtoService.getProduct(Mockito.anyInt())).willReturn(Optional.of(new Produto()));

		mvc.perform(MockMvcRequestBuilders.delete(PRODUTO_BASE + PRODUTO_ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	private String toJson(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

}
