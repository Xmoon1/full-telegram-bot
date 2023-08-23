package com.example.telegram_bot.main;

import com.example.telegram_bot.config.BotConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> messages = new ArrayList<>();
        messages.add("First ad");
        messages.add("Second ad");
        messages.add("Third ad");

        List<String> chatID = new ArrayList<>();
        chatID.add("-994628304");
        chatID.add("-820141740");

        Main main = new Main();
        main.sendRequest(messages, chatID);

    }

    public void sendRequest(List<String> messages, List<String> chatID) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        BotConfig configs = new BotConfig();

        for (int j = 0; j < messages.size(); j++) {

            for (String msg : messages
            ) {

                Thread.sleep(2000);

                for (int i = 0; i < chatID.size(); i++) {
                    String url = "https://api.telegram.org/bot" + configs.getToken() + "/sendMessage?chat_id=" + chatID.get(i) + "&text=" + msg.replaceAll("\\s+", "%20");

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .build();
                    HttpResponse<String> response = client.send(request,
                            HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.body());
                }
            }
        }
    }
}
