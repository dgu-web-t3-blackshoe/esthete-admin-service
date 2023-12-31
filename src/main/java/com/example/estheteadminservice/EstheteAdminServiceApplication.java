package com.example.estheteadminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EstheteAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstheteAdminServiceApplication.class, args);
    }

}
