package com.footballhub.footballhubapp.common.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupMessagePrinter implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Application started at:");
        System.out.println("http://localhost:8080");
        System.out.println("https://localhost:8443");
    }

}