package com.guilhermefgl.spring.crudproduto.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermefgl.spring.crudproduto.util.Response;
import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.dto.ProdutoDTO;
import com.guilhermefgl.spring.crudproduto.models.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	/**
	 * POST product
	 * 
	 * @param ProdutoDTO
	 * @param BindingResult
	 * @return ResponseEntity<Response<ProdutoDTO>>
	 * @throws ParseException
	 */
	@PostMapping
	public ResponseEntity<Response<ProdutoDTO>> save(@Valid @RequestBody ProdutoDTO produtoDTO, BindingResult result)
			throws ParseException {
		Response<ProdutoDTO> response = new Response<ProdutoDTO>();
		Produto produto = toModel(produtoDTO, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		} else {
			produto = produtoService.save(produto);
			response.setData(createProdutoDTO(new ProdutoDTO(), produto));
			return ResponseEntity.ok(response);
		}
	}

	/**
	 * GET list of products
	 * 
	 * @return ResponseEntity<Response<ProdutoDTO>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<ProdutoDTO>>> list() {
		Response<List<ProdutoDTO>> response = new Response<List<ProdutoDTO>>();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		produtoService.listProducts().forEach(produto -> produtosDTO.add(createProdutoDTO(new ProdutoDTO(), produto)));

		response.setData(produtosDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * GET list of products with parent
	 * 
	 * @return ResponseEntity<Response<List<ProdutoDTO>>>
	 */
	@GetMapping("/pais")
	public ResponseEntity<Response<List<ProdutoDTO>>> listWithParent() {
		Response<List<ProdutoDTO>> response = new Response<List<ProdutoDTO>>();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		produtoService.listProducts().forEach(produto -> {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			createProdutoDTO(produtoDTO, produto);
			createProdutoPaiDTO(produtoDTO, produto);
			produtosDTO.add(produtoDTO);
		});

		response.setData(produtosDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * GET product
	 * 
	 * @param produtoId
	 * @return ResponseEntity<Response<ProdutoDTO>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<ProdutoDTO>> get(@PathVariable("id") Integer produtoId) {
		Response<ProdutoDTO> response = new Response<ProdutoDTO>();
		Optional<Produto> produto = produtoService.getProduct(produtoId);
		if (!produto.isPresent()) {
			response.getErrors().add("Produto não encontrado");
			return ResponseEntity.badRequest().body(response);
		} else {
			response.setData(createProdutoDTO(new ProdutoDTO(), produto.get()));
			return ResponseEntity.ok(response);
		}
	}

	/**
	 * GET product with parent
	 * 
	 * @param produtoId
	 * @return ResponseEntity<Response<ProdutoDTO>>
	 */
	@GetMapping(value = "/{id}/pais")
	public ResponseEntity<Response<ProdutoDTO>> getWithParent(@PathVariable("id") Integer produtoId) {
		Response<ProdutoDTO> response = new Response<ProdutoDTO>();
		Optional<Produto> produto = produtoService.getProduct(produtoId);
		if (!produto.isPresent()) {
			response.getErrors().add("Produto não encontrado");
			return ResponseEntity.badRequest().body(response);
		} else {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			createProdutoDTO(produtoDTO, produto.get());
			createProdutoPaiDTO(produtoDTO, produto.get());
			response.setData(produtoDTO);
			return ResponseEntity.ok(response);
		}
	}

	/**
	 * GET list of sons products
	 * 
	 * @param produtoPaiId
	 * @return ResponseEntity<Response<List<ProdutoDTO>>>
	 */
	@GetMapping(value = "/{id}/filhos")
	public ResponseEntity<Response<List<ProdutoDTO>>> getWithSons(@PathVariable("id") Integer produtoPaiId) {
		Response<List<ProdutoDTO>> response = new Response<List<ProdutoDTO>>();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		produtoService.listSonsProducts(produtoPaiId).forEach(
				produto -> produtosDTO.add(createProdutoDTO(new ProdutoDTO(), produto)));

		response.setData(produtosDTO);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * PUT produto
	 * 
	 * @param id
	 * @param ProdutoDTO
	 * @return ResponseEntity<Response<Produto>>
	 * @throws ParseException 
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ProdutoDTO>> update(
			@PathVariable("id") Integer id, @Valid @RequestBody ProdutoDTO produtoDTO, BindingResult result) throws ParseException {
		Response<ProdutoDTO> response = new Response<ProdutoDTO>();
		produtoDTO.setIdProduto(id);
		Produto produto = toModel(produtoDTO, result);
		
		if (produtoDTO.getProdutoPai() != null) {
			Optional<Produto> produtoPai = produtoService.getProduct(produtoDTO.getProdutoPai().getIdProduto());
			if (!produtoPai.isPresent()) {
				result.addError(new ObjectError("produto", "Produto pai não encontrado"));
			}
		}

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		produto = produtoService.save(produto);
		response.setData(createProdutoDTO(new ProdutoDTO(), produto));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * DELETE produto
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Produto>>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Integer idProduto) {
		Response<String> response = new Response<String>();
		Optional<Produto> produto = produtoService.getProduct(idProduto);

		if (!produto.isPresent()) {
			response.getErrors().add("Produto não encontrado");
			return ResponseEntity.badRequest().body(response);
		}

		produtoService.delete(idProduto);
		return ResponseEntity.ok(new Response<String>());
	}

	/*
	 * Convert DTO to model class
	 * 
	 * @param ProdutoDTO
	 * @param BindingResult
	 * @return Produto
	 * @throws ParseException
	 */
	private Produto toModel(ProdutoDTO produtoDTO, BindingResult result) throws ParseException {
		Produto produto = new Produto();

		if (produtoDTO.getIdProduto() != null) {
			Optional<Produto> produtoOpt = produtoService.getProduct(produtoDTO.getIdProduto());
			if (produtoOpt.isPresent()) {
				produto = produtoOpt.get();
			} else {
				result.addError(new ObjectError("produto", "Produto não encontrado."));
			}
		}

		if (produtoDTO.getProdutoPai() != null) {
			Optional<Produto> produtoOpt = produtoService.getProduct(produtoDTO.getProdutoPai().getIdProduto());
			if (produtoOpt.isPresent()) {
				produto.setProdutoPai(produtoOpt.get());
			} else {
				result.addError(new ObjectError("produto", "Produto pai não encontrado."));
			}
		}
		
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());

		return produto;
	}

	/*
	 * Create Produto DTO object from model
	 * 
	 * @param ProdutoDTO
	 * @param Produto
	 * @return ProdutoDTO
	 */
	ProdutoDTO createProdutoDTO(ProdutoDTO produtoDTO, Produto produto) {
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setDescricao(produto.getDescricao());
		if (produto.getIdProdutoOpt().isPresent()) {
			produtoDTO.setIdProduto(produto.getIdProdutoOpt().get());
		}
		return produtoDTO;
	}

	/*
	 * Create ProdutoPai DTO object from model
	 * 
	 * @param ProdutoDTO
	 * @param Produto
	 * @return ProdutoDTO
	 */
	ProdutoDTO createProdutoPaiDTO(ProdutoDTO produtoDTO, Produto produto) {
		if (produto.getProdutoPaiOpt().isPresent()) {
			Produto produtoPai = produto.getProdutoPaiOpt().get();
			ProdutoDTO produtoPaiDTO = new ProdutoDTO();
			produtoPaiDTO.setNome(produtoPai.getNome());
			produtoPaiDTO.setDescricao(produtoPai.getDescricao());
			if (produtoPai.getIdProdutoOpt().isPresent()) {
				produtoPaiDTO.setIdProduto(produtoPai.getIdProdutoOpt().get());
			}
			produtoDTO.setProdutoPai(produtoPaiDTO);
		}
		return produtoDTO;
	}

}
