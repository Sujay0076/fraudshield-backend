package com.app.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.FraudUserDTO;
import com.app.dto.ReviewRequestDTO;
import com.app.dto.ReviewStatsDTO;
import com.app.entity.Review;
import com.app.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private ReviewService reviewService;
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@PostMapping
	public ResponseEntity<Review> submitReview(@RequestBody ReviewRequestDTO reviewRequestDTO, Authentication auth){
		return ResponseEntity.ok().body(reviewService.submitReview(reviewRequestDTO,auth.getName()));
	}
	
	@GetMapping("/fake")
	public ResponseEntity<List<Review>> getAllReviewsByFake(){
		return ResponseEntity.ok().body(reviewService.getAllReviewByFakeTrue());
	}
	
	@GetMapping("/stats")
	public ResponseEntity<ReviewStatsDTO> getStats(){
		return ResponseEntity.ok().body(reviewService.getStats());
	}
	
	@GetMapping("/top-fraud-users")
	public ResponseEntity<List<FraudUserDTO>> getTopFraudUsers(){
		return ResponseEntity.ok().body(reviewService.getTopFraudUser());
	}
	
	@PutMapping("/{id}/approve")
	public ResponseEntity<Review> approveReview(@PathVariable Long id){
		return ResponseEntity.ok().body(reviewService.approveReview(id));
	}
	
	@PutMapping("/{id}/reject")
	public ResponseEntity<Review> rejectReview(@PathVariable Long id){
		return ResponseEntity.ok().body(reviewService.rejectedReview(id));
	}
	
	@GetMapping
	public ResponseEntity<Page<Review>> getAllReviews(
													@RequestParam(defaultValue = "0") int page,
													@RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok().body(reviewService.getAllReviews(page, size));
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<Review>> getFakeReviews(@RequestParam boolean isFake){
		return ResponseEntity.ok().body(reviewService.getAllReviewByFakeTrue());
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<Review>> getApprovedReviews(@RequestParam String reviewStatus){
		return ResponseEntity.ok().body(reviewService.getAllApprovedReviews(reviewStatus));
	}
	
	@GetMapping("/my-reviews")
	public ResponseEntity<List<Review>> getAllReviewsByUserEmail(Authentication auth){
		String email = auth.getName();
		return ResponseEntity.ok().body(reviewService.getAllReviewsByUserEmail(email));
	}
}
