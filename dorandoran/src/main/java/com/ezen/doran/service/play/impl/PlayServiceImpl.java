package com.ezen.doran.service.play.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.mapper.PlayMapper;
import com.ezen.doran.service.play.PlayService;

@Service
public class PlayServiceImpl implements PlayService {

	@Autowired
	PlayMapper mapper;
	
	@Override
	public void insertPlay(PlayDTO playDTO) {
		mapper.insertBoard(playDTO);
	}

	@Override
	public List<PlayDTO> selectPlayList(Pagination page) {	
		return mapper.selectPlayList(page);
	}

	@Override
	public int selectPlayCnt(String searchKeyword) {
		return mapper.selectPlayCnt(searchKeyword);
	}

	@Override
	public PlayDTO selectPlay(int playNo) {
		return mapper.selectPlay(playNo);
	}

	@Override
	public void updatePlay(PlayDTO playDTO) {
		mapper.updatePlay(playDTO);
	}

	@Override
	public void deletePlay(int playNo) {
		mapper.deletePlay(playNo);
	}

	@Override
	public void updatePlayCnt(int playNo) {
		mapper.updatePlayCnt(playNo);
	}

}
