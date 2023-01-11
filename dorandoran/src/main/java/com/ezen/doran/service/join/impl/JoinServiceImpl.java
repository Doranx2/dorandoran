package com.ezen.doran.service.join.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.mapper.JoinMapper;
import com.ezen.doran.service.join.JoinService;

@Service
public class JoinServiceImpl implements JoinService{
	@Autowired
	JoinMapper mapper;
}
