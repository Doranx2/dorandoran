package com.ezen.doran.service.join.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.mapper.JoinMapper;
import com.ezen.doran.service.join.JoinService;

@Service
public class JoinServiceImpl implements JoinService{
	@Autowired
	JoinMapper mapper;

	@Override
	public void insertJoin(JoinDTO joinDTO) {
		mapper.insertJoin(joinDTO);
	}

	@Override
	public List<JoinDTO> selectJoinList(Pagination pagination) {
		return mapper.selectJoinList(pagination);
	}

	@Override
	public int selectJoinCnt(Map<String, String> map) {
		return mapper.selectJoinCnt(map);
	}

	@Override
	public JoinDTO selectJoin(int joinNo) {
		return mapper.selectJoin(joinNo);
	}

	@Override
	public void updateJoin(JoinDTO joinDTO) {
		mapper.updateJoin(joinDTO);
	}

	@Override
	public void deleteJoin(int joinNo) {
		mapper.deleteJoin(joinNo);
	}
}
