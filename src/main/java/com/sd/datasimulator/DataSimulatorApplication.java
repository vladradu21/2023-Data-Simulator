package com.sd.datasimulator;

import com.sd.datasimulator.dto.MessageDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class DataSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataSimulatorApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, MessageDTO> kafkaTemplate) {
		return args -> {
			for (int i = 0; i < 1000; i++) {
				kafkaTemplate.send("monitoring", new MessageDTO("vladradu", "bec", LocalDateTime.now().minusHours(1), (Math.round((Math.random() * 9_00) + 100) / 100.0) ));
				kafkaTemplate.send("monitoring", new MessageDTO("dumitruradu", "altbec", LocalDateTime.now().minusHours(1), (Math.round((Math.random() * 9_00) + 100) / 100.0) ));
				sleep(5000);
			}
		};
	}
}