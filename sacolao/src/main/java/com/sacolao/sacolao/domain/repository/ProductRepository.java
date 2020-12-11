package com.sacolao.sacolao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sacolao.sacolao.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
