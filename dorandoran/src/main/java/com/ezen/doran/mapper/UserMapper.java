package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.entity.User;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM TB_USER WHERE USER_NO = #{userNo}")
	User getUser(int userNo);
	
	@Select("SELECT * FROM TB_USER")
	List<User> getUserList();
	 

	//회원가입
	@Insert("INSERT INTO TB_USER("
			+ "USER_NO,"
			+ "USER_NM,"
			+ "USER_ID,"
			+ "USER_PW,"
			+ "USER_AGE,"
			+ "USER_GEN,"
			+ "USER_EMAIL,"
			+ "USER_LOC,"
			+ "USER_NICK,"
			+ "USER_TEL,"
			+ "USER_ROLE,"
			+ "INPUT_DTM"
			+ ") VALUES ("
			+ "(SELECT IFNULL(MAX(A.USER_NO), 0) + 1 FROM TB_USER A),"
			+ "#{userNm},"
			+ "#{userId},"
			+ "#{userPw},"
			+ "#{userAge},"
			+ "#{userGen},"
			+ "#{userEmail},"
			+ "#{userLoc},"
			+ "#{userNick},"
			+ "#{userTel}"
			+ "#{userRole},"
			+ "NOW()"
			)
	void insertUser (User user);

    @Select("SELECT USER_NICK, USER_NM FROM TB_USER WHERE USER_NO = #{userNo}")
	UserDTO getUserNm(int sendUserNo);
    
    @Delete("DELETE FROM TB_USER WHERE USER_NO = #{userNo}")
    void deleteUser(int userNo);
	

/*
	//아이디 찾기
	@Select("SELECT * FROM TB_USER" 
			 		+ "WHERE USER_NM=#{userNm} " 
			 		+ "AND USER_EMAIL=#{userEmail}")
	 (@Param("userNm")String userNm,
			 @Param("userEmail")String userEmail);
	 

	
	//비밀번호 찾기
	@Select("SELECT * FROM TB_USER WHERE USER_ID=#{userId}")
*/
}
