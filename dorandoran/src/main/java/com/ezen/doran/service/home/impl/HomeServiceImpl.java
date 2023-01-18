package com.ezen.doran.service.home.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.mapper.HomeMapper;
import com.ezen.doran.service.home.HomeService;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeMapper mapper;

	@Override
	public List<NoticeDTO> selectHomeNoticeList() {
		// TODO Auto-generated method stub
		return mapper.selectHomeNoticeList();
	}

	@Override
	public List<PlayDTO> selectHomePlayList() {
		// TODO Auto-generated method stub
		return mapper.selectHomePlayList();
	}

	@Override
	public List<HelpDTO> selectHomeHelpList() {
		// TODO Auto-generated method stub
		return mapper.selectHomeHelpList();
	}

	@Override
	public List<JoinDTO> selectHomeJoinList() {
		// TODO Auto-generated method stub
		return mapper.selectHomeJoinList();
	}

}
