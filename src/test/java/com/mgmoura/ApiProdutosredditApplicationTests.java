package com.mgmoura;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiProdutosredditApplicationTests {

	@Test
	@Order(1)
	public void testProdutoPost() {
		fail("Não implementado!");
	}

	@Test
	@Order(2)
	public void testProdutoPut() {
		fail("Não implementado!");
	}
	
	@Test
	@Order(3)
	public void testProdutoGetAll() {
		fail("Não implementado!");
	}

	@Test
	@Order(4)
	public void testProdutoGetById() {
		fail("Não implementado!");
	}


	@Test
	@Order(5)
	public void testProdutoDelete() {
		fail("Não implementado!");
	}
	
	


}
