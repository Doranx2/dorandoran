package com.ezen.doran.service.share;

import java.util.List;

import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.ShareDTO;

public interface ShareService {
	void insertShare(ShareDTO shareDTO);
	
	List<ShareDTO> selectShareList(Pagination pagination);
	
	int selectShareCnt(String searchKeyword);
	
	ShareDTO selectShare(int shareNo);
	
	void updateShare(ShareDTO shareDTO);
	
	void deleteShare(int shareNo);
}
