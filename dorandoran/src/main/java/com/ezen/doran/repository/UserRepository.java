package com.ezen.doran.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ezen.doran.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserIdAndUserPw(
			@Param("userId")String userId,
			@Param("userPw")String userPw);
	
	@Query(value="SELECT IFNULL(MAX(U.USER_NO), 0) + 1\r\n"
			+ "		FROM TB_USER U", 
		   nativeQuery=true)
	int getNextUserNo();
	
	Optional<User> findByUserId(@Param("userId") String userId); 
	
	Optional<User> findByUserNm(@Param("userNm") String userNm); 
	
	Optional<User> findByUserEmail(@Param("userEmail") String userEmail); 
	
	
}
