package com.app.auth;

import com.app.dto.AuthResponseDTO;
import com.app.dto.LoginRequestDTO;

public interface AuthService {
	
	AuthResponseDTO login(LoginRequestDTO loginRequestDTO);

}
