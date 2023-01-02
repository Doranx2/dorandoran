package com.ezen.doran.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String userId;
	private String inputDtm;
}
