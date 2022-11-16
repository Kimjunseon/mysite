package com.bitacademy.mysite.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.GuestbookRepository;
import com.bitacademy.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> getContentsList(GuestbookVo vo) {
		return guestbookRepository.findAll();
	}
	
	public void deleteContents(Long no, String password) {
	}

	public void addContents(GuestbookVo vo) {
	}
	
}