package com.footballhub.footballhubapp;

import com.footballhub.footballhubapp.common.config.CustomBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FootballhubAppApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FootballhubAppApplication.class);
        app.setBanner(new CustomBanner());
        app.addListeners((ApplicationListener<ApplicationStartedEvent>) event -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        });
        app.run(args);
    }

}
