package com.ezen.doran.service.re;

import java.util.List;

import com.ezen.doran.dto.ReDTO;

public interface ReService {
	void insertRe(ReDTO reDTO);
	
	List<ReDTO> selectReList(ReDTO reDTO);
	
	void updateRe(ReDTO reDTO); 
	
	void deleteRe(int reNo);
	
}
