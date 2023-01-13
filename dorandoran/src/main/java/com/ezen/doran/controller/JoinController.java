package com.ezen.doran.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.ReDTO;
import com.ezen.doran.service.join.JoinService;
import com.ezen.doran.service.re.ReService;

@RestController
@RequestMapping("/join")
public class JoinController {

	@Autowired
	JoinService joinService;

	@Autowired
	ReService reService;

	@SuppressWarnings("finally")
	@RequestMapping("/selectJoinList")
	public ModelAndView selectJoinList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "") String joinCd)
			throws Exception {
		ModelAndView mv = new ModelAndView();

		Map<String, String> map = new HashMap<>();
		map.put("searchKeyword", searchKeyword);
		map.put("joinCd", joinCd);

		// 총 게시글 수
		int totalListCnt = joinService.selectJoinCnt(map);

		if (page == 0) {
			page = 1;
		} else if (page > totalListCnt) {
			page = totalListCnt;
		}

		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(totalListCnt, page);
		pagination.setSearchKeyword(searchKeyword);
		pagination.setJoinCd(joinCd);

		List<JoinDTO> selectJoinList = null;
		try {
			selectJoinList = joinService.selectJoinList(pagination);
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (selectJoinList != null) {
				Map<Integer, String[]> typeCdList = new HashMap<>();
				for (JoinDTO join : selectJoinList) {
					if (!join.getJoinTypeCd().equals("") && join.getJoinTypeCd() != null) {
						String[] arrayTypeCd = join.getJoinTypeCd().split(",");
						typeCdList.put(join.getJoinNo(), arrayTypeCd);
					}
				}

				mv.addObject("selectJoinList", selectJoinList);
				mv.addObject("typeCdList", typeCdList);
				mv.addObject("pagination", pagination);
				mv.addObject("Check", false);
				mv.setViewName("join/selectJoinList.html");

				return mv;
			} else {
				Map<Integer, String[]> typeCdList = new HashMap<>();
				mv.addObject("selectJoinList", selectJoinList);
				mv.addObject("typeCdList", typeCdList);
				mv.addObject("pagination", pagination);
				mv.addObject("Check", true);
				mv.setViewName("join/selectJoinList.html");

				return mv;
			}
		}

	}

	@RequestMapping("/insertJoinPage")
	public ModelAndView insertJoinPage() {
		ModelAndView mv = new ModelAndView();
		JoinDTO testDTO = new JoinDTO();
		mv.addObject("join", testDTO);
		mv.addObject("update", false);
		mv.setViewName("join/insertJoin.html");

		return mv;
	}

	@PostMapping("/insertJoin")
	public void insertJoin(JoinDTO joinDTO, HttpServletResponse response) throws Exception {
		joinService.insertJoin(joinDTO);
		response.sendRedirect("/join/selectJoinList");
	}

	@RequestMapping("/selectJoin")
	public ModelAndView selectJoin(@RequestParam("joinNo") int joinNo) {
		ModelAndView mv = new ModelAndView();

		ReDTO reDTO = new ReDTO();
		reDTO.setBoardCd("JOI");
		reDTO.setBoardNo(joinNo);

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

		JoinDTO join = joinService.selectJoin(joinNo);

		String[] typeCd = join.getJoinTypeCd().split(",");

		mv.addObject("join", join);
		mv.addObject("typeCd", typeCd);

		mv.setViewName("join/selectJoin.html");
		return mv;
	}

	@RequestMapping("/updateJoinPage")
	public ModelAndView updateJoinPage(@RequestParam("joinNo") int joinNo) {
		ModelAndView mv = new ModelAndView();

		JoinDTO join = joinService.selectJoin(joinNo);

		String[] typeCd = join.getJoinTypeCd().split(",");

		mv.addObject("typeCd", typeCd);
		mv.addObject("join", join);
		mv.addObject("update", true);
		mv.setViewName("join/insertJoin.html");

		return mv;
	}

	@RequestMapping("/updateJoin")
	public void updateJoin(JoinDTO joinDTO, HttpServletResponse response) throws Exception {
		joinService.updateJoin(joinDTO);
		response.sendRedirect("/join/selectJoin?joinNo=" + joinDTO.getJoinNo());
	}

	@RequestMapping("/deleteJoin")
	public void deleteJoin(@RequestParam("joinNo") int joinNo, HttpServletResponse response) throws Exception {
		joinService.deleteJoin(joinNo);
		response.sendRedirect("/join/selectJoinList");
	}
}
