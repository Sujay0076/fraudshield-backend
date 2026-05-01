package com.app.service;

import com.app.entity.Review;

public interface ReviewDetectionService {
	public int calculateFakeScore(Review review);
	public String getFraudReason(Review review);
	
}
