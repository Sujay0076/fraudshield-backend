package com.app.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Product;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}
