package com.techgig.meetingroombooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@ComponentScan(basePackages = { "com.techgig.*" })
public class MeetingRoomApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingRoomApiApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setCacheSeconds(10); // reload messages every 10 seconds
		return messageSource;
	}
}
