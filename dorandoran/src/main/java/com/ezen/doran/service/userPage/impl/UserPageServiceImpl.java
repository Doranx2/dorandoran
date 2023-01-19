package com.ezen.doran.service.userPage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.Pagination2;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.dto.ShareDTO;
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
	public List<MarketDTO> selectMyMarketList(Pagination pagination) {
		// TODO Auto-generated method stub
		return mapper.selectMyMarketList(pagination);
	}

	@Override
	public List<QuestionDTO> selectMyQuestionList(Pagination2 pagination1) {
		// TODO Auto-generated method stub
		return mapper.selectMyQuestionList(pagination1);
	}

	@Override
	public List<RepDTO> selectMyRepList(Pagination2 pagination2) {
		// TODO Auto-generated method stub
		return mapper.selectMyRepList(pagination2);
	}

	@Override
	public List<ShareDTO> selectMyShareList(Pagination2 pagination1) {
		// TODO Auto-generated method stub
		return mapper.selectMyShareList(pagination1);
	}

	@Override
	public List<HelpDTO> selectMyHelpList(Pagination2 pagination2) {
		// TODO Auto-generated method stub
		return mapper.selectMyHelpList(pagination2);
	}

	@Override
	public List<PlayDTO> selectMyPlayList(Pagination2 pagination3) {
		// TODO Auto-generated method stub
		return mapper.selectMyPlayList(pagination3);
	}

	@Override
	public List<JoinDTO> selectMyJoinList(Pagination2 pagination1) {
		// TODO Auto-generated method stub
		return mapper.selectMyJoinList(pagination1);
	}

	@Override
	public int selectMyMarketListCnt(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyMarketListCnt(userNo);
	}

	@Override
	public int selectMyShareListCnt(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyShareListCnt(userNo);
	}

	@Override
	public int selectMyHelpListCnt(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyHelpListCnt(userNo);
	}

	@Override
	public int selectMyPlayListCnt(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyPlayListCnt(userNo);
	}

	@Override
	public int selectMyQuestionListCnt(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyQuestionListCnt(userNo);
	}

	@Override
	public int selectMyRepListCnt(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyRepListCnt(userNo);
	}

	@Override
	public int selectMyJoinListCnt(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyJoinListCnt(userNo);
	}

	@Override
	public UserDTO selectUser(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectUser(userNo);
	}

}
