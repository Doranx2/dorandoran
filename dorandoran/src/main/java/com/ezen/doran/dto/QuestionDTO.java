package com.ezen.doran.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
	private int qNo;
	private String qTitle;
	private String qContent;
	private int userNo;
	private String inputDtm;
	private String aYn;
	private String qCd;
}
