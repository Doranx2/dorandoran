package com.ezen.doran.service.temp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.mapper.TempMapper;
import com.ezen.doran.service.temp.tempService;

@Service
public class tempServiceImpl implements tempService{

	@Autowired
	TempMapper mapper;
	
	@Override
	public UserDTO selectUser(String userId) {
		return mapper.selectUser(userId);
	}

}
