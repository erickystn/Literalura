package dev.erickystn.literalura;

import dev.erickystn.literalura.principal.Principal;
import dev.erickystn.literalura.service.AutorService;
import dev.erickystn.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	LivroService livroService;
	@Autowired
	AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new Principal(livroService, autorService).exibeMenu();
	}
}
