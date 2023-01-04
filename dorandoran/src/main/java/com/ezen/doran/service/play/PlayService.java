package com.ezen.doran.service.play;

import java.util.List;

import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.PlayDTO;

public interface PlayService {
	void insertPlay(PlayDTO playDTO);
	
	List<PlayDTO> selectPlayList(Pagination pagination);
	
	int selectPlayCnt(String searchKeyword);
	
	PlayDTO selectPlay(int playNo);
	
	void updatePlay(PlayDTO playDTO);
	
	void deletePlay(int playNo);
	
	void updatePlayCnt(int playNo);
}