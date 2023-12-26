package com.sesac.db_access_write;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DbAccessWriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbAccessWriteApplication.class, args);
	}

}
