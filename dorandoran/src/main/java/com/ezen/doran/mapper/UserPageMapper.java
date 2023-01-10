package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.UserDTO;

@Mapper
public interface UserPageMapper {
	
	@Select("SELECT * FROM TB_USER WHERE USER_ID = #{userId}")
	UserDTO selectUser(String userId);
	
	@Update("UPDATE tb_user "
			+ "SET USER_EMAIL = #{userEmail},"
			+ "	   USER_TEL = #{userTel},"
			+ "	   USER_NICK = #{userNick},"
			+ "    USER_LOC = #{userLoc} "
			+ "WHERE USER_ID = #{userId}")
	void updateUser(UserDTO UserDTO);
	
	@Delete("Delete from tb_user "
			+ "Where joinNo = #{joinNo}")
	int DeleteUser(int joinNo);
	
	
	@Select("SELECT * FROM tb_play WHERE USER_NO = #{userNo}")
	List<PlayDTO> selectMyPlayList();

}
