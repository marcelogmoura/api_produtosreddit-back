package com.mgmoura.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.ProdutoPostRequestDto;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
	
	@PostMapping("/post")
	public void produtoPost(@RequestBody ProdutoPostRequestDto dto) {
		
	}
	
	@PutMapping("/put")
	public void produtoPut(@RequestBody ProdutoPostRequestDto dto) {
		
	}
	
	@GetMapping("/getAll")
	public void produtoGetAll() {
		
	}
	
	@GetMapping("/getById")
	public void produtoGetById(@PathVariable("idProduto") Integer idProduto) {
		
	}
	
	
	@DeleteMapping("{idProduto}")
	public void delete(@PathVariable("idProduto") Integer idProduto) {
		
	}


}
