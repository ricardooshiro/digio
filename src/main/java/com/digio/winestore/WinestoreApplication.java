package com.digio.winestore;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.digio.winestore.service.MockService;

@SpringBootApplication
public class WinestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WinestoreApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(MockService mockService){
		return args -> {
			mockService.setProduto();
			mockService.setCliente();
		};
	}
}
