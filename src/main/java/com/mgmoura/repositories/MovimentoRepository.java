package com.mgmoura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgmoura.entities.MovimentoEstoque;

@Repository
public interface MovimentoRepository extends JpaRepository<MovimentoEstoque, Integer>{

}
