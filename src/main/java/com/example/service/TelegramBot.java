package com.example.service;

import com.example.config.BotConfig;
import com.example.model.Announcement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final AdsService service;

    /*
    Создать и получить токен бота нужно в @BotFather в Telegram.
    */
    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/start" -> sendMessage(
                        chatId,
                        "Hello, "+update.getMessage().getChat().getFirstName()+" 🙃");
                case "Пост" -> {
                    try {
                        for (int i = 0; i < 1; i++) {
                            HttpClient client = HttpClient.newHttpClient();

                            HttpRequest request = HttpRequest.newBuilder()
//                                    .uri(URI.create("http://localhost:8080/ads/send_to_groups"))
                                    .uri(URI.create("https://full-telegram-bot-production.up.railway.app/ads/send_to_groups"))
                                    .build();
                            HttpResponse<String> response = client.send(request,
                                    HttpResponse.BodyHandlers.ofString());
                            System.out.println(response.body());
                        }
                        sendMessage(chatId, "Все вакансии успешно отправлены во все группы!");
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } case "Все" ->{
                    for (Announcement ad: service.findAllAds()
                         ) {
                        sendMessage(chatId, ad.toStringForUsers());
                    }

                }

                default -> sendMessage(
                        chatId,
                        "Такой команды не существует."
                );
            }
        }
    }

    public void sendMessage(Long chatId, String messageText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(messageText);
        sendMessage.setChatId(chatId);
        try {
            executeAsync(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Error -> TelegramAPIException");
        }
    }

//    public void sendRequest() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://full-telegram-bot-production.up.railway.app/ads/send_to_groups"))
//                .build();
//        HttpResponse<String> response = client.send(request,
//                HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//
//    }
}

