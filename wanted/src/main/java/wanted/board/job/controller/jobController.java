package wanted.board.job.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import wanted.board.job.service.jobService;
import wanted.board.common.domain.CommandMap;


@Log4j
@Controller
public class jobController {
	
	@Resource(name = "jobService")
	private jobService jobService;
	
	/** 24.08.03 : 등록, 수정, 삭제 리스트, 검색 구현 */
	/** 24.08.04 : 회사가 올린 다른 채용 공고, 카테고리 별 검색 수정 */
	
	// 1. 채용공고를 등록합니다.
	@RequestMapping(value = "/jobBoard/insertForm") // 요청 URL. 주소는 @RequestMapping과 맵핑되어 해당 메서드 실행
	public ModelAndView jobBoardInsertForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/job/insert"); // jsp 경로 설정
	
		return mv;
	}
	
	@RequestMapping(value = "/jobBoard/insert")
	public ModelAndView johBoardInsert(CommandMap commandMap, RedirectAttributes redirect) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/jobBoard/list.com");
	
		jobService.jobBoardInsert(commandMap.getMap());

		redirect.addFlashAttribute("success", "공고 등록이 완료되었습니다.");
	    
		return mv;
	}
	
	
	// 2. 채용공고를 수정합니다.
	@RequestMapping(value="/jobBoard/updateForm")
	public ModelAndView jobBoardUpdateForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/job/updateForm");
	
		log.info("getmap()"+commandMap.getMap());
		Map<String,Object> map = jobService.jobBoardDetail(commandMap.getMap());
		
		log.info("returned map"+map);
		mv.addObject("map", map);
			
		
		return mv;
	}
		
	@RequestMapping(value = "/jobBoard/update", method = RequestMethod.POST)
	public ModelAndView jobBoardUpdate(CommandMap commandMap, RedirectAttributes redirect) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/jobBoard/list.com");
	
		jobService.jobBoardUpdate(commandMap.getMap());
		
		mv.addObject("NOTICE_ID", commandMap.get("NOTICE_ID"));
		redirect.addFlashAttribute("info", "글 수정이 완료되었습니다.");
	
		return mv;
	}
	
	
	// 3. 채용공고를 삭제합니다.
	@RequestMapping(value = "/jobBoard/delete", method = RequestMethod.POST )
	public ModelAndView jobBoardDelete(CommandMap commandMap, RedirectAttributes redirect) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/jobBoard/list.com");
	
		jobService.jobBoardDelete(commandMap.getMap());
		redirect.addFlashAttribute("warning", "글 삭제가 완료되었습니다.");
		
		return mv;
	}
	
	
	// 4-1. 채용공고 목록을 가져옵니다.
	@SuppressWarnings("unused")
	@RequestMapping(value = "/jobBoard/list", method = RequestMethod.GET)
	public ModelAndView jobBoardList(CommandMap commandMap, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/job/list");
	
		log.info("Calling jobService.jobBoardList");
		
		List<Map<String, Object>> list = jobService.jobBoardList(commandMap.getMap());
		
		log.info("Job list size: " + list.size());
		
		mv.addObject("list", list);
		
		if (list.size() > 0) {
		    mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		    log.info("Total count: " + list.get(0).get("TOTAL_COUNT"));
		} else {
		    mv.addObject("TOTAL", 0);
		    log.info("Total count: 0");
		}
		
		//총 채용 공고 수 
		int total = jobService.jobBoardTotal();
		mv.addObject("TOTAL", total);
		
		log.info("Returning ModelAndView");
		return mv;
	}
	
	
	// 4-2. 채용공고 검색 기능 구현
	@RequestMapping(value = "/jobBoard/list/search")
	public ModelAndView jobBoardListSearch(CommandMap commandMap, HttpServletRequest request,
		@RequestParam(value = "keyword", defaultValue = "") String keyword,
		@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "category", required = false) String category) throws Exception {
		
		ModelAndView mv = new ModelAndView("/job/search");
			
		int pageSize = 10; 
		int start = (page - 1) * pageSize + 1;
		int end = page * pageSize;
			
		Map<String, Object> searchMap = commandMap.getMap();
		searchMap.put("keyword", keyword);
		searchMap.put("START", start);
		searchMap.put("END", end);
			
		if (category != null && !category.isEmpty()) {
			searchMap.put("category", category);
		}
			
		List<Map<String, Object>> boardSearchList = jobService.jobBoardSearchList(searchMap);
			
		mv.addObject("boardSearchList", boardSearchList);
		mv.addObject("keyword", keyword);
		mv.addObject("currentPage", page);
		mv.addObject("category", category);
			    
		return mv;
	}
	
	
	// 5. 채용 상세 페이지를 가져옵니다.
	@RequestMapping(value="/jobBoard/detail/{NOTICE_ID}")
	public ModelAndView jobBoardDetail(@PathVariable("NOTICE_ID") int NOTICE_ID, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/job/detail");
		
		log.info("BoardDetail method called with NOTICE_ID: " + NOTICE_ID);
		 
		System.out.println(NOTICE_ID);
		commandMap.put("NOTICE_ID", NOTICE_ID); 
				
		Map<String,Object> map = jobService.jobBoardDetail(commandMap.getMap());
		mv.addObject("map", map);
		
		// 해당 회사가 올린 다른 채용공고
		List<Map<String, Object>> listByCompany = jobService.listByCompany(map);
		mv.addObject("listByCompany", listByCompany);
		
		System.out.println("listByCompany: " + listByCompany);
		System.out.println("ModelAndView attributes: " + mv.getModel());
				
	return mv;
	}

}
