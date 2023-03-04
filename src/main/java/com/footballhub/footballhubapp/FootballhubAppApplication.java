package com.footballhub.footballhubapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootballhubAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballhubAppApplication.class, args);
        System.out.println("\nServer Started\n\nhttp://localhost:8080\nhttps://localhost:8443");
    }

}
