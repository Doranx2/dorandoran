package com.ezen.doran.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ShareDTO {
	private int shareNo;
	private int userNo;
	private String shareTitle;
	private String shareContent;
	private String shareCat;
	private Date inputDtm;
	
	private String userNm;
	
}
