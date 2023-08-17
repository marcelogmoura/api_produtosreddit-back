package com.mgmoura.controllers;

import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.MovimentoPostRequestDto;
import com.mgmoura.dtos.MovimentoResponseDto;
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
	public ResponseEntity<MovimentoResponseDto> movimentoPost(@RequestBody MovimentoPostRequestDto dto) {
		
		MovimentoResponseDto response = new MovimentoResponseDto();
		
		try {
			Optional<Produto> produto = produtoRepository.findById(dto.getIdProduto());
			
			if(produto.isEmpty()) {
				
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setMensagem("Produto não localizado");
				
			}else {
				MovimentoEstoque movimentoEstoque = new MovimentoEstoque();
				
				movimentoEstoque.setProduto(produto.get());
				movimentoEstoque.setQuantidade(dto.getQuantidade());
				movimentoEstoque.setTipoMovimento(dto.getTipoMovimento());
				movimentoEstoque.setDataMovimento(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataMovimento()));
				
				movimentoRepository.save(movimentoEstoque);
				
				response.setStatus(HttpStatus.CREATED);
				response.setMensagem("Movimentação cadastrada com sucesso");
				
			}
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}
	
	@GetMapping("{dataInicio}/{dataFim}")
	public void getAll(
			@PathVariable("dataInicio") String dataInicio, 
			@PathVariable("dataFim") String dataFim) {
		
	}

}
