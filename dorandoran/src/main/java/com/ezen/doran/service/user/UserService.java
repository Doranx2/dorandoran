package com.ezen.doran.service.user;

import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.entity.User;

public interface UserService {
	void join(User user);
	
	User idCheck(User user);
	
	User nickCheck(User user);
	
	//아이디 찾기
	User idfCheck(User user); 
	
	//비밀번호 찾기
	User pwfCheck(User user);
	
	//임시 비밀번호로 회원 비밀번호 변경하기
	User editPw(User user);

	void updateTempPw(User user);
}