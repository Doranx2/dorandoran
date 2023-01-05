package com.ezen.doran.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ezen.doran.dto.UserDTO;

@Mapper
public interface TempMapper {


	@Select("SELECT * FROM TB_USER WHERE USER_ID = #{userId}")
	UserDTO selectUser(String userId);
}
