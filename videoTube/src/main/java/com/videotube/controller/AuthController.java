package com.videotube.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videotube.dto.JWTAuthResponse;
import com.videotube.dto.LoginDTO;
import com.videotube.entity.RolesAndAuthority;
import com.videotube.entity.User;
import com.videotube.service.AuthService;
import com.videotube.service.RolesAndAuthorityService;
import com.videotube.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class AuthController {
   
	private UserService us;
    private BCryptPasswordEncoder passwordEncoder;
    private RolesAndAuthorityService rolesAndAuthSer;
    private AuthService authService;
    

	

    public AuthController(UserService us, BCryptPasswordEncoder passwordEncoder,
			RolesAndAuthorityService rolesAndAuthSer,AuthService authService) {
		super();
		this.us = us;
		this.passwordEncoder = passwordEncoder;
		this.rolesAndAuthSer = rolesAndAuthSer;
		this.authService=authService;
	}




	@PostMapping("/register")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
    	user.setRegistrationDate(LocalDate.now());
    	String Hashpass= passwordEncoder.encode(user.getPassword());
    	user.setPassword(Hashpass);
    	user.setVideos(new ArrayList<>());
    	user.setComments(new ArrayList<>());
    	user.setLikes(new ArrayList<>());
    	user.setSubscriptions(new ArrayList<>());
    	user.setWatchHistory(new ArrayList<>());
    	Set<RolesAndAuthority> authset= user.getAuthSet();
    	Set<RolesAndAuthority> managedStateSet= new HashSet<>();
    	for(RolesAndAuthority role:authset) {
    		managedStateSet.add(rolesAndAuthSer.getRolesAndAuthority(role.getName()));
    	}
    	user.setAuthSet(managedStateSet);
    	User u= us.addUser(user);
    	return new ResponseEntity<User>(u, HttpStatus.CREATED);
    	
    }
	@GetMapping("/logged-in-customer")
	public ResponseEntity<User> getLoggedInCustomer(){
		User customer = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ResponseEntity<User>(customer, HttpStatus.OK);
	}
	@PostMapping("/signin")
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO logindto) {
		
		String token= authService.login(logindto);
		  JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
	        jwtAuthResponse.setAccessToken(token);
		return new ResponseEntity<JWTAuthResponse>(jwtAuthResponse, HttpStatus.ACCEPTED);
		
	}
}
