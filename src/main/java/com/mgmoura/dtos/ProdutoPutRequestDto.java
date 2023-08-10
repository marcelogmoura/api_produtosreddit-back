package com.mgmoura.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPutRequestDto {
	
	private Integer idProduto;
	private String descricao;
	private Integer quantidadeMinima;
	private String dataCadastro; 
	private Double Valor;

}
