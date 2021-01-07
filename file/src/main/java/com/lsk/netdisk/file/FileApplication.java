package com.lsk.netdisk.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class FileApplication {
	public static void main(String[] args) {
		SpringApplication.run(FileApplication.class,args);
	}
}
