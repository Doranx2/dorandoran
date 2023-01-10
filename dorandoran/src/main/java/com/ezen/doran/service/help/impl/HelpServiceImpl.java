package com.ezen.doran.service.help.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.mapper.HelpMapper;
import com.ezen.doran.service.help.HelpService;

@Service
public class HelpServiceImpl implements HelpService{
	@Autowired
	HelpMapper mapper;

	@Override
	public void insertHelp(HelpDTO helpDTO) {
		mapper.insertHelp(helpDTO);
	}

	@Override
	public List<HelpDTO> selectHelpList(Pagination pagination) {
		return mapper.selectHelpList(pagination);
	}

	@Override
	public int selectHelpCnt(String searchKeyword) {
		return mapper.selectPlayCnt(searchKeyword);
	}

	@Override
	public HelpDTO selectHelp(int helpNo) {
		return mapper.selectHelp(helpNo);
	}

	@Override
	public void updateHelp(HelpDTO helpDTO) {
		mapper.updateHelp(helpDTO);
	}

	@Override
	public void deleteHelp(int helpNo) {
		mapper.deleteHelp(helpNo);
	}
}
