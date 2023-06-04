package com.example.sobolevawork.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


/**
 *
 * Сущность: "Профиль пользователя"
 *
 * */
@Entity
@Table(name = "user_profile")
@Data
public class Profile {
    // Id - идентификатор пользователя
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    // Связь "Профиль и Пользователь"
    @OneToOne
    private User user;

    // Связь "Профиль и Документ"
    @OneToMany(mappedBy="profile")
    private List<DocumentEntity> documents;
}
