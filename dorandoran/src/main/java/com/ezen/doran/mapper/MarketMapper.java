package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;

@Mapper
public interface MarketMapper {
	
	@Insert("INSERT INTO TB_MARKET VALUES ("
			+ "(SELECT IFNULL(MAX(A.MARKET_NO),0)+1 FROM TB_MARKET A)"
			+ ", #{userNo}"
			+ ", #{marketContent}"
			+ ", #{price}"
			+ ", #{prodNm}"
			+ ", now()"
			+ ", #{status}"
			+ ", #{imageNm})")
	void insertMarket(MarketDTO marketDTO);
	
	@Select("SELECT * FROM TB_MARKET"
			+ " WHERE PROD_NM LIKE CONCAT('%', #{searchKeyword}, '%')"
			+ " ORDER BY INPUT_DTM DESC"
			+ " LIMIT #{pageSize} OFFSET #{startIndex}")
	List<MarketDTO> selectMarketList(Pagination pagination);
	
	@Select("SELECT COUNT(*) FROM TB_MARKET"
			+" WHERE PROD_NM LIKE CONCAT('%', #{searchKeyword}, '%')")
	int selectPlayCnt(String searchKeyword);
	
	@Select("SELECT "
			+ "A.MARKET_NO"
			+ ",A.USER_NO"
			+ ",A.MARKET_CONTENT"
			+ ",A.PRICE"
			+ ",A.PROD_NM"
			+ ",A.INPUT_DTM"
			+ ",A.STATUS"
			+ ",A.IMAGE_NM"
			+ ",(SELECT B.USER_NICK FROM TB_USER B WHERE B.USER_NO = A.USER_NO) AS USER_NICK"
			+ " FROM TB_MARKET A WHERE A.MARKET_NO = #{marketNo}")
	MarketDTO selectMarket(int marketNo);
	
	@Update("UPDATE TB_MARKET SET"
			+ "  PROD_NM = #{prodNm}"
			+ ", MARKET_CONTENT = #{marketContent}"
			+ ", PRICE = #{price}"
			+ ", STATUS = #{status}"
			+ ", IMAGE_NM = #{imageNm}"
			+ " WHERE MARKET_NO = #{marketNo}")
	void updateMarket(MarketDTO marketDTO);
	
	@Delete("DELETE FROM TB_MARKET WHERE MARKET_NO = #{marketNo}")
	void deleteMarket(int marketNo);
	
	@Update("UPDATE TB_MARKET SET STATUS = 'SDO' WHERE MARKET_NO = #{marketNo}")
	void updateStatus(int marketNo);
}
