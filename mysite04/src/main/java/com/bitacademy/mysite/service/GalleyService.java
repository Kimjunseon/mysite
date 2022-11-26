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
	public void saveImages(GalleryVo galleryVo) {
		galleyRepository.insert(galleryVo);  // 내가 기입한 값
		
	}
	
	public void removeImages(Long no) { // Long no 내가 넣은 값
		galleyRepository.delete(no); // null 내가 넣은 값
		
	}
}
