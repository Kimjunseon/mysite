package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.mysite.service.GuestbookService;
import com.bitacademy.mysite.vo.GuestbookVo;
 
@Controller
@RequestMapping("/gb")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping({"", "/list"})
	public String list(Model model) {
		model.addAttribute("list", guestbookService.getContents());
		return "guestbook/list";
	}
	
	@RequestMapping("/add")
	public String add(GuestbookVo vo) {
		guestbookService.addContents(vo);
		return "redirect:/gb";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String delete(
		@PathVariable("no") Long no,
		@RequestParam(value="password", required=true, defaultValue="") String password) { 
		guestbookService.deleteContents(no, password);
		return "redirect:/gb";
	}
	
	
	
}