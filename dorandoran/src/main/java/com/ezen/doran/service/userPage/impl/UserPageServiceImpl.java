package com.ezen.doran.service.userPage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.mapper.UserPageMapper;
import com.ezen.doran.service.userPage.UserPageService;

@Service
public class UserPageServiceImpl implements UserPageService {
	
	@Autowired
	UserPageMapper mapper;

}
