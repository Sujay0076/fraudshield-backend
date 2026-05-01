package com.app.serviceImpl;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.dto.FraudUserDTO;
import com.app.dto.ReviewRequestDTO;
import com.app.dto.ReviewStatsDTO;
import com.app.entity.Product;
import com.app.entity.Review;
import com.app.entity.Review.ReviewStatus;
import com.app.entity.User;
import com.app.repository.ProductRepository;
import com.app.repository.ReviewRepository;
import com.app.repository.UserRepository;
import com.app.service.ReviewDetectionService;
import com.app.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private UserRepository userRepository;
	private ReviewRepository reviewRepository;
	private ReviewDetectionService reviewDetectionService;
	private ProductRepository productRepository;
	
	public ReviewServiceImpl(UserRepository userRepository,ProductRepository productRepository,ReviewRepository reviewRepository,ReviewDetectionService reviewDetectionService) {
		this.productRepository = productRepository;
		this.reviewDetectionService = reviewDetectionService;
		this.reviewRepository = reviewRepository;
		this.userRepository = userRepository;
	}
	
	
	
	@Override
	public Review submitReview(ReviewRequestDTO reviewRequestDTO,String email) {		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));

		Product product = productRepository.findById(reviewRequestDTO.getProductId())
		        .orElseThrow(() -> new RuntimeException("Product not found"));
		Review review = new Review();
		review.setReviewText(reviewRequestDTO.getReviewText());
		review.setRating(reviewRequestDTO.getRating());
		review.setUser(user);
		review.setProduct(product);
		int score = reviewDetectionService.calculateFakeScore(review);
		review.setReviewScore(score);
		review.setFake(score >= 60);
		review.setFraudReason(reviewDetectionService.getFraudReason(review));
		review.setStatus(ReviewStatus.PENDING);
		return reviewRepository.save(review);
	}
	
	@Override
	public List<Review> getAllReviewByFakeTrue() {
		return reviewRepository.findByIsFakeTrue();
	}
	
	@Override
	public ReviewStatsDTO getStats() {
		
		long fakeReviews = reviewRepository.countByIsFakeTrue();
		long genuineReviews = reviewRepository.countByIsFakeFalse();
		long totalReviews = reviewRepository.count();
		double fraudPercentage = totalReviews == 0
		        ? 0
		        : ((double) fakeReviews / totalReviews) * 100;
		
		ReviewStatsDTO reviewStatsDTO = new ReviewStatsDTO();
		reviewStatsDTO.setFakeReviews(fakeReviews);
		reviewStatsDTO.setGenuineReviews(genuineReviews);
		reviewStatsDTO.setTotalReviews(totalReviews);
		reviewStatsDTO.setFraudPercentage(fraudPercentage);
		return reviewStatsDTO;
	}
	
	@Override
	public List<FraudUserDTO> getTopFraudUser() {
		List<Object[]> results = reviewRepository.findTopFraudUsers();
		
		return results.stream().
				map(obj -> {
					FraudUserDTO dto = new FraudUserDTO();
					dto.setUserId((Long) obj[0]);
					dto.setUserName((String) obj[1]);
					dto.setFakeReviewCount((Long) obj[2]);
					
					return dto;
				}).toList();
	}
	
	@Override
	public Review approveReview(Long reviewId) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new RuntimeException("Review not found"));
			review.setStatus(ReviewStatus.APPROVED);
		return reviewRepository.save(review);
	}
	
	@Override
	public Review rejectedReview(Long reviewId) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new RuntimeException("Review not found"));
			review.setStatus(ReviewStatus.REJECTED);
		return reviewRepository.save(review);
	}
	
	@Override
	public Page<Review> getAllReviews(int page, int size) {
		Pageable pageable =  PageRequest.of(page, size);
		return reviewRepository.findAll(pageable);
	}
	@Override
	public List<Review> getReviewsByFake(boolean isFake) {
		// TODO Auto-generated method stub
		return reviewRepository.findByIsFakeTrue();
	}
	@Override
	public List<Review> getAllApprovedReviews(String status) {
		Review.ReviewStatus reviewStatus=  ReviewStatus.valueOf(status.toUpperCase());
		
		return reviewRepository.findByStatus(reviewStatus);
	}
	
	@Override
	public List<Review> getAllReviewsByUserEmail(String email) {
		return reviewRepository.findByUserEmail(email);
	}
}
