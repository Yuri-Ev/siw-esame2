package com.example.demo;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiwCatering1Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SiwCatering1Application.class, args);
		FileUtils.cleanDirectory(new File("src/main/resources/static/animali-photos"));
		FileUtils.cleanDirectory(new File("src/main/resources/static/ambienti-photos"));
	}

}
