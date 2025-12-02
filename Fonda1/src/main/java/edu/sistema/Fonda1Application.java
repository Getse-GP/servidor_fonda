package edu.sistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Fonda1Application {

	public static void main(String[] args) {
		SpringApplication.run(Fonda1Application.class, args);
	}

}
