package com.app.dto;

public class FraudUserDTO {
	
	private Long userId;
	private String userName;
	private long fakeReviewCount;
	
	public FraudUserDTO() {
		
	}
	public FraudUserDTO(Long userId,String userName,long fakeReviewCount) {
		this.userId = userId;
		this.userName = userName;
		this.fakeReviewCount = fakeReviewCount;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getFakeReviewCount() {
		return fakeReviewCount;
	}
	public void setFakeReviewCount(long fakeReviewCount) {
		this.fakeReviewCount = fakeReviewCount;
	}
	
	

}
