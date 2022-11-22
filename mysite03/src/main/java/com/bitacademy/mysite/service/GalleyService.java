package com.bitacademy.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bitacademy.mysite.repository.GalleyRepository;
import com.bitacademy.mysite.vo.GalleryVo;

@Service
public class GalleyService {
	@Autowired
	private GalleyRepository galleyRepository;
	
	public List<GalleryVo> getImagesList(){
		return galleyRepository.findAll();
	}
	
	// 내가 만든 서비스
	public void saveImages(GalleryVo galleryVo, Model model) {
		galleyRepository.saveImage(galleryVo, model);  // 내가 기입한 값
		
	}
	
	public GalleryVo removeImages(Long no) { // Long no 내가 넣은 값
		return null; // null 내가 넣은 값
		
	}
}
