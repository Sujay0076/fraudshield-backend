package com.app.serviceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.app.entity.Review;
import com.app.repository.ReviewRepository;
import com.app.service.ReviewDetectionService;

@Service
public class ReviewDetectionServiceImpl implements ReviewDetectionService {
	
	private ReviewRepository reviewRepository;

	
	public ReviewDetectionServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository= reviewRepository;
		
	}
	@Override
	public int calculateFakeScore(Review review) {
		int count =0;
		if(reviewRepository.existsByReviewText(review.getReviewText())) {
			count += 35;
		}
		if(reviewRepository.existsByUserIdAndProductId(review.getUser().getId(), review.getProduct().getId())) {
			count += 40;
		}
		
		List<Review> userReviews = reviewRepository.findByUserId(review.getUser().getId());
		
		LocalDateTime now  = LocalDateTime.now();
		LocalDateTime threeMinAgo = now.minusMinutes(3);
		
		long reviewCount = userReviews.stream().filter(r -> r.getCreatedAt() != null)
							.filter(r -> r.getCreatedAt().isAfter(threeMinAgo))
							.count();
		long sameProductReviewCount = reviewRepository
		        .countByUserIdAndProductId(
		            review.getUser().getId(),
		            review.getProduct().getId()
		        );

		if(sameProductReviewCount >= 3) {
		    count += 25;
		}
		
		if(reviewCount >= 5) {
			count += 30;
		}
		
		return Math.min(count, 100);
		
	}
	@Override
	public String getFraudReason(Review review) {
		StringBuilder reason = new StringBuilder();
		
		if(reviewRepository.existsByReviewText(review.getReviewText())) {
			reason.append("Duplicate Review Text, ");
		}
		if(reviewRepository.existsByUserIdAndProductId(review.getUser().getId(), review.getProduct().getId())) {
			reason.append("Same User Reviewed Same Product, ");
		}

	    List<Review> userReviews = reviewRepository.findByUserId(review.getUser().getId());

	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime threeMinAgo = now.minusMinutes(3);

	    long reviewCount = userReviews.stream()
	            .filter(r -> r.getCreatedAt() != null)
	            .filter(r -> r.getCreatedAt().isAfter(threeMinAgo))
	            .count();

	    if(reviewCount >= 5) {
	        reason.append("Spam Frequency Detected, ");
	    }

	    long sameProductReviewCount = reviewRepository.countByUserIdAndProductId(
	            review.getUser().getId(),
	            review.getProduct().getId());

	    if(sameProductReviewCount >= 3) {
	        reason.append("Repeated Product Reviews, ");
	    }

	    if(reason.length() == 0) {
	        return "No Fraud Detected";
	    }

	    return reason.substring(0, reason.length() - 2);
		
		
	}
}
