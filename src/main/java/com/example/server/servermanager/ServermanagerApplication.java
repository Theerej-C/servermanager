package com.example.server.servermanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.server.servermanager.enumeration.Status;
import com.example.server.servermanager.model.Server;
import com.example.server.servermanager.repository.ServerRepo;

@SpringBootApplication
public class ServermanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServermanagerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null, "192.18", "mem", "ubu", "pc", Status.SERVER_UP));
			serverRepo.save(new Server((long) 3, "192.18.9", "mem", "ubu", "pc", Status.SERVER_UP));
			serverRepo.save(new Server((long) 5, "192.18.00", "mem", "ubu", "pc", Status.SERVER_UP));
		};

	}

}
