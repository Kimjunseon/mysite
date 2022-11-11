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
			String hit = request.getParameter("hit");
			String gn = request.getParameter("groupno");
			String on = request.getParameter("orderno");
			String depth = request.getParameter("depth");
			String un = request.getParameter("userno");
			
			System.out.println("1" + title);
			System.out.println("2" + content);
			System.out.println("3" + hit);
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			response.sendRedirect(request.getContextPath() + "/board");
			
		} else if("view".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
			rd.forward(request, response);
		} else if("modify".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/modify.jsp");
			rd.forward(request, response);
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