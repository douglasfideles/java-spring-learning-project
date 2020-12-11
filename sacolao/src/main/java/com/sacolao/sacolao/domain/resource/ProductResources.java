package com.sacolao.sacolao.domain.resource;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sacolao.sacolao.domain.model.Product;
import com.sacolao.sacolao.domain.repository.ProductRepository;


@RestController
@RequestMapping(value = "/sacolao/produto")
public class ProductResources {
	
	@Autowired
	private ProductRepository productRepository;
	
	@CrossOrigin("*")
	@PostMapping(path = "/add")
	public ResponseEntity<Product> addProduto(@RequestBody Product product){
		Product newProduct = productRepository.save(product);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}
	
	@CrossOrigin("*")
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Product> getProduto(@PathVariable("id") long id){
		
		Product produto = productRepository.findById(id).orElse(null);
		if(produto == null) {
			return new ResponseEntity(produto, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(produto, HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@GetMapping(path = "/getAll")
	public ResponseEntity<List<Product>> getAllProdutos(){
		List<Product> lstProduct = productRepository.findAll();
		
		if(lstProduct.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Product>>(lstProduct, HttpStatus.OK);
		
	}
	
	@CrossOrigin("*")
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product produto){
		
		Product oldProduct = productRepository.findById(id).orElse(null);
		
		if(oldProduct == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
		BeanUtils.copyProperties(produto, oldProduct, "id");
		productRepository.save(oldProduct);
		return new ResponseEntity<Product>(oldProduct, HttpStatus.OK);
		
	}
	
	@CrossOrigin("*")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id){
		Product product = productRepository.findById(id).orElse(null);
		
		if(product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
		productRepository.delete(product);
		
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	
}
