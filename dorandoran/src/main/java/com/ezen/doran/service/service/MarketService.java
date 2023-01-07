package com.ezen.doran.service.service;

import java.util.List;

import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;

public interface MarketService {
	void insertMarket(MarketDTO marketDTO);
	
	List<MarketDTO> selectMarketList(Pagination pagination);
	
	int selectMarketCnt(String searchKeyword);
}
