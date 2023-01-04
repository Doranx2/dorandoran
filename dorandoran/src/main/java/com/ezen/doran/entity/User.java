package com.ezen.doran.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DynamicInsert
@Data
@Table(name="TB_USER")
public class User {
	private String userNo;
	@Id
	private String userId;
	private String userPw;
	private String userEmail;
	private String userAge;
	private String userGen;
	private String userLoc;
	private String userNick;
	@Column
	@ColumnDefault("USER_ROLE")
	private String userRole;
	private LocalDateTime inputDtm = LocalDateTime.now();

	

}
