package com.bitacademy.mysite.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageResourceConfig {
	
	@Bean
	public MessageSource resourceBundleMessageSource() {
		ResourceBundleMessageSource messagesource = new ResourceBundleMessageSource();
		messagesource.setBasename("com/bitacademy/mysite/config/web/message_ko.properties");
		messagesource.setDefaultEncoding("utf-8");
		return messagesource;
	}
}
