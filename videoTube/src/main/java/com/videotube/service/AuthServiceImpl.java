package com.videotube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.videotube.config.JwtTokenProvider;
import com.videotube.dto.LoginDTO;
import com.videotube.entity.User;
@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private AuthenticationManager authmanager;
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;

	@Override
	public String login(LoginDTO logindto) {
		Authentication authentication= authmanager.authenticate(new  UsernamePasswordAuthenticationToken(
				logindto.getUsername(), logindto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		 String token = jwtTokenProvider.generateToken(authentication);
		return token;
	}

}
