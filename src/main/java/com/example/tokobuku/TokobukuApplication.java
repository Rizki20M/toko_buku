package com.example.tokobuku;
import com.example.tokobuku.entity.Book;
import com.example.tokobuku.repo.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class TokobukuApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TokobukuApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TokobukuApplication.class);
	}

	@Profile("pertamakalijalan")
	@Bean
	CommandLineRunner initDatabase(BookRepository bookRepository){
		return args->{
			bookRepository.save(new Book("Cara Tidur","Dodi",new BigDecimal("15.22")));
			bookRepository.save(new Book("Cara Makan","Ardi",new BigDecimal("14.13")));
			bookRepository.save(new Book("Cara Salto","Budi",new BigDecimal("20.31")));
		};
	}

}
