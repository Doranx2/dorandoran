package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.Pagination;

@Mapper
public interface HelpMapper {
	
	@Insert("INSERT INTO TB_HELP VALUES ("
			+ "(SELECT IFNULL(MAX(A.HELP_NO),0)+1 FROM TB_HELP A)"
			+ ", #{helpTitle}"
			+ ", #{helpContent}"
			+ ", #{helpPlace}"
			+ ", now()"
			+ ", #{doneYn}"
			+ ", #{userNo}"
			+ ", #{imageNm} )")
	void insertHelp(HelpDTO helpDTO);

	@Select("SELECT * FROM TB_HELP"
			+ " WHERE HELP_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+ " OR HELP_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')"
			+ " OR HELP_PLACE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+ " ORDER BY INPUT_DTM DESC"
			+ " LIMIT #{pageSize} OFFSET #{startIndex}")
	List<HelpDTO> selectHelpList(Pagination pagination);
	
	@Select("SELECT COUNT(*) FROM TB_HELP"
			+ " WHERE HELP_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+ " OR HELP_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')"
			+ " OR HELP_PLACE LIKE CONCAT('%', #{searchKeyword}, '%')")
	int selectPlayCnt(String searchKeyword);
	
	@Select("SELECT "
			+ "A.HELP_NO"
			+ ",A.HELP_TITLE"
			+ ",A.HELP_CONTENT"
			+ ",A.HELP_PLACE"
			+ ",A.INPUT_DTM"
			+ ",A.DONE_YN"
			+ ",A.USER_NO"
			+ ",A.IMAGE_NM"
			+ ",(SELECT B.USER_NICK FROM TB_USER B WHERE B.USER_NO = A.USER_NO) AS USER_NICK "
			+ "FROM TB_HELP A WHERE HELP_NO = #{helpNo}")
	HelpDTO selectHelp(int helpNo);
	
	@Update("UPDATE TB_HELP SET"
			+ "  HELP_TITLE = #{helpTitle}"
			+ ", HELP_CONTENT = #{helpContent}"
			+ ", HELP_PLACE = #{helpPlace}"
			+ ", DONE_YN = #{doneYn}"
			+ ", IMAGE_NM = #{imageNm}"
			+ " WHERE HELP_NO = #{helpNo}")
	void updateHelp(HelpDTO helpDTO);
	
	@Delete("DELETE FROM TB_HELP WHERE HELP_NO = #{helpNo}")
	void deleteHelp(int helpNo);
	
	@Update("UPDATE TB_HELP"
			+ " SET "
			+ " DONE_YN = 'Y'"
			+ " WHERE HELP_NO = #{helpNo}")
	void updateDoneYn(int helpNo);
}
