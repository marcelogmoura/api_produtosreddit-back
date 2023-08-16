package com.mgmoura.dtos;

import lombok.Data;

@Data
public class MovimentoPostRequestDto {
	
	private Integer idProduto;
	private String DataMovimento;
	private Integer quantidade;
	private String tipoMovimento;

}
