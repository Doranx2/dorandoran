package com.ezen.doran.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.ReDTO;
import com.ezen.doran.service.help.HelpService;
import com.ezen.doran.service.re.ReService;

@RestController
@RequestMapping("/help")
public class HelpController {

	@Autowired
	HelpService helpService;

	@Autowired
	ReService reService;

	@RequestMapping("/selectHelpList")
	public ModelAndView selectHelpList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String searchKeyword) {
		ModelAndView mv = new ModelAndView();

		// 총 게시글 수
		int totalListCnt = helpService.selectHelpCnt(searchKeyword);

		if (page == 0) {
			page = 1;
		} else if (page > totalListCnt) {
			page = totalListCnt;
		}

		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(totalListCnt, page);
		pagination.setSearchKeyword(searchKeyword);

		List<HelpDTO> list = helpService.selectHelpList(pagination);

		for (HelpDTO help : list) {
			if (help.getDoneYn().equals("Y")) {
				help.setDoneYn("해결완료");
			} else if (help.getDoneYn().equals("N")) {
				help.setDoneYn("요청중");
			}
		}

		mv.addObject("list", list);
		mv.addObject("pagination", pagination);
		mv.setViewName("help/selectHelpList.html");

		return mv;
	}

	@RequestMapping("/insertHelpPage")
	public ModelAndView insertHelpPage() {
		ModelAndView mv = new ModelAndView();
		HelpDTO testDTO = new HelpDTO();
		mv.addObject("help", testDTO);
		mv.addObject("update", false);
		mv.setViewName("help/insertHelp.html");

		return mv;
	}

	@PostMapping("/insertHelp")
	public void insertHelp(HelpDTO helpDTO, @RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		if (!file.isEmpty()) {
			// 원래 파일 이름 추출
			String origName = file.getOriginalFilename();

			// 파일 이름으로 쓸 uuid 생성
			String uuid = UUID.randomUUID().toString();

			// 확장자 추출(ex : .png)
			String extension = origName.substring(origName.lastIndexOf("."));

			// uuid와 확장자 결합
			String savedName = uuid + extension;

			String attachPath = request.getSession().getServletContext().getRealPath("/") + "/helpImg/";
			// 파일을 불러올 때 사용할 파일 경로
			String savedPath = attachPath + savedName;

			File saveFile = new File(savedPath);
			// 실제로 로컬에 uuid를 파일명으로 저장
			file.transferTo(saveFile);

			helpDTO.setImageNm(savedName);
		}

		System.out.println(helpDTO);
		helpService.insertHelp(helpDTO);

		response.sendRedirect("/help/selectHelpList");
	}

	@RequestMapping("/selectHelp")
	public ModelAndView selectHelp(@RequestParam("helpNo") int helpNo) {
		ModelAndView mv = new ModelAndView();

		ReDTO reDTO = new ReDTO();
		reDTO.setBoardCd("HEP");
		reDTO.setBoardNo(helpNo);

		List<ReDTO> list = reService.selectReList(reDTO);
		List<ReDTO> reList = new ArrayList<>();
		List<ReDTO> reReList = new ArrayList<>();

		for (ReDTO re : list) {
			if (re.getParentReNo() != 0) {
				reReList.add(re);
			} else if (re.getParentReNo() == 0) {
				reList.add(re);
			}
		}

		System.out.println("reList => " + reList);
		System.out.println("reReList => " + reReList);

		mv.addObject("reList", reList);
		mv.addObject("reReList", reReList);

		HelpDTO help = helpService.selectHelp(helpNo);
		if (help.getDoneYn().equals("Y")) {
			help.setDoneYn("해결완료");
		} else if (help.getDoneYn().equals("N")) {
			help.setDoneYn("요청중");
		}
		
		System.out.println(help);

		mv.addObject("help", help);

		mv.setViewName("help/selectHelp.html");
		return mv;
	}

	@RequestMapping("/updateHelpPage")
	public ModelAndView updateHelpPage(@RequestParam("helpNo") int helpNo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("help", helpService.selectHelp(helpNo));
		mv.addObject("update", true);
		mv.setViewName("help/insertHelp.html");

		return mv;
	}

	@PostMapping("/updateHelp")
	public void updateHelp(HelpDTO helpDTO, @RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		if (!file.isEmpty()) {
			// 원래 파일 이름 추출
			String origName = file.getOriginalFilename();

			// 파일 이름으로 쓸 uuid 생성
			String uuid = UUID.randomUUID().toString();

			// 확장자 추출(ex : .png)
			String extension = origName.substring(origName.lastIndexOf("."));

			// uuid와 확장자 결합
			String savedName = uuid + extension;

			String attachPath = request.getSession().getServletContext().getRealPath("/") + "/helpImg/";
			// 파일을 불러올 때 사용할 파일 경로
			String savedPath = attachPath + savedName;

			// 이전 파일 삭제
			File deleteFile = new File(attachPath + helpDTO.getImageNm());
			if (deleteFile.exists()) { // 파일이 존재하면
				deleteFile.delete(); // 파일 삭제
			}

			File saveFile = new File(savedPath);
			// 실제로 로컬에 uuid를 파일명으로 저장
			file.transferTo(saveFile);

			helpDTO.setImageNm(savedName);
		}

		helpService.updateHelp(helpDTO);

		response.sendRedirect("/help/selectHelp?helpNo=" + helpDTO.getHelpNo());
	}
	
	@GetMapping("/deleteHelp")
	public void deleteHelp(@RequestParam("helpNo") int helpNo, HttpServletResponse response) throws Exception {
		helpService.deleteHelp(helpNo);
		response.sendRedirect("/help/selectHelpList");
	}
}
