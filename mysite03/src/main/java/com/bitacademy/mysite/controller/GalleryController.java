package com.bitacademy.mysite.controller;

import java.util.List;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
			GalleryVo galleryVo,
			@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("comments") String comments) {
		String url = fileuploadService.restore(multipartFile);
		galleryVo.setUrl(url);
		System.out.println(url);
		System.out.println(comments);
		galleryService.saveImages(galleryVo);
		return "redirect:/gallery";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		galleryService.removeImages(no);
		return "redirect:/gallery";
	}	
}