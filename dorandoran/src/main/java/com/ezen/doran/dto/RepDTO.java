package com.ezen.doran.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepDTO {
	private int repNo;
	private String repTitle;
	private String repContent;
	private int userNo;
	private String userId;
	private String inputDtm;
	private String aYn;
}
