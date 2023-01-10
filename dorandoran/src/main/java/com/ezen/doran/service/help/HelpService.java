package com.ezen.doran.service.help;

import java.util.List;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.Pagination;

public interface HelpService {
	
	void insertHelp(HelpDTO helpDTO);
	
	List<HelpDTO> selectHelpList(Pagination pagination);
	
	int selectHelpCnt(String searchKeyword);
	
	HelpDTO selectHelp(int helpNo);
	
	void updateHelp(HelpDTO helpDTO);
	
	void deleteHelp(int helpNo);
}
