package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
	public void insertMarket(MarketDTO marketDTO);
	
	@Select("SELECT * FROM TB_MARKET"
			+ " WHERE PROD_NM LIKE CONCAT('%', #{searchKeyword}, '%')"
			+ " ORDER BY INPUT_DTM DESC"
			+ " LIMIT #{pageSize} OFFSET #{startIndex}")
	public List<MarketDTO> selectMarketList(Pagination pagination);
	
	@Select("SELECT COUNT(*) FROM TB_MARKET"
			+" WHERE PROD_NM LIKE CONCAT('%', #{searchKeyword}, '%')")
	int selectPlayCnt(String searchKeyword);
}
