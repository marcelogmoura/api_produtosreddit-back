package com.mgmoura;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.mgmoura.dtos.MovimentoPostRequestDto;
import com.mgmoura.dtos.ProdutoPostRequestDto;
import com.mgmoura.dtos.ProdutoPutRequestDto;
import com.mgmoura.dtos.ProdutoResponseDto;
import com.mgmoura.entities.Produto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiProdutosredditApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	// atributo para guardar o produto cadastrado nos testes
	private static Produto produto;

	@Test
	@Order(1)
	public void testProdutoPost() throws Exception {

		Faker faker = new Faker();

		ProdutoPostRequestDto dto = new ProdutoPostRequestDto();
		
		dto.setDescricao(faker.commerce().productName());
		dto.setQuantidadeMinima(faker.number().randomDigit());
		dto.setDataCadastro("2023-08-21");
		dto.setValor(Double.valueOf(faker.number().randomNumber(2, false)));

		MvcResult result = mockMvc.perform(
				post("/api/produtos/post").contentType("application/json").content(mapper.writeValueAsString(dto)))
				.andExpect(status().isCreated()).andReturn();

		String responseBody = result.getResponse().getContentAsString();
		ProdutoResponseDto response = mapper.readValue(responseBody, ProdutoResponseDto.class);

		// capturando o id do produto
		produto = response.getProduto();

	}

	@Test
	@Order(2)
	public void testProdutoPut() throws Exception{
		
		Faker faker = new Faker();

		ProdutoPutRequestDto dto = new ProdutoPutRequestDto();
		dto.setIdProduto(produto.getIdProduto());
		dto.setDescricao(faker.commerce().productName());
		dto.setQuantidadeMinima(faker.number().randomDigit());
		dto.setDataCadastro("2023-08-22");
		dto.setValor(Double.valueOf(faker.number().randomNumber(2, false)));
		
		mockMvc.perform(put("/api/produtos/put") // 
				.contentType("application/json") 
				.content(mapper.writeValueAsString(dto))) 
				.andExpect(status().isOk());
		
	}

	@Test
	@Order(3)
	public void testProdutoGetAll() throws Exception  {
		
		mockMvc.perform(get("/api/produtos/getAll")).andExpect(status().isOk());
		
	}

	@Test
	@Order(4)
	public void testProdutoGetById() throws Exception {
		
		mockMvc.perform(get("/api/produtos/" + produto.getIdProduto())) 
						.andExpect(status().isOk());
		
	}

	
	@Test()
	@Order(5)
	public void testMovimentoPost() throws Exception{
		
		Faker faker = new Faker();
		
		MovimentoPostRequestDto dto = new MovimentoPostRequestDto();
		dto.setIdProduto(produto.getIdProduto());
		dto.setDataMovimento("2023-08-22");
		dto.setQuantidade(faker.number().randomDigit());
		dto.setTipoMovimento("2");
		
		mockMvc.perform(post("/api/movimentoEstoque/post") 
				.contentType("application/json") 
				.content(mapper.writeValueAsString(dto))) 
				.andExpect(status().isCreated());
	}
	
	@Test
	@Order(6)
	public void testProdutoDelete() throws Exception {
		
		testProdutoPost(); 
		
		mockMvc.perform(delete("/api/produtos/" + produto.getIdProduto())) 
		.andExpect(status().isOk());
	}

}
