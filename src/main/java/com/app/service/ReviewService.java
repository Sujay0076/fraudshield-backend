package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.dto.FraudUserDTO;
import com.app.dto.ReviewRequestDTO;
import com.app.dto.ReviewStatsDTO;
import com.app.entity.Review;

public interface ReviewService {
	Review submitReview(ReviewRequestDTO reviewRequestDTO,String email);
	
	List<Review> getAllReviewByFakeTrue();
	
	ReviewStatsDTO getStats();
	
	List<FraudUserDTO> getTopFraudUser();
	Review approveReview(Long reviewId);
	Review rejectedReview(Long reviewId);
	
	Page<Review> getAllReviews(int page,int size);
	List<Review> getReviewsByFake(boolean isFake);
	List<Review> getAllApprovedReviews(String reviewStatus);
	
	List<Review> getAllReviewsByUserEmail(String email);

}
