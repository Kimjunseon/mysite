package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.mysite.security.AuthUser;
import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.UserVo;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping("/board")
public class BoardController{
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"", "/board"})
	public String list(Model model) {
		model.addAttribute("list", boardService.getContentsList());
		return "board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String view(Model model, @PathVariable("no") Long no) {
		BoardVo boardVo = boardService.findContents(no);
		model.addAttribute("title", boardVo.getTitle());
		model.addAttribute("contents", boardVo.getContents());
		model.addAttribute("userNo", boardVo.getUserNo());
		return "board/view";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardVo boardVo) {
		boardService.addContents(boardVo);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/delete/{no}")
	public String delete(@PathVariable Long no, Long userNo) {
		boardService.deleteContents(no, userNo);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(Model model, @PathVariable("no") Long no) {
		BoardVo boardVo = boardService.findContents(no);
		model.addAttribute("title", boardVo.getTitle());
		model.addAttribute("contents", boardVo.getContents());
		return "board/modify";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(BoardVo boardVo, @PathVariable("no") Long no) {
		boardVo.setNo(no);
		boardService.updateContents(boardVo);		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/reply/{no}", method=RequestMethod.GET)
	public String reply(Model model, @PathVariable("no") Long no) {
		BoardVo boardVo = boardService.findContents(no);
		model.addAttribute("contents", boardVo.getContents());
		model.addAttribute("orderNo", boardVo.getOrderNo());
		return "board/reply";
	}
	
	@RequestMapping(value="/reply/{no}", method=RequestMethod.POST)
	public String reply(
			Model model,
			BoardVo boardVo,
			@AuthUser UserVo authUser,
			@PathVariable("no") Long no,
			@RequestParam("contents") String contents
			) {
		
		BoardVo boardVo2 = boardService.findContents(no);
		System.out.println("자바 : " + boardVo2);
		boardService.updateByReply(boardVo2);
		
		Long an = authUser.getNo();
		
		BoardVo boardVo1 = boardService.findContents(no);
		
		String title = boardVo1.getTitle();
		Integer userNo = an.intValue();
		Integer groupNo = boardVo1.getGroupNo();
		Integer orderNo = boardVo1.getOrderNo();
		Integer depth = boardVo1.getDepth();
		
		boardVo1.setTitle(title);
		boardVo1.setGroupNo(groupNo);
		boardVo1.setContents(contents);
		boardVo1.setUserNo(userNo);
		boardVo1.setOrderNo(orderNo);
		boardVo1.setDepth(depth);
		boardService.addRelpy(boardVo1);
		
		return "redirect:/board";
	}	
	
	// insert 기능 reply 기능을 구현, update도 사용한 적 있으니 이것도 고려/ 최종은 index에 보여야함. newboard은 검토
	
}
	
