package com.ezen.doran.dto;
  
import java.sql.Date;
  
import org.springframework.format.annotation.DateTimeFormat;
  
import lombok.AllArgsConstructor; 
import lombok.Builder; 
import lombok.Data;
import lombok.NoArgsConstructor;
  
  @Data
  
  @Builder
 
  @NoArgsConstructor
  
  @AllArgsConstructor
  
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
	  private String userTel;
	  
  @DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date inputDtm; 
  }
 