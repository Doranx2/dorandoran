package com.ezen.doran.service.market.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.mapper.MarketMapper;
import com.ezen.doran.service.market.MarketService;

@Service
public class MarketSerivceImpl implements MarketService{

	@Autowired
	MarketMapper mapper;
	
	@Override
	public void insertMarket(MarketDTO marketDTO){
		mapper.insertMarket(marketDTO);
	}

	@Override
	public List<MarketDTO> selectMarketList(Pagination pagination) {
		return mapper.selectMarketList(pagination);
	}

	@Override
	public int selectMarketCnt(String searchKeyword) {
		return mapper.selectPlayCnt(searchKeyword);
	}

	@Override
	public MarketDTO selectMarket(int marketNo) {
		return mapper.selectMarket(marketNo);
	}

	@Override
	public void updateMarket(MarketDTO marketDTO) {
		mapper.updateMarket(marketDTO);
	}

	@Override
	public void deleteMarket(int marketNo) {
		mapper.deleteMarket(marketNo);
	}

	@Override
	public void updateStatus(int marketNo) {
		mapper.updateStatus(marketNo);
	}

}
