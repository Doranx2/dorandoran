package com.ezen.doran.service.re.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.ReDTO;
import com.ezen.doran.mapper.ReMapper;
import com.ezen.doran.service.re.ReService;

@Service
public class ReServiceImpl implements ReService {

	@Autowired
	ReMapper mapper;
	
	@Override
	public void insertRe(ReDTO reDTO) {
		mapper.insertRe(reDTO);
	}

	@Override
	public List<ReDTO> selectReList(ReDTO reDTO) {
		return mapper.selectReList(reDTO);
	}

	@Override
	public void updateRe(ReDTO reDTO) {
		mapper.updateRe(reDTO);
	}

	@Override
	public void deleteRe(int reNo) {
		mapper.deleteRe(reNo);
	}

}
