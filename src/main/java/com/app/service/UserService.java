package com.app.service;

import com.app.dto.RegisterRequestDTO;
import com.app.entity.User;

public interface UserService {
	User userRegister(RegisterRequestDTO registerRequestDTO);
}
