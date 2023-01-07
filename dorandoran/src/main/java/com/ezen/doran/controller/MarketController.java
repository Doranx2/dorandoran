package com.ezen.doran.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.service.service.MarketService;

@RestController
@RequestMapping("/market")
public class MarketController {

	@Autowired
	MarketService marketService;

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
		ModelAndView mv = new ModelAndView();
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

			// 실제로 로컬에 uuid를 파일명으로 저장
			file.transferTo(new File(savedPath));

			marketDTO.setImageNm(savedName);
		}

		marketService.insertMarket(marketDTO);

		response.sendRedirect("/market/selectMarketList");
	}
}
