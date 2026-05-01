package com.app.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByProductId(Long productId);
	boolean existsByReviewText(String reviewText);
	
	boolean existsByUserIdAndProductId(Long userId,Long productId);
	
	List<Review> findByUserId(Long userId);
	long countByUserIdAndProductId(Long userId, Long productId);
	
	List<Review> findByIsFakeTrue();
	
	long countByIsFakeTrue();
	long countByIsFakeFalse();
	
	@Query("""
			SELECT r.user.id, r.user.name, COUNT(r)
			FROM Review r
			WHERE r.isFake = true
			GROUP BY r.user.id, r.user.name
			ORDER BY COUNT(r) DESC
			""")
			List<Object[]> findTopFraudUsers();
			
	List<Review> findByStatus(Review.ReviewStatus reviewStatus);
	
	List<Review> findByUserEmail(String email);
}
