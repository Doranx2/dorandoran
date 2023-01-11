package com.ezen.doran.service.userPage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.mapper.UserPageMapper;
import com.ezen.doran.service.userPage.UserPageService;

@Service
public class UserPageServiceImpl implements UserPageService {
	
	@Autowired
	UserPageMapper mapper;

	@Override
	public void updateUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		mapper.updateUser(userDTO);
	}

	@Override
	public List<PlayDTO> selectMyPlayList() {
		// TODO Auto-generated method stub
		return mapper.selectMyPlayList();
	}

	@Override
	public List<MarketDTO> selectMyMarketList(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyMarketList(userNo);
	}

}
