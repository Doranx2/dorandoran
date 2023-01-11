package com.ezen.doran.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DynamicInsert
@Data
@Table(name="TB_USER")
@Builder
@AllArgsConstructor
public class User {
	@Id
	private int userNo;
	private String userId;
	private String userPw;
	private String userEmail;
	private int userAge;
	private String userGen;
	private String userLoc;
	private String userNick;
	private String userTel;
	@Column
	@ColumnDefault("USER_ROLE")
	private String userRole;
	private String userNm;
	private LocalDateTime inputDtm = LocalDateTime.now();

	

}
