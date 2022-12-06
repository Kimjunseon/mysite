
package com.bitacademy.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	private static final Log Logger = LogFactory.getLog(ControllerExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	// 404 에러처리
	public String HandlerException(Model model, Exception e) {
		if(e instanceof NoHandlerFoundException) {
			return "error/404";
		}
		
		// 500 에러처리
		// 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		//System.out.println(errors.toString());
		Logger.error(errors.toString());
		
		// 사과 페이지(HTML 응답, 정상종료)
		model.addAttribute("exception", errors.toString());
		return "error/exception";
	}
}