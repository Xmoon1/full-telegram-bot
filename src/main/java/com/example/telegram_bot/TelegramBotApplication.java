package com.example.telegram_bot;
import com.example.telegram_bot.main.Main;
import com.example.telegram_bot.service.AdsService;
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
