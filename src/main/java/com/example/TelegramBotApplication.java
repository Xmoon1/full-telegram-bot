package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@SpringBootApplication
@Controller
public class TelegramBotApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(TelegramBotApplication.class, args);

    }

}
