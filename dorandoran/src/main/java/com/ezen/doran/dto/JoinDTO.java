package com.ezen.doran.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class JoinDTO {
	private int joinNo;
	private String joinTitle;
	private String joinContent;
	private int userNo;
	private int joinCnt;
	private String joinCd;
	private String joinTypeCd;
	private Date joinDtm;
	private String joinPlace;
	private Date inputDtm;
	private String doneYn;
}
