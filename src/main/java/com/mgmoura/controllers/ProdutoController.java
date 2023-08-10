package com.mgmoura.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.mgmoura.entities.Produto;
import com.mgmoura.repositories.ProdutoRepository;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping("/post")
	public ResponseEntity<String> produtoPost(@RequestBody ProdutoPostRequestDto dto) {
		
		try {
			
			Produto produto = new Produto();
			produto.setDescricao(dto.getDescricao());
			produto.setDataCadastro(new SimpleDateFormat("yyyy-MM-DD").parse(dto.getDataCadastro()));
			produto.setQuantidadeMinima(dto.getQuantidadeMinima());
			produto.setValor(dto.getValor());
			
			produtoRepository.save(produto);
			
			return ResponseEntity.status(201).body("cadastrado ok");
			
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body("erro" +e.getMessage());
		}
		
	}
	
	@PutMapping("/put")
	public ResponseEntity<String> produtoPut(@RequestBody ProdutoPutRequestDto dto) {
		
		try {
			Optional<Produto> produto = produtoRepository.findById(dto.getIdProduto());
			
			if(produto.isEmpty()) {
				return ResponseEntity.status(400).body("nao encontrado");
				
			}else {
				Produto produtoPut = produto.get();
				produtoPut.setDescricao(dto.getDescricao());
				produtoPut.setDataCadastro(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataCadastro()));
				produtoPut.setQuantidadeMinima(dto.getQuantidadeMinima());
				produtoPut.setValor(dto.getValor());
				
				produtoRepository.save(produtoPut);
				
				return ResponseEntity.status(200).body("atualizado");
							
			}
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body("falha" + e.getMessage());
		}
		
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
	public ResponseEntity<String> delete(@PathVariable("idProduto") Integer idProduto) {
		try {
			Optional<Produto> produto = produtoRepository.findById(idProduto);
			
			if(produto.isEmpty()) {
				return ResponseEntity.status(400).body("nao localizado");

				
			}else {
				produtoRepository.delete(produto.get());
				return ResponseEntity.status(200).body("excluido");
				
			}
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body("falha" + e.getMessage());

		}
		
	}


}
