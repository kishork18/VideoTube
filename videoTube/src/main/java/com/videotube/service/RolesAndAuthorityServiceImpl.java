package com.videotube.service;

import org.springframework.stereotype.Service;

import com.videotube.models.RolesAndAuthority;
import com.videotube.repository.RolesAndAuthorityRepository;
@Service
public class RolesAndAuthorityServiceImpl implements RolesAndAuthorityService{
	private RolesAndAuthorityRepository rar;
	

	public RolesAndAuthorityServiceImpl(RolesAndAuthorityRepository rar) {
		super();
		this.rar = rar;
	}


	@Override
	public RolesAndAuthority getRolesAndAuthority(String name) {
		
		return rar.findByName(name).get();
	}

}
