package com.mgmoura.dtos;

import org.springframework.http.HttpStatus;

import com.mgmoura.entities.MovimentoEstoque;

import lombok.Data;

@Data
public class MovimentoResponseDto {
	
	private HttpStatus status;
	private String mensagem;
	private MovimentoEstoque movimentoEstoque;

}
