package com.mgmoura.controllers;

import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.MovimentoPostRequestDto;
import com.mgmoura.entities.MovimentoEstoque;
import com.mgmoura.entities.Produto;
import com.mgmoura.repositories.MovimentoRepository;
import com.mgmoura.repositories.ProdutoRepository;

@RestController
@RequestMapping("api/movimentoEstoque")
public class MovimentoEstoqueController {
	
	@Autowired
	private MovimentoRepository movimentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	
	@PostMapping("/post")
	public ResponseEntity<String> movimentoPost(@RequestBody MovimentoPostRequestDto dto) {
		
		try {
			Optional<Produto> produto = produtoRepository.findById(dto.getIdProduto());
			
			if(produto.isEmpty()) {
				return ResponseEntity.status(400).body("Produto não localizado");
				
			}else {
				MovimentoEstoque movimentoEstoque = new MovimentoEstoque();
				
				movimentoEstoque.setProduto(produto.get());
				movimentoEstoque.setQuantidade(dto.getQuantidade());
				movimentoEstoque.setTipoMovimento(dto.getTipoMovimento());
				movimentoEstoque.setDataMovimento(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataMovimento()));
				
				movimentoRepository.save(movimentoEstoque);
				
				return ResponseEntity.status(201).body("Movimentação cadastrada");
			}
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body("Falha ao cadastrar" + e.getMessage());
		}
	}
	
	@GetMapping("{dataInicio}/{dataFim}")
	public void getAll(
			@PathVariable("dataInicio") String dataInicio, 
			@PathVariable("dataFim") String dataFim) {
		
	}

}
