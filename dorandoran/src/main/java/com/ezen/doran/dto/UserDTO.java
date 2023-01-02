package com.ezen.doran.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class UserDTO {
	private int userNo;
	private String userNm;
	private String userId;
	private String userPw;
	private int userAge;
	private String userGen;
	private String userEmail;
	private String userLoc;
	private String userNick;
	private String userRole;
	private Date inputDTM;
	
	
}
