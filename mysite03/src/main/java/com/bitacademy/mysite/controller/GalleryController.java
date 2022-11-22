package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.service.FileUploadService;
import com.bitacademy.mysite.service.GalleyService;
import com.bitacademy.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleyService galleryService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getImagesList();
		model.addAttribute("list", list);
		return "gallery/index";
	}
	
	@RequestMapping("/upload")
	public String upload(
			@RequestParam("comments") GalleryVo galleryVo,
			@RequestParam("file") MultipartFile multipartFile,
			Model model) {
		String url = fileuploadService.restore(multipartFile);
		System.out.println("url:" + url);
		
		model.addAttribute("url", url);
		
		galleryService.saveImages(galleryVo.getComments(), model.addAttribute(url));
		return "redirect:/gallery";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(Long no) { // Long no 내가 넣은 값
		galleryService.removeImages(no);
		return "redirect:/gallery";
	}	
}