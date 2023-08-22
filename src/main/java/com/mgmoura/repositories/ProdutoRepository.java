package com.mgmoura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgmoura.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{	
	
	
	@Query("select count(m) from MovimentoEstoque m where m.produto.idProduto = :idProduto")
	Integer countByMovimentacao(@Param("idProduto") Integer idProduto);
	
	

}
