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

import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.ReDTO;
import com.ezen.doran.service.market.MarketService;
import com.ezen.doran.service.re.ReService;

@RestController
@RequestMapping("/market")
public class MarketController {

	@Autowired
	MarketService marketService;

	@Autowired
	ReService reService;

	@RequestMapping("/selectMarketList")
	public ModelAndView selectMarketList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String searchKeyword) {
		ModelAndView mv = new ModelAndView();

		// 총 게시글 수
		int totalListCnt = marketService.selectMarketCnt(searchKeyword);

		if (page == 0) {
			page = 1;
		} else if (page > totalListCnt) {
			page = totalListCnt;
		}

		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(totalListCnt, page);
		pagination.setSearchKeyword(searchKeyword);

		List<MarketDTO> list = marketService.selectMarketList(pagination);

		for (MarketDTO market : list) {
			if (market.getStatus().equals("SAE")) {
				market.setStatus("판매중");
			} else if (market.getStatus().equals("SDO")) {
				market.setStatus("거래완료");
			} else if (market.getStatus().equals("RES")) {
				market.setStatus("예약중");
			}
		}

		mv.addObject("list", list);
		mv.addObject("pagination", pagination);
		mv.setViewName("market/selectMarketList.html");

		return mv;
	}

	@RequestMapping("/insertMarketPage")
	public ModelAndView insertMarketPage() {
		MarketDTO testDTO = new MarketDTO();
		ModelAndView mv = new ModelAndView();
		mv.addObject("market", testDTO);
		mv.addObject("update", false);
		mv.setViewName("market/insertMarket.html");

		return mv;
	}

	@PostMapping("/insertMarket")
	public void insertMarket(MarketDTO marketDTO, @RequestParam("file") MultipartFile file, HttpServletRequest request,
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

			String attachPath = request.getSession().getServletContext().getRealPath("/") + "/upload/";
			// 파일을 불러올 때 사용할 파일 경로
			String savedPath = attachPath + savedName;

			File saveFile = new File(savedPath);
			// 실제로 로컬에 uuid를 파일명으로 저장
			file.transferTo(saveFile);

			marketDTO.setImageNm(savedName);
		}

		marketService.insertMarket(marketDTO);

		response.sendRedirect("/market/selectMarketList");
	}

	@RequestMapping("/selectMarket")
	public ModelAndView selectMarket(@RequestParam("marketNo") int marketNo) {
		ModelAndView mv = new ModelAndView();

		ReDTO reDTO = new ReDTO();
		reDTO.setBoardCd("MAR");
		reDTO.setBoardNo(marketNo);

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

		MarketDTO market = marketService.selectMarket(marketNo);
		if (market.getStatus().equals("SAE")) {
			market.setStatus("판매중");
		} else if (market.getStatus().equals("SDO")) {
			market.setStatus("거래완료");
		} else if (market.getStatus().equals("RES")) {
			market.setStatus("예약중");
		}

		mv.addObject("market", market);

		mv.setViewName("market/selectMarket.html");
		return mv;
	}

	@RequestMapping("/updateMarketPage")
	public ModelAndView updateMarketPage(@RequestParam("marketNo") int marketNo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("market", marketService.selectMarket(marketNo));
		mv.addObject("update", true);
		mv.setViewName("market/insertMarket.html");

		return mv;
	}

	@PostMapping("/updateMarket")
	public void updateMarket(MarketDTO marketDTO, @RequestParam("file") MultipartFile file, HttpServletRequest request,
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

			String attachPath = request.getSession().getServletContext().getRealPath("/") + "/upload/";
			// 파일을 불러올 때 사용할 파일 경로
			String savedPath = attachPath + savedName;

			// 이전 파일 삭제
			File deleteFile = new File(attachPath + marketDTO.getImageNm());
			if (deleteFile.exists()) { // 파일이 존재하면
				deleteFile.delete(); // 파일 삭제
			}

			File saveFile = new File(savedPath);
			// 실제로 로컬에 uuid를 파일명으로 저장
			file.transferTo(saveFile);

			marketDTO.setImageNm(savedName);
		}

		marketService.updateMarket(marketDTO);

		response.sendRedirect("/market/selectMarket?marketNo=" + marketDTO.getMarketNo());
	}

	@GetMapping("/deleteMarket")
	public void deleteMarket(@RequestParam("marketNo") int marketNo, HttpServletResponse response) throws Exception {
		marketService.deleteMarket(marketNo);
		response.sendRedirect("/market/selectMarketList");
	}
	
	@PostMapping("/updateStatus")
	public void updateStatus(@RequestParam("marketNo") int marketNo) {
		marketService.updateStatus(marketNo);
	}

}
