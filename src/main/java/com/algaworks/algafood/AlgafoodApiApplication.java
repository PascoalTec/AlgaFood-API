package com.algaworks.algafood;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.algaworks.algafood.core.io.Base64ProtocolResolver;


@EnableWebMvc
@SpringBootApplication
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		var app = new SpringApplication(AlgafoodApiApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
		
		//  SpringApplication.run(AlgafoodApiApplication.class, args);
	}

}
