package com.bitacademy.mysite.repository;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.BoardVo;
@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}
	
	public BoardVo findTitle(Long no) {
		return sqlSession.selectOne("board.findTitle", no);
	}
	
	
	public Boolean newBoardInsert(BoardVo vo) {
		int count = sqlSession.insert("board.newBoardInsert", vo);
		return count == 1;
	}
	
	public Boolean insertReply(BoardVo boardVo1) {
		int count = sqlSession.insert("board.insertReply", boardVo1);
		return count == 1;
	}
	
	public BoardVo deleteByUser(Long no, Long userNo) {
		return sqlSession.selectOne("board.deleteByUser", no);				
	}
	
	public Boolean update(BoardVo vo) {
		int count = sqlSession.update("board.update", vo);
		return count == 1;
	}
	
	public Boolean updateHit(Long no) {
		int count = sqlSession.update("board.updateHit", no);
		return count  == 1;
	}
	
	public Boolean updateByReply(BoardVo boardvo) {
		int count = sqlSession.update("board.updateByReply", boardvo);
		return count == 1;
	}
		

	
	
	public BoardVo findReplyValue(int no) {
		BoardVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select contents, group_no, order_no, depth, user_no from board where no= ? "; // user_no, 추가함
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String contents = rs.getString(1);
				Integer gn = rs.getInt(2);
				Integer on = rs.getInt(3);
				Integer depth = rs.getInt(4);
				Integer un = rs.getInt(5);
				result = new BoardVo();
				result.setContents(contents);
				result.setGroupNo(gn);
				result.setOrderNo(on);
				result.setDepth(depth);
				result.setUserNo(un);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) { // 닫는 순서는 생성 역순으로
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}

}
