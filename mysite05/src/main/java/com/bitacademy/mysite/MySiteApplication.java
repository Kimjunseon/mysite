package com.bitacademy.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class MySiteApplication {
	
	@Controller
	public class  HelloController{
		@RequestMapping("/hello")
		public String hello() {
			return "hello";
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MySiteApplication.class, args);
		// try(ConfigurableApplicationContext ac = SpringApplication.run(MyWebApplication.class, args)){}
		// 여기서 try구문은 try catch finally close 구문과 같음
		// close가 발생되므로 web에서는 close가 되면 안됌
	}
}