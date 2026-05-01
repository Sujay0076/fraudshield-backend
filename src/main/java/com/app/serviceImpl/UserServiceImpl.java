package com.app.serviceImpl;

import org.springframework.stereotype.Service;

import com.app.dto.RegisterRequestDTO;
import com.app.entity.User;
import com.app.entity.User.Role;
import com.app.repository.UserRepository;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public User userRegister(RegisterRequestDTO registerRequestDTO) {
		User user = new User();
		user.setName(registerRequestDTO.getName());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(registerRequestDTO.getPassword());
		user.setRole(Role.USER);
		return userRepository.save(user);
		
	}
}
