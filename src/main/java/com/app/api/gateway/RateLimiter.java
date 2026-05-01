package com.app.api.gateway;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class RateLimiter {
	private final int LIMIT = 50;
	private  long time = 60 * 1000;
	Map<String,List<Long>> userRequest = new ConcurrentHashMap<>();
	public boolean isAllowed(String userId) {
		
		 long currentTime = System.currentTimeMillis(); 
		
		List<Long> timestamps = userRequest.getOrDefault(userId, new ArrayList<>());
		
		timestamps.removeIf(t -> (currentTime-t) > time);
		
		if(timestamps.size() >= LIMIT) {
			return false;
		}
		
		timestamps.add(currentTime);
		userRequest.put(userId,timestamps);
		
		return true;
		
		
	}

}
