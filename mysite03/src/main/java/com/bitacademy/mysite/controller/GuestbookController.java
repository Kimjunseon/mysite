package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.service.GuestbookService;
import com.bitacademy.mysite.vo.GuestbookVo;
import com.bitacademy.mysite.vo.UserVo;
 
@Controller
@RequestMapping("/gb")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index() {
		return "guestbook/list";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String index(GuestbookVo vo) {
		System.out.println("hi" + vo);
		List<GuestbookVo> list = guestbookService.getContentsList(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/add")
	public String add(GuestbookVo vo) {
		guestbookService.addContents(vo);
		return "redirect:/";
	}
	
	
	
}