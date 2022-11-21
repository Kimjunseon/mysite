package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bitacademy.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler)
			throws Exception {
		
		// 설정 실수(spring-servlet에서 exclude-mapping 누락)로 핸들러 타입이 변경이 될 때 캐스팅에러
		// 1. handler 종류확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Handler Method에 @Auth를 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Handler Method에 @Auth가 없다면...
		if(auth == null) {
			return true;
		}
		
		// 5. @Auth가 붙어있기 때문에 인증(Authentification) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 5. @Auth도 붙어 있고 인증도 되어 있다
		return true;
	}

}