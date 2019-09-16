package com.andree.reto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andree.repositorio.Connection;

@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class Inicio {

	public static void main(String[] args) {
		SpringApplication.run(Inicio.class, args);
	}
	
	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "<strong>Reto Intercorp Retail</strong><br><br>Andree Ochoa<br><br> >> <a href='./swagger-ui.html'>Swagger</a>";
	}	
}
