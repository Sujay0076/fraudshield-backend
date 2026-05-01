package com.app.dto;

import org.springframework.stereotype.Component;

@Component
public class ReviewRequestDTO {
	private Long userId;
	private Long productId;
	private String reviewText;
	private double rating;
	public ReviewRequestDTO() {
		super();
	}
	public ReviewRequestDTO(Long userId, Long productId, String reviewText, double rating) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.reviewText = reviewText;
		this.rating = rating;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	
	
}
