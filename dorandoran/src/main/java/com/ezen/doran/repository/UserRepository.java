package com.ezen.doran.repository;

import org.springframework.data.repository.query.Param;

import com.ezen.doran.entity.User;

public interface UserRepository {
	User findByUserIdAndUserPw(
			@Param("userId")String userId,
			@Param("userPw")String userPw);

}
