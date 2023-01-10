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
	
	@Select("SELECT * FROM TB_HELP WHERE HELP_NO = #{helpNo}")
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
}
