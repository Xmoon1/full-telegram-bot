package com.example.main;

import com.example.config.BotConfig;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
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

        List<Integer> arrayList = Arrays.asList(1, 2, 3, 4, 5);


        for (Integer numb: reverseList(arrayList)
             ) {
            System.out.println(numb);
        }

    }

    public static<T> List<T> reverseList(List<T> list) {
        return new ArrayList<>(Lists.reverse(list));
    }

    public void sendRequest(List<String> messages, List<String> chatID) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        for (String msg : messages
        ) {
            String formattedString = msg.replaceAll("\\n", "%0A");
            Thread.sleep(4000);

            for (int i = 0; i < chatID.size(); i++) {
                String url = "https://api.telegram.org/bot" + config.getToken() + "/sendMessage?chat_id=" + chatID.get(i) + "&text=" + formattedString.replaceAll("\\s", "%20");
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
