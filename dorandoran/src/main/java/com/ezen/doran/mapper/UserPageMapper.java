package com.ezen.doran.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.UserDTO;

@Mapper
public interface UserPageMapper {
	
	@Select("SELECT * FROM TB_USER WHERE USER_ID = #{userId}")
	UserDTO selectUser(String userId);
	
	@Update("Update tb_user "
			+ "Set "
			+ "Set "
			+ "Set ")
	void updateUser(UserDTO UserDTO);
	
	@Delete("Delete from tb_user "
			+ "Where joinNo = #{joinNo}")
	int DeleteUser(int joinNo);

}
