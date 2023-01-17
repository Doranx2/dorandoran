package com.ezen.doran.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ezen.doran.entity.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserIdAndUserPw(
			@Param("userId")String userId,
			@Param("userPw")String userPw);
	
	@Query(value="SELECT IFNULL(MAX(U.USER_NO), 0) + 1\r\n"
			+ "		FROM TB_USER U", 
		   nativeQuery=true)
	int getNextUserNo();
	
	Optional<User> findByUserId(@Param("userId") String userId); 
	
	Optional<User> findByUserPw(@Param("userPw") String userPw); 
	
	Optional<User> findByUserNick(@Param("userNick") String userNick);
	
	Optional<User> findByUserNm(@Param("userNm") String userNm); 
	
	/* Optional<User> findByUserEmail(@Param("userEmail") String userEmail); */
	
	//아이디 찾을 때 (이름 + 이메일 입력하여 찾기)
	Optional<User> findByUserNmAndUserEmail(
			@Param("userNm")String userNm, 
			@Param("userEmail")String userEmail);
	
	//비밀번호 찾을 때 (아이디 + 이메일 입력)
	Optional<User> findByUserIdAndUserEmail(
			@Param("userId")String userId,
			@Param("userEmail")String userEmail);
	
	@Modifying
	@Query(value="UPDATE TB_USER"
			+ "		SET USER_PW = :userPw"
			+ "		WHERE USER_ID = :userId", nativeQuery=true)
	void updateTempPw(@Param("userId") String userId, @Param("userPw") String tempLoginPasswd);
	
	
	
	//임시 비밀번호로 업데이트
	
	
}
