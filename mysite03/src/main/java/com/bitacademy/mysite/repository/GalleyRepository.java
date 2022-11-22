package com.bitacademy.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.bitacademy.mysite.vo.GalleryVo;

@Repository
public class GalleyRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> findAll(){
		return sqlSession.selectList("gallery.findAll");
	}
	
	public Boolean insert(GalleryVo vo) {
		int count = sqlSession.insert("gallery.insert", vo);
		return count == 1;
	}
	
	public Boolean delete(Long no) {
		return sqlSession.selectOne("gallery.delete", no);
		
	}
}
