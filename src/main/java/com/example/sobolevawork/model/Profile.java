package com.example.sobolevawork.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


/**
 *
 * Сущность "Профиль пользователя"
 *
 * */

@Entity
@Table(name = "user_profile")
@Data
public class Profile {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy="profile")
    private List<DocumentEntity> documents;
}
