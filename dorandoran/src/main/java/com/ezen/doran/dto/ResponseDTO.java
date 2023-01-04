package com.ezen.doran.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class ResponseDTO<T> {
	private String errorMessage;
	private int statusCode;
	private List<T> Items;
	private T Item;
	private Page<T> pageItems;
	}
