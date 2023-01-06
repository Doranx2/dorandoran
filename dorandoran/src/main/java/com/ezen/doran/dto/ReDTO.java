package com.ezen.doran.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ReDTO {
	private int reNo;
	private int boardNo;
	private String boardCd;
	private int userNo;
	private String reContent;
	private int parentReNo;
	private Date inputDtm;
	private Date updateDtm;
	
	private String userNick;
}
