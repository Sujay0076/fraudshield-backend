package com.app.dto;



public class ReviewStatsDTO {
	private long totalReviews;
	private long fakeReviews;
	private long genuineReviews;
	private double fraudPercentage;
	
	public ReviewStatsDTO() {
		
	}
	
	public ReviewStatsDTO(long totalReviews, long fakeReviews, long genuineReviews, double fraudPercentage) {
		super();
		this.totalReviews = totalReviews;
		this.fakeReviews = fakeReviews;
		this.genuineReviews = genuineReviews;
		this.fraudPercentage = fraudPercentage;
	}
	public long getTotalReviews() {
		return totalReviews;
	}
	public void setTotalReviews(long totalReviews) {
		this.totalReviews = totalReviews;
	}
	public long getFakeReviews() {
		return fakeReviews;
	}
	public void setFakeReviews(long fakeReviews) {
		this.fakeReviews = fakeReviews;
	}
	public long getGenuineReviews() {
		return genuineReviews;
	}
	public void setGenuineReviews(long genuineReviews) {
		this.genuineReviews = genuineReviews;
	}
	public double getFraudPercentage() {
		return fraudPercentage;
	}
	public void setFraudPercentage(double fraudPercentage) {
		this.fraudPercentage = fraudPercentage;
	}
	
	

}
