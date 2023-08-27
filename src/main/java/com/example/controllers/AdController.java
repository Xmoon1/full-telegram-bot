package com.example.controllers;
import com.example.model.Announcement;
import com.example.service.AdsService;
import com.example.main.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdController {
    private final AdsService service;
    private final Main main;

    @GetMapping
    public List<Announcement> findAll(){
        return service.findAllAds();
    }


    @PostMapping("/save")
    public ResponseEntity<?> addAnnouncementToDB(@RequestBody Announcement announcement) {
        service.saveAd(announcement);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Ad is successfully added to database!");
    }
    @GetMapping("/send_to_groups")
    public void sendToGroups() throws IOException, InterruptedException {

        List<String> chatID = new ArrayList<>();
        chatID.add("-994628304");
        chatID.add("-820141740");

        List<String> a = new ArrayList<>();
        for (Announcement msg: service.findAllAds()
        ) {
            a.add(msg.getBody());
        }
        main.sendRequest(a, chatID);
    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity<?> deleteAd(@PathVariable int id){
        service.deleteAd(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Post deleted successfully!");
    }
}
