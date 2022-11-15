package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.dao.BoardDao;
import com.bitacademy.mysite.dao.GuestbookDao;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.GuestbookVo;

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");
		
		if("writeform".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/write.jsp");
			rd.forward(request, response);	
		} else if("write".equals(action)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String userno = request.getParameter("userno");

			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUserNo(Integer.parseInt(userno));
			
			new BoardDao().newBoardInsert(vo);
			
			response.sendRedirect(request.getContextPath() + "/board");
			
		} else if("view".equals(action)) {
			String no = request.getParameter("no");
			BoardVo vo = new BoardDao().findTitle(Integer.parseInt(no));
			
			BoardVo vo2 = new BoardVo();
			vo2.setNo(Integer.parseInt(no));
			new BoardDao().updateHit(vo2);
			
			request.setAttribute("boardVo", vo);
			request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);
			
		} else if("modify".equals(action)) {
			String no = request.getParameter("no");
			String user = request.getParameter("userno");
			BoardVo vo = new BoardDao().findTitle(Integer.parseInt(no));
			request.setAttribute("boardVo", vo);
			request.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(request, response);

		} else if("executeModify".equals(action)) {
			String no = request.getParameter("no");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			new BoardDao().update(title, content, Integer.parseInt(no));
			response.sendRedirect(request.getContextPath() + "/board");
			
		} else if("delete".equals(action)) {
			String no = request.getParameter("no");
			new BoardDao().deleteByUser(Integer.parseInt(no));
			response.sendRedirect(request.getContextPath() + "/board");
			
		} else if("replyform".equals(action)) {
			String no = request.getParameter("no");
			BoardVo vo = new BoardDao().findReplyValue(Integer.parseInt(no));
			request.setAttribute("boardVo", vo);
			request.getRequestDispatcher("/WEB-INF/views/board/replyform.jsp").forward(request, response);
			
		} else if("reply".equals(action)) {
			String content = request.getParameter("content");
			String gno = request.getParameter("groupno");
			String ono = request.getParameter("orderno");
			String ano = request.getParameter("authno");
			
			System.out.println("g: " + gno);
			System.out.println("o: " + ono);
						
			BoardVo vo = new BoardVo();
			vo.setContent(content);
			vo.setGroupNo(Integer.parseInt(gno));
			vo.setOrderNo(Integer.parseInt(ono));
			vo.setUserNo(Integer.parseInt(ano));
			new BoardDao().replyInsert(vo);
			response.sendRedirect(request.getContextPath() + "/board");
			
		} else {
			List<BoardVo> list = new BoardDao().findAll(); 
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
