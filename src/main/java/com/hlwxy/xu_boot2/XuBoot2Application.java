package com.hlwxy.xu_boot2;

import com.hlwxy.xu_boot2.common.config.MyConfig;
import com.hlwxy.xu_boot2.common.shiro.ExceptionResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class XuBoot2Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(XuBoot2Application.class, args);
	}

	// 注册统一异常处理bean
	@Bean
	public ExceptionResolver myExceptionResolver() {
		return new ExceptionResolver();
	}

	@Bean
	public MyConfig myConfig(){
		return new MyConfig();
	}

}
