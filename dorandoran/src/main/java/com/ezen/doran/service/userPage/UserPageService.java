package com.ezen.doran.service.userPage;

import java.util.List;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.dto.ShareDTO;
import com.ezen.doran.dto.UserDTO;

public interface UserPageService {

	public void updateUser(UserDTO userDTO);

	public List<MarketDTO> selectMyMarketList(int userNo, Pagination pagination);

	public List<QuestionDTO> selectMyQuestionList(int userNo);

	public List<RepDTO> selectMyRepList(int userNo);

	public List<ShareDTO> selectMyShareList(int userNo);

	public List<HelpDTO> selectMyHelpList(int userNo);

	public List<PlayDTO> selectMyPlayList(int userNo);

	public List<JoinDTO> selectMyJoinList(int userNo);

	public int selectMyMarketListCnt(int userNo);

	public int selectMyShareListCnt(int userNo);

	public int selectMyHelpListCnt(int userNo);

	public int selectMyPlayListCnt(int userNo);

	public int selectMyQuestionListCnt(int userNo);

	public int selectMyRepListCnt(int userNo);

	public int selectMyJoinListCnt(int userNo);


}
