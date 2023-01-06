package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.ReDTO;

@Mapper
public interface ReMapper {
	
	@Insert("INSERT INTO TB_RE VALUES ("
			+ "(SELECT IFNULL(MAX(A.RE_NO), 0) + 1 FROM TB_RE A),"
			+ "#{boardNo},"
			+ "#{boardCd},"
			+ "#{userNo},"
			+ "#{reContent},"
			+ "#{parentReNo},"
			+ "now(),"
			+ "null )")
	void insertRe(ReDTO reDTO);
	
	@Select("SELECT "
			+ " A.RE_NO"
			+ " ,A.BOARD_NO"
			+ " ,A.BOARD_CD"
			+ " ,B.USER_NICK"
			+ " ,A.USER_NO"
			+ " ,A.RE_CONTENT"
			+ " ,A.PARENT_RE_NO"
			+ " ,A.INPUT_DTM"
			+ "	,A.UPDATE_DTM"
			+ " FROM TB_RE A"
			+ " INNER JOIN TB_USER B"
			+ " ON A.USER_NO = B.USER_NO"
			+ " WHERE BOARD_NO = #{boardNo} AND BOARD_CD = #{boardCd}")
	List<ReDTO> selectReList(ReDTO reDTO);
	
	
	@Update("UPDATE TB_RE SET"
			+ " RE_CONTENT = #{reContent},"
			+ " UPDATE_DTM = now()"
			+ " WHERE RE_NO = #{reNo}")
	void updateRe(ReDTO reDTO);
	
	
	@Delete("DELETE FROM TB_RE WHERE RE_NO = #{reNo}")
	void deleteRe(int reNo);
}
