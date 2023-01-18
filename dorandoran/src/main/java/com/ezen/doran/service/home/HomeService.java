package com.ezen.doran.service.home;

import java.util.List;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.PlayDTO;

public interface HomeService {
	
	List<NoticeDTO> selectHomeNoticeList();

	List<PlayDTO> selectHomePlayList();
	
	List<HelpDTO> selectHomeHelpList();
	
	List<JoinDTO> selectHomeJoinList();
}
