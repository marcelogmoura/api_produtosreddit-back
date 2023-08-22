package com.mgmoura.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.ProdutoPostRequestDto;
import com.mgmoura.dtos.ProdutoPutRequestDto;
import com.mgmoura.dtos.ProdutoResponseDto;
import com.mgmoura.entities.Produto;
import com.mgmoura.repositories.ProdutoRepository;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping("/post")
	public ResponseEntity<ProdutoResponseDto> produtoPost(@RequestBody ProdutoPostRequestDto dto) {
		
		ProdutoResponseDto response = new ProdutoResponseDto();
		
		try {
			
			Produto produto = new Produto();
			produto.setDescricao(dto.getDescricao());
			produto.setDataCadastro(new SimpleDateFormat("yyyy-MM-DD").parse(dto.getDataCadastro()));
			produto.setQuantidadeMinima(dto.getQuantidadeMinima());
			produto.setValor(dto.getValor());
			
			produtoRepository.save(produto);
			
			response.setStatus(HttpStatus.CREATED);
			response.setMensagem("Produto cadastrado com sucesso");
			response.setProduto(produto);
			
		}catch (Exception e) {
			
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
		
	}
	
	@PutMapping("/put")
	public ResponseEntity<ProdutoResponseDto> produtoPut(@RequestBody ProdutoPutRequestDto dto) {
		
		ProdutoResponseDto response = new ProdutoResponseDto();
		
		try {
			Optional<Produto> produto = produtoRepository.findById(dto.getIdProduto());
			
			if(produto.isEmpty()) {

				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setMensagem("Produto não localizado");
				
			}else {
				Produto produtoPut = produto.get();
				produtoPut.setDescricao(dto.getDescricao());
				produtoPut.setDataCadastro(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataCadastro()));
				produtoPut.setQuantidadeMinima(dto.getQuantidadeMinima());
				produtoPut.setValor(dto.getValor());
				
				produtoRepository.save(produtoPut);
				
				response.setMensagem("Produto atualizado com sucesso");
				response.setStatus(HttpStatus.OK);
				response.setProduto(produtoPut);
							
			}
			
		}catch (Exception e) {
			
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); 
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);

		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Produto>> produtoGetAll() {
		
		try {
			List<Produto> produtos = produtoRepository.findAll();
			
			if(produtos.size() > 0) {
				return ResponseEntity.status(200).body(produtos);
				
			}else {
				return ResponseEntity.status(204).body(null);
			}
									
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		
	}
	
	@GetMapping("{idProduto}")
	public ResponseEntity<Produto> produtoGetById(@PathVariable("idProduto") Integer idProduto) {
		
		try {
			Optional<Produto> produto = produtoRepository.findById(idProduto);
			
			if(produto.isPresent()) {
				return ResponseEntity.status(200).body(produto.get());
				
			}else {
				return ResponseEntity.status(204).body(null);				
			}
						
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);

		}
		
	}
	
	
	@DeleteMapping("{idProduto}")
	public ResponseEntity<ProdutoResponseDto> delete(@PathVariable("idProduto") Integer idProduto) {
		
		ProdutoResponseDto response = new ProdutoResponseDto();
		
		try {
			Optional<Produto> produto = produtoRepository.findById(idProduto);
			
			if(produto.isEmpty()) {
				
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setMensagem("Produto não localizado");
				
			}else {
				
				if(produtoRepository.countByMovimentacao(idProduto) > 0) {
					response.setStatus(HttpStatus.BAD_REQUEST); // 400
					response.setMensagem("Não pode ser excluído, esse produto possui movimentação(ões) vinculada(s).");
					
				}else {
					
					produtoRepository.delete(produto.get());

					response.setStatus(HttpStatus.OK);
					response.setMensagem("Produto deletado com sucesso");
					response.setProduto(produto.get());
				}
				
			}
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); 
			response.setMensagem(e.getMessage());

		}
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}
}


