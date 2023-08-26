package com.example.main;

import com.example.config.BotConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


@RequiredArgsConstructor
@Component
public class Main {
    private final BotConfig config;
    public static void main(String[] args) throws IOException, InterruptedException {
//        List<String> messages = new ArrayList<>();
//        messages.add("First ad");
//        mess
//        ages.add("Second ad");
//        messages.add("Third ad");
//
//        List<String> chatID = new ArrayList<>();
//        chatID.add("-994628304");
//        chatID.add("-820141740");
//
//        Main main = new Main();
//        main.sendRequest(messages, chatID);

    }

    public void sendRequest(List<String> messages, List<String> chatID) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        for (String msg : messages
        ) {

            Thread.sleep(4000);

            for (int i = 0; i < chatID.size(); i++) {
                String url = "https://api.telegram.org/bot" + config.getToken() + "/sendMessage?chat_id=" + chatID.get(i) + "&text=" + msg.replaceAll("\\s+", "%20");
                System.out.println(url);
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
