package com.mssql.beadando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BeadandoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeadandoApplication.class, args);
    }

}
