package com.example.service;


import com.example.model.Announcement;
import com.example.repository.AdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class AdsService {
    private final AdsRepository repository;


    public List<Announcement> findAllAds(){
        return repository.findAll();
    }

    public Announcement getOneAd(int id){
        Optional<Announcement> foundedAd = repository.findById(id);
        return foundedAd.orElse(null);
    }

    @Transactional
    public void saveAd(Announcement announcement){
        repository.save(announcement);
    }


    @Transactional
    public void editAd(Announcement announcement){
        Announcement ad = new Announcement();
        ad.setId(announcement.getId());
        ad.setCompanyName(announcement.getCompanyName());
        ad.setBody(announcement.getBody());
        ad.setContact(announcement.getContact());


        repository.save(ad);
    }

    @Transactional
    public void deleteAd(int id){
        repository.deleteById(id);
    }
}
