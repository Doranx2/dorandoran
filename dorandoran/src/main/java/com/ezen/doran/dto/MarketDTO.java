package com.ezen.doran.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MarketDTO {
	private int marketNo;
	private int userNo;
	private String marketContent;
	private int price;
	private String prodNm;
	private Date inputDtm;
	private String status;
	
	private String imageNm;
	private String userNick;
}
