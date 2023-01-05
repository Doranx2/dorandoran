package com.ezen.doran.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class PlayDTO {
	private int playNo;
	private String playTitle;
	private String playContent;
	private int playCnt;
	private Date inputDtm;
	private int userNo;
	private String userNm;
	
	private String searchKeyword;
}
