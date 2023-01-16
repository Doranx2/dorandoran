package com.ezen.doran.service;

import com.ezen.doran.entity.User;

public interface UserService {
	void join(User user);
	
	User idCheck(User user);
	
	User nickCheck(User user);
	
	//아이디 찾기
	User idfCheck(User user); 
	
	//비밀번호 찾기
	User pwfCheck(User user);
	
	
}
