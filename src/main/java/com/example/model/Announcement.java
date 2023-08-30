package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <h2>Объявление</h2>
 */
@Entity
@Table(name = "announcements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "body")
    private String body;      // Text, description of the announcement
    @Column(name = "contact")
    private String contact;   // Contact number of the ad creator

    @Override
    public String toString() {
        return "Компания: " + companyName+" ✅\n"+"%0AТекст: " + body + '\n' +
                "%0AКонтакт: " + contact;
    }
}
