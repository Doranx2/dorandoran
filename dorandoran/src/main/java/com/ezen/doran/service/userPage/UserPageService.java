package com.ezen.doran.service.userPage;

import java.util.List;

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

public interface UserPageService {

	public void updateUser(UserDTO userDTO);

	public List<MarketDTO> selectMyMarketList(Pagination pagination);

	public List<QuestionDTO> selectMyQuestionList(Pagination2 pagination1);

	public List<RepDTO> selectMyRepList(Pagination2 pagination2);

	public List<ShareDTO> selectMyShareList(Pagination2 pagination1);

	public List<HelpDTO> selectMyHelpList(Pagination2 pagination2);

	public List<PlayDTO> selectMyPlayList(Pagination2 pagination2);

	public List<JoinDTO> selectMyJoinList(Pagination2 pagination1);

	public int selectMyMarketListCnt(int userNo);

	public int selectMyShareListCnt(int userNo);

	public int selectMyHelpListCnt(int userNo);

	public int selectMyPlayListCnt(int userNo);

	public int selectMyQuestionListCnt(int userNo);

	public int selectMyRepListCnt(int userNo);

	public int selectMyJoinListCnt(int userNo);


}
