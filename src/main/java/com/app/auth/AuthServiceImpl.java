package com.app.auth;

import org.springframework.stereotype.Service;

import com.app.dto.AuthResponseDTO;
import com.app.dto.LoginRequestDTO;
import com.app.entity.User;
import com.app.jwt.JwtService;
import com.app.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	private UserRepository userRepository;
	private JwtService jwtService;
	
	public AuthServiceImpl(UserRepository userRepository,JwtService jwtService) {
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}
	
	@Override
	public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
		User user = userRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new RuntimeException("Invalid Email"));
		
		if(!user.getPassword().equals(loginRequestDTO.getPassword())) {
			throw new RuntimeException("Invalid password");
		}
		
		String token = jwtService.generateToken(user.getEmail(),user.getRole().name());
		
		return new AuthResponseDTO(token);
	}
}
