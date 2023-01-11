package com.ezen.doran.service.user;

import com.ezen.doran.entity.User;

public interface UserService {
	void join(User user);
	
	User idCheck(User user);
	
	/* User idfCheck(User user); */

}
