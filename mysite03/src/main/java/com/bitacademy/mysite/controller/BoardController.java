package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/view/{no}")
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
		System.out.println("넘버: "+no);
		boardVo.setNo(no);
		boardService.updateContents(boardVo);		
		return "redirect:/board";
	}
	
}
	
