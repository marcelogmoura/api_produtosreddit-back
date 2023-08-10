package com.mgmoura.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.MovimentoPostRequestDto;

@RestController
@RequestMapping("api/movimentoEstoque")
public class MovimentoEstoqueController {
	
	@PostMapping("/post")
	public void movimentoPost(@RequestBody MovimentoPostRequestDto dto) {
		
	}
	
	@GetMapping("{data}")
	public void movimentoGetAll() {
		
	}

}
