package com.ezen.doran.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class UserDTO {
	private int UserNo;
	private String userNm;
	private String userId;
	private String userPw;
	private String userTel;
	private int userAge;
	private String userGen;
	private String userEmail;
	private String userLoc;
	private String userNick;
	private Date inputDtm;

}
