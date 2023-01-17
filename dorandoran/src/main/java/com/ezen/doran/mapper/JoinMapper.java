package com.ezen.doran.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.Pagination;

@Mapper
public interface JoinMapper {
	

	List<JoinDTO> selectJoinList(Pagination pagination);

	int selectJoinCnt(Map<String, String> map);
	
	@Insert("INSERT INTO TB_JOIN VALUES ("
	+ "(SELECT IFNULL(MAX(A.JOIN_NO),0)+1 FROM TB_JOIN A)"
	+ ", #{joinTitle}"
	+ ", #{joinContent}"
	+ ", #{userNo}"
	+ ", #{joinCnt}"
	+ ", #{joinCd}"
	+ ", #{joinTypeCd}"
	+ ", #{joinDtm}"
	+ ", #{joinPlace}"
	+ ", now()"
	+ ", #{doneYn})")
	public void insertJoin(JoinDTO joinDTO);
	
	@Select("SELECT "
			+ "A.JOIN_NO"
			+ " ,A.JOIN_TITLE"
			+ " ,A.JOIN_CONTENT"
			+ " ,A.USER_NO"
			+ " ,B.USER_NM"
			+ " ,A.JOIN_CNT"
			+ " ,A.JOIN_CD"
			+ " ,A.JOIN_TYPE_CD"
			+ " ,A.JOIN_DTM"
			+ " ,A.JOIN_PLACE"
			+ " ,A.INPUT_DTM"
			+ " ,A.DONE_YN"
			+ " FROM TB_JOIN A"
			+ " INNER JOIN TB_USER B"
			+ " ON A.USER_NO = B.USER_NO"
			+ " WHERE A.JOIN_NO = #{joinNo}")
	JoinDTO selectJoin(int joinNo);
	
	@Update("UPDATE TB_JOIN SET"
			+ " JOIN_TITLE = #{joinTitle}"
			+ ", JOIN_CONTENT = #{joinContent}"
			+ ", JOIN_CD = #{joinCd}"
			+ ", JOIN_TYPE_CD = #{joinTypeCd}"
			+ ", JOIN_CNT = #{joinCnt}"
			+ ", JOIN_DTM = #{joinDtm}"
			+ ", DONE_YN = #{doneYn}"
			+ ", JOIN_PLACE = #{joinPlace}"
			+ " WHERE JOIN_NO = #{joinNo}")
	void updateJoin(JoinDTO joinDTO);
	
	@Delete("DELETE FROM TB_JOIN WHERE JOIN_NO = #{joinNo}")
	void deleteJoin(int joinNo);
}
