package com.yufrolov.vocabularytrainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VocabularyTrainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VocabularyTrainerApplication.class, args);
	}

}
