package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.mysite.service.FileUploadService;
import com.bitacademy.mysite.service.GalleyService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleyService galleryService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping("")
	public String index() {
		return "gallery/index";
	}
	
	@RequestMapping("/upload")
	public String upload() {
		fileuploadService.restore(null);
		galleryService.saveImages(galleryVo);
		
		return "redirect:/gallery";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete() {
		galleryService.removeImages(no);
		
		return "redirect:/gallery";
	}	
}