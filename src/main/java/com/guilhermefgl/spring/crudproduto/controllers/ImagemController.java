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

import com.guilhermefgl.spring.crudproduto.models.Imagem;
import com.guilhermefgl.spring.crudproduto.models.Produto;
import com.guilhermefgl.spring.crudproduto.models.dto.ImagemDTO;
import com.guilhermefgl.spring.crudproduto.models.services.ImagemService;
import com.guilhermefgl.spring.crudproduto.models.services.ProdutoService;
import com.guilhermefgl.spring.crudproduto.util.Response;

@RestController
@RequestMapping("/api/imagens")
@CrossOrigin(origins = "*")
public class ImagemController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ImagemService imagemService;
	
	/**
	 * POST imagem
	 * 
	 * @param ImagemDTO
	 * @param BindingResult
	 * @return ResponseEntity<Response<ImagemDTO>>
	 * @throws ParseException
	 */
	@PostMapping
	public ResponseEntity<Response<ImagemDTO>> save(@Valid @RequestBody ImagemDTO imagemDTO, BindingResult result)
			throws ParseException {
		Response<ImagemDTO> response = new Response<ImagemDTO>();
		Imagem imagem = imagemDTO.toModel(produtoService, imagemService, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		} else {
			imagem = imagemService.save(imagem);
			response.setData(new ImagemDTO().createImagemDTO(imagem));
			return ResponseEntity.ok(response);
		}
	}
	
	/**
	 * GET list of imagens
	 * 
	 * @return ResponseEntity<Response<ImagemDTO>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<ImagemDTO>>> list() {
		Response<List<ImagemDTO>> response = new Response<List<ImagemDTO>>();
		List<ImagemDTO> imagensDTO = new ArrayList<ImagemDTO>();
		imagemService.listImagens().forEach(imagem -> imagensDTO.add(new ImagemDTO().createImagemDTO(imagem)));

		response.setData(imagensDTO);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * PUT imagem
	 * 
	 * @param idImagem
	 * @param ImagemDTO
	 * @return ResponseEntity<Response<ImagemDTO>>
	 * @throws ParseException 
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ImagemDTO>> update(
			@PathVariable("id") Integer idImagem, @Valid @RequestBody ImagemDTO imagemDTO, BindingResult result) throws ParseException {
		Response<ImagemDTO> response = new Response<ImagemDTO>();
		imagemDTO.setIdImagem(idImagem);
		Imagem imagem = imagemDTO.toModel(produtoService, imagemService, result);
		
		if (imagemDTO.getProduto() != null) {
			Optional<Produto> produtoPai = produtoService.getProduct(imagemDTO.getProduto().getIdProduto());
			if (!produtoPai.isPresent()) {
				result.addError(new ObjectError("imagem", "Produto não encontrado"));
			}
		}

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		} else {
			imagem = imagemService.save(imagem);
			response.setData(new ImagemDTO().createImagemDTO(imagem));
			return ResponseEntity.ok(response);	
		}
	}
	
	/**
	 * DELETE imagem
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Integer idImagem) {
		Response<String> response = new Response<String>();
		Optional<Imagem> imagem = imagemService.getImagem(idImagem);

		if (!imagem.isPresent()) {
			response.getErrors().add("Imagem não encontrado");
			return ResponseEntity.badRequest().body(response);
		}

		imagemService.delete(idImagem);
		return ResponseEntity.ok(new Response<String>());
	}

}
