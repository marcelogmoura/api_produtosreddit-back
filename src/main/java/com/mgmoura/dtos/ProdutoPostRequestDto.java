package com.mgmoura.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPostRequestDto {
	
	private String descricao;
	private Integer quantidadeMinima;
	private String dataCadastro; 
	private Double Valor;

}
