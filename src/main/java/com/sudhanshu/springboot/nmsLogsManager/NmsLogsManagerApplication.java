package com.sudhanshu.springboot.nmsLogsManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// @ComponentScan(basePackages = "com.developerstack")
@SpringBootApplication
public class NmsLogsManagerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NmsLogsManagerApplication.class, args);
	}

}

