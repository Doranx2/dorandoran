package com.ezen.doran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.doran.dto.ReDTO;
import com.ezen.doran.service.re.ReService;

@RestController
@RequestMapping("/re")
public class ReController {
	
	@Autowired
	ReService reService;

	@PostMapping("/insertRe")
	public void insertRe(ReDTO reDTO ) {
		reService.insertRe(reDTO);
		
	}
	
	
	@PostMapping("/updateRe")
	public void updateRe(ReDTO reDTO) {
		reService.updateRe(reDTO);
	}
	
	@PostMapping("/deleteRe")
	public void deleteRe(@RequestParam("reNo") int reNo) {
		reService.deleteRe(reNo);
	}
}
