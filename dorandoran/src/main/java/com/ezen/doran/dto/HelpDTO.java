package com.ezen.doran.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class HelpDTO {
	private int helpNo;
	private String helpTitle;
	private String helpContent;
	private String helpPlace;
	private Date inputDtm;
	private String doneYn;
	private int userNo;
	private String imageNm;
}
