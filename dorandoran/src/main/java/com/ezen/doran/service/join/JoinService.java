package com.ezen.doran.service.join;

import java.util.List;
import java.util.Map;

import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.Pagination;

public interface JoinService {
	void insertJoin(JoinDTO joinDTO);
	
	List<JoinDTO> selectJoinList(Pagination pagination);
	
	int selectJoinCnt(Map<String, String> map);
	
	JoinDTO selectJoin(int joinNo);
	
	void updateJoin(JoinDTO joinDTO);
	
	void deleteJoin(int joinNo);
}
