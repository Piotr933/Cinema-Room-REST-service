package com.piotrzawada.CinemaRoomRESTService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CinemaRoomRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaRoomRestServiceApplication.class, args);
	}

	@Bean
	public int rows(){
		return 9;
	}

	@Bean
	public int  columns(){
		return 9;
	}
}