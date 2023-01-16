package com.ezen.doran.service.userPage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
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
	public List<MarketDTO> selectMyMarketList(int userNo, Pagination pagination) {
		// TODO Auto-generated method stub
		return mapper.selectMyMarketList(userNo, pagination);
	}

	@Override
	public List<QuestionDTO> selectMyQuestionList(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyQuestionList(userNo);
	}

	@Override
	public List<RepDTO> selectMyRepList(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyRepList(userNo);
	}

	@Override
	public List<ShareDTO> selectMyShareList(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyShareList(userNo);
	}

	@Override
	public List<HelpDTO> selectMyHelpList(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyHelpList(userNo);
	}

	@Override
	public List<PlayDTO> selectMyPlayList(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyPlayList(userNo);
	}

	@Override
	public List<JoinDTO> selectMyJoinList(int userNo) {
		// TODO Auto-generated method stub
		return mapper.selectMyJoinList(userNo);
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

}
