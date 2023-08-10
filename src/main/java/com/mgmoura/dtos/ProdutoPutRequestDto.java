package com.mgmoura.dtos;

import java.util.Date;

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
	private Date dataCadastro; 
	private Double Valor;

}
