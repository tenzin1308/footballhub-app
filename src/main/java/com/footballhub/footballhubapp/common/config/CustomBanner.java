package com.footballhub.footballhubapp.common.config;

import com.github.lalyos.jfiglet.FigletFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomBanner implements Banner {

    Logger logger = LoggerFactory.getLogger(CustomBanner.class);

    private List<String> showWelcomeBanner(BufferedReader reader) throws IOException {
        List<String> message = new ArrayList<>();
        List<String> list = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                list.add(line);
            }
        }
        int idx = getRandomIndex(list.size());
        String[] tokens = list.get(idx).split("\\|");
        String author = tokens[0].trim();
        String banner = tokens[1].trim();
        String asciiArt = FigletFont.convertOneLine(banner);
        message.add(asciiArt);
        message.add(String.format("Contributed by -> %s", author));
//            System.out.println(asciiArt);
//            System.out.println("Contributed by -> " + author);
//            System.out.flush();
        return message;
    }

    private int getRandomIndex(int index) {
        Random rand = new Random(System.nanoTime());
        return rand.nextInt(index);
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        //"src/main/resources/static/banner.txt"
        try {
            Resource resource = new ClassPathResource("static/banner.txt");
            InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            List<String> message = showWelcomeBanner(bufferedReader);
            bufferedReader.close();
            out.println(message.get(0));
            out.println(message.get(1));
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}