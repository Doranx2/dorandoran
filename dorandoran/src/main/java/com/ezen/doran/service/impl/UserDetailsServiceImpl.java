package com.ezen.doran.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezen.doran.entity.CustomUserDetails;
import com.ezen.doran.entity.User;
import com.ezen.doran.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	//계정없음
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(userRepository.findByUserId(username).isEmpty()) {
			return null;
		} else {
			User user = userRepository.findByUserId(username).get();
			
			return CustomUserDetails.builder()
									.user(user)
									.build();
		}
	}

}