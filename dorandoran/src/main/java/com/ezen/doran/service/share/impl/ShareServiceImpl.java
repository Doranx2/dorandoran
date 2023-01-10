package com.ezen.doran.service.share.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.ShareDTO;
import com.ezen.doran.mapper.ShareMapper;
import com.ezen.doran.service.share.ShareService;

@Service
public class ShareServiceImpl implements ShareService {
	@Autowired
	ShareMapper mapper;

	@Override
	public void insertShare(ShareDTO shareDTO) {
		mapper.insertShare(shareDTO);
	}

	@Override
	public List<ShareDTO> selectShareList(Pagination pagination) {
		return mapper.selectShareList(pagination);
	}

	@Override
	public int selectShareCnt(String searchKeyword) {
		return mapper.selectShareCnt(searchKeyword);
	}

	@Override
	public ShareDTO selectShare(int shareNo) {
		return mapper.selectShare(shareNo);
	}

	@Override
	public void updateShare(ShareDTO shareDTO) {
		mapper.updateShare(shareDTO);
	}

	@Override
	public void deleteShare(int shareNo) {
		mapper.deleteShare(shareNo);
	}
}
