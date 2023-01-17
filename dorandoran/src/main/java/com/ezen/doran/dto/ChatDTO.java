package com.ezen.doran.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {
	private int chatNo;
	private int roomNo;
	private int sendUserNo; // writer
	private String message;
	private Date sendDtm;
	private String readYn;
}
