package com.bitacademy.mysite.dao;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.GuestbookVo;

public class BoardDao {
	
	public Boolean newBoardInsert(BoardVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into board values (null, ?, ?, 0, now(), (select ifnull(max(group_no)+1, 1) from board b), 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getUserNo());
			
			int count = pstmt.executeUpdate();
			
			//5. 결과 처리
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public Boolean replyInsert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert into board values (null, '', ?, 0, now(), ?, (select min(order_no)+1 from board b), 1, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContents());
			pstmt.setInt(2, vo.getGroupNo());
			// pstmt.setInt(3, vo.getOrderNo());
			pstmt.setInt(3, vo.getUserNo());
			
			int count = pstmt.executeUpdate();
			
			//5. 결과 처리
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public Boolean replyInsert2(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert into board values (null, '', ?, 0, now(), ?, ?+1, ?+1, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContents());
			pstmt.setInt(2, vo.getGroupNo());
			pstmt.setInt(3, vo.getOrderNo());
			pstmt.setInt(4, vo.getUserNo());
			pstmt.setInt(5, vo.getDepth());
			
			int count = pstmt.executeUpdate();
			
			//5. 결과 처리
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =			
					"   select b.no, b.title, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s'), b.contents, b.group_no, b.order_no, b.depth" +
					"     from user a, board b"  +
					"    where a.no = b.user_no" +
					" order by b.group_no desc, b.order_no asc, b.depth asc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				String contents = rs.getString(6);
				int groupNo = rs.getInt(7);
				int orderNo = rs.getInt(8);
				int depth = rs.getInt(9);
//				int userNo = rs.getInt(9);

				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setContents(contents);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
//				vo.setUserNo(userNo);

				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}
	
	public boolean deleteByUser(int no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
		
	}
	
	public BoardVo findTitle(int no) {
		BoardVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select user_no, title, contents from board where no= ? "; // user_no, 추가함
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				Integer un = rs.getInt(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				result = new BoardVo();
				result.setUserNo(un);
				result.setTitle(title);
				result.setContents(contents);
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
	
	public boolean update(String title, String contents, int no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update board" + 
			               " set title = ?, contents = ?" +
					     " where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setInt(3, no);
			
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
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
	
	public boolean updateReply(int gno) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update board" + 
			               " set order_no = order_no + 1" +
					     " where group_no = ? and order_no > 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gno);

			
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
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
	
	public boolean updateReply2(int gno, int ono) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update board" + 
			               " set order_no = order_no + 1" +
					     " where group_no = ? and order_no > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gno);
			pstmt.setInt(2, ono);

			
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
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
	
	public boolean updateHit(BoardVo vo2) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update board" + 
					       " set hit = hit + 1" +
					     " where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo2.getNo());
		
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
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
