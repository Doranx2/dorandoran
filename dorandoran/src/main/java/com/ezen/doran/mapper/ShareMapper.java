package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.ShareDTO;

@Mapper
public interface ShareMapper {
	
	@Insert("INSERT INTO TB_SHARE VALUES ("
			+ " (SELECT IFNULL(MAX(A.SHARE_NO), 0)+1 FROM TB_SHARE A)"
			+ ", #{userNo}"
			+ ", #{shareTitle}"
			+ ", #{shareContent}"
			+ ", #{shareCat}"
			+ ", now())")
	void insertShare(ShareDTO shareDTO);
	
	@Select("SELECT A.SHARE_NO, A.SHARE_TITLE, A.SHARE_CONTENT, A.INPUT_DTM, B.USER_NM FROM TB_SHARE A INNER JOIN TB_USER B ON A.USER_NO = B.USER_NO"
			+" WHERE SHARE_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR SHARE_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" ORDER BY A.INPUT_DTM DESC"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<ShareDTO> selectShareList(Pagination pagination);
	
	@Select("SELECT COUNT(*) FROM TB_SHARE"
			+" WHERE SHARE_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR SHARE_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')")
	int selectShareCnt(String searchKeyword);
	
	@Select("SELECT * FROM TB_SHARE WHERE SHARE_NO = #{shareNo}")
	ShareDTO selectShare (int shareNo);
	
	@Update("UPDATE TB_SHARE SET "
			+ "SHARE_TITLE = #{shareTitle}"
			+ ", SHARE_CONTENT = #{shareContent}"
			+ ", SHARE_CAT = #{shareCat}"
			+ " WHERE SHARE_NO = #{shareNo}")
	void updateShare(ShareDTO shareDTO);
	
	@Delete("DELETE FROM TB_SHARE WHERE SHARE_NO = #{shareNo}")
	void deleteShare(int shareNo);
}
