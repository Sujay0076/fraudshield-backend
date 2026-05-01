package com.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,length = 1000)
	private String reviewText;
	@Column(nullable = false)
	private double rating;
	
	@Column(nullable = false)
	private int reviewScore;
	@Column(nullable = false)
	private boolean isFake;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ReviewStatus status;
	
	@Column(length = 1000)
	private String fraudReason;
	
	
	public String getFraudReason() {
		return fraudReason;
	}
	public void setFraudReason(String fraudReason) {
		this.fraudReason = fraudReason;
	}
	public enum ReviewStatus{
		PENDING,
		REJECTED,
		APPROVED
	}
	
	public Review() {
		super();
	}
	public Review(String reviewText, double rating, int reviewScore, boolean isFake, User user, Product product) {
		super();
		this.reviewText = reviewText;
		this.rating = rating;
		this.reviewScore = reviewScore;
		this.isFake = isFake;
		this.user = user;
		this.product = product;
	}
	public Review(Long id, String reviewText, double rating, int reviewScore, boolean isFake, User user,
			Product product, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.reviewText = reviewText;
		this.rating = rating;
		this.reviewScore = reviewScore;
		this.isFake = isFake;
		this.user = user;
		this.product = product;
		this.createdAt = createdAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	public boolean isFake() {
		return isFake;
	}
	public void setFake(boolean isFake) {
		this.isFake = isFake;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	@PrePersist
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
		 if(this.status == null) {
		        this.status = ReviewStatus.PENDING;
		    }
	}
	public ReviewStatus getStatus() {
		return status;
	}
	public void setStatus(ReviewStatus status) {
		this.status = status;
	}
	
	
	

}
