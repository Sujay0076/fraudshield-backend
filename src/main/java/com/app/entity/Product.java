package com.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private String brand;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	public Product(Long id, String productName, String category, String brand, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.productName = productName;
		this.category = category;
		this.brand = brand;
		this.createdAt = createdAt;
	}
	public Product(String productName, String category, String brand, LocalDateTime createdAt) {
		super();
		this.productName = productName;
		this.category = category;
		this.brand = brand;
		this.createdAt = createdAt;
	}
	public Product() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	@PrePersist
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}
	
	
}
