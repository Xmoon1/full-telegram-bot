package com.example.telegram_bot.repository;


import com.example.telegram_bot.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsRepository extends JpaRepository<Announcement, Integer> {
}
