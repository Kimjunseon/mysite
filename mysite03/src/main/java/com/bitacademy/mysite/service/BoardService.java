package com.bitacademy.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> getContentsList() {
		return boardRepository.findAll();
	}
	
	public void addContents(BoardVo boardVo) {
		boardRepository.newBoardInsert(boardVo);
	}
	
	public BoardVo findContents(Long no) {
		boardRepository.updateHit(no);
		return boardRepository.findTitle(no);
	}

	public void updateContents(BoardVo boardVo) {
		boardRepository.update(boardVo);
	}
	
	public void deleteContents(Long no, Long userNo) {
		boardRepository.deleteByUser(no, userNo);
	}
	
	public Map<String, Object> findContentsList(int currentPage){
		// view의 페이징을 처리하기 위한 데이터의 값 계산
		int beginPage = 0;
		int endPage = 0;
		
		// 리스트 가져오기
		return null;
	}
	
//	public BoardVo findContents(Long no, int userNo) {
//		return null;
//	}
//	
	
}