package com.ezen.doran.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqDTO {
	private int faqNo;
	private String faqTitle;
	private String faqContent;
	private String userId;
	private String inputDtm;
}
